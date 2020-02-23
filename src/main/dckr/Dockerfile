# Docker image with PostgreSQL.

# Contains:
# * PostgreSQL

FROM postgres:${docker.postgres.version}

LABEL ProjectVersion=${project.version}

ADD resources/docker-entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["postgres"]

HEALTHCHECK --interval=3s --retries=30 --timeout=10s \
    CMD pg_isready || exit 1
