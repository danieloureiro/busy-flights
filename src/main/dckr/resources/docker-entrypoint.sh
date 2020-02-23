#!/bin/bash

# Custom PostgreSQL entrypoint, allows for initial configuration through environment variables:
#  - Users: POSTGRES_USERS="user1:user1pass user2:user2pass"
#  - Databases: POSTGRES_DATABASES="database1:user1 database2:user2"
#  - Schemas: POSTGRES_SCHEMAS="schema1:user1 schema3:user2"
#  - Other configs (postgresql.conf): POSTGRES_CONFIGS="work_mem:15MB fsync:off full_page_writes:off"

set -e

file_env() {
	local var="$1"
	local fileVar="${var}_FILE"
	local def="${2:-}"
	if [ "${!var:-}" ] && [ "${!fileVar:-}" ]; then
		echo >&2 "error: both $var and $fileVar are set (but are exclusive)"
		exit 1
	fi
	local val="$def"
	if [ "${!var:-}" ]; then
		val="${!var}"
	elif [ "${!fileVar:-}" ]; then
		val="$(< "${!fileVar}")"
	fi
	export "$var"="$val"
	unset "$fileVar"
}

if [ "${1:0:1}" = '-' ]; then
	set -- postgres "$@"
fi

# allow the container to be started with `--user`
if [ "$1" = 'postgres' ] && [ "$(id -u)" = '0' ]; then
	mkdir -p "$PGDATA"
	chown -R postgres "$PGDATA"
	chmod 700 "$PGDATA"

	mkdir -p /var/run/postgresql
	chown -R postgres /var/run/postgresql
	chmod g+s /var/run/postgresql

	exec gosu postgres "$BASH_SOURCE" "$@"
fi

if [ "$1" = 'postgres' ]; then
	mkdir -p "$PGDATA"
	chown -R "$(id -u)" "$PGDATA" 2>/dev/null || :
	chmod 700 "$PGDATA" 2>/dev/null || :

	# look specifically for PG_VERSION, as it is expected in the DB dir
	if [ ! -s "$PGDATA/PG_VERSION" ]; then
		file_env 'POSTGRES_INITDB_ARGS'
		eval "initdb --username=postgres $POSTGRES_INITDB_ARGS"

		authMethod=trust
		if [ "$POSTGRES_USERS" ]; then
			for USER in $POSTGRES_USERS
			do
				USER_PASSWORD=`echo $USER | cut -d: -f2`
				if [ "$USER_PASSWORD" ]; then
					authMethod=md5
				fi
			done
		fi

		# check password first so we can output the warning before postgres
		# messes it up
		file_env 'POSTGRES_PASSWORD'
		if [ "$POSTGRES_PASSWORD" ]; then
			pass="PASSWORD '$POSTGRES_PASSWORD'"
			authMethod=md5
		else
			pass=
		fi

		if [ "$authMethod" == "trust" ]; then
			cat >&2 <<-'EOWARN'
				****************************************************
				WARNING: No password has been set for the database.
				         This will allow anyone with access to the
				         Postgres port to access your database. In
				         Docker's default configuration, this is
				         effectively any other container on the same
				         system.

				         Use "-e POSTGRES_PASSWORD=password" to set
				         it in "docker run".
				****************************************************
			EOWARN
		fi

		{ echo; echo "host all all all $authMethod"; } | tee -a "$PGDATA/pg_hba.conf" > /dev/null

		# internal start of server in order to allow set-up using psql-client
		# does not listen on external TCP/IP and waits until start finishes
		PGUSER="${PGUSER:-postgres}" \
		pg_ctl -D "$PGDATA" \
			-o "-c listen_addresses='localhost'" \
			-w start

		file_env 'POSTGRES_USER' 'postgres'
		file_env 'POSTGRES_DB' "$POSTGRES_USER"

		psql=( psql -v ON_ERROR_STOP=1 )

		if [ "$POSTGRES_DB" != 'postgres' ]; then
			"${psql[@]}" --username postgres <<-EOSQL
				CREATE DATABASE "$POSTGRES_DB" ;
			EOSQL
			echo
		fi

		if [ "$POSTGRES_USER" = 'postgres' ]; then
			op='ALTER'
		else
			op='CREATE'
		fi
		"${psql[@]}" --username postgres <<-EOSQL
			$op USER "$POSTGRES_USER" WITH SUPERUSER $pass ;
		EOSQL
		echo

		psql+=( --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" )

		# If you want to create more than one user, please use that variable
		# Variable example: POSTGRES_USERS="user1:user1pass user2:user2pass user3:user3password"
		if [ "$POSTGRES_USERS" ]; then
			for USER in $POSTGRES_USERS
			do
				USER_NAME=`echo $USER | cut -d: -f1`
				USER_PASSWORD=`echo $USER | cut -d: -f2`
				if [ "$USER_NAME" = 'postgres' ]; then
					op='ALTER'
				else
					op='CREATE'
				fi
				"${psql[@]}" --username postgres <<-EOSQL
					$op USER "$USER_NAME" WITH SUPERUSER PASSWORD '$USER_PASSWORD' ;
				EOSQL
			done
		fi

		# If you want to create more than one database, please use that variable
		# Variable example: POSTGRES_DATABASES="database1:user1 database2:user2 database3:user3"
		if [ "$POSTGRES_DATABASES" ]; then
			for DATABASE in $POSTGRES_DATABASES
			do
				DATABASE_NAME=`echo $DATABASE | cut -d: -f1`
				DATABASE_OWNER=`echo $DATABASE | cut -d: -f2`
				if [ "$DATABASE_NAME" != 'postgres' ]; then
					if [ "$DATABASE_OWNER" ]; then
						"${psql[@]}" --username postgres <<-EOSQL
						CREATE DATABASE "$DATABASE_NAME" owner "$DATABASE_OWNER" ;
						EOSQL
						echo
					else
						"${psql[@]}" --username postgres <<-EOSQL
							CREATE DATABASE "$DATABASE_NAME" ;
						EOSQL
						echo
					fi
				fi
			done
		fi

		# If you want to create more than one schemas, please use that variable
		# Variable example: $POSTGRES_SCHEMAS="schema1:user1 schema3:user2 schema3:user3"
		if [ "$POSTGRES_SCHEMAS" ]; then
			for SCHEMA in $POSTGRES_SCHEMAS
			do
				SCHEMA_NAME=`echo $SCHEMA | cut -d: -f1`
				SCHEMA_OWNER=`echo $SCHEMA | cut -d: -f2`
                "${psql[@]}" --username postgres <<-EOSQL
					CREATE SCHEMA IF NOT EXISTS "$SCHEMA_NAME" AUTHORIZATION "$SCHEMA_OWNER" ;
				EOSQL
				echo
			done
		fi

		# If you want to set up initial postgresql.conf parameters, please use that variable
		# Variable example: POSTGRES_CONFIGS="work_mem:15MB fsync:off full_page_writes:off"
		if [ "$POSTGRES_CONFIGS" ]; then
			for CONFIG in $POSTGRES_CONFIGS
			do
				CONFIG_NAME=`echo $CONFIG | cut -d: -f1`
				CONFIG_VALUE=`echo $CONFIG | cut -d: -f2`
				"${psql[@]}" --username postgres <<-EOSQL
					ALTER SYSTEM SET $CONFIG_NAME = "$CONFIG_VALUE" ;
				EOSQL
			done
		fi

		echo
		for f in /docker-entrypoint-initdb.d/*; do
			case "$f" in
				*.sh)     echo "$0: running $f"; . "$f" ;;
				*.sql)    echo "$0: running $f"; "${psql[@]}" -f "$f"; echo ;;
				*.sql.gz) echo "$0: running $f"; gunzip -c "$f" | "${psql[@]}"; echo ;;
				*)        echo "$0: ignoring $f" ;;
			esac
			echo
		done

		PGUSER="${PGUSER:-postgres}" \
		pg_ctl -D "$PGDATA" -m fast -w stop

		echo
		echo 'PostgreSQL init process complete; ready for start up.'
		echo
	fi
fi

exec "$@"
