package com.travix.medusa.busyflights.connector;

import com.travix.medusa.busyflights.connector.impl.CrazyAirConnector;
import com.travix.medusa.busyflights.connector.impl.ToughJetConnector;
import com.travix.medusa.busyflights.enums.Supplier;

import java.util.ArrayList;
import java.util.List;

public final class ConnectorFactory {

    private ConnectorFactory() {
    }

    public static SupplierConnector resolve(Supplier supplier) {

        switch(supplier){
            case CRAZY_AIR: return new CrazyAirConnector();
            case TOUGH_JET: return new ToughJetConnector();
        }
        return null;
    }

    public static List<SupplierConnector> getAllConnectors() {

        List<SupplierConnector> suppliers = new ArrayList<>();
        suppliers.add(new CrazyAirConnector());
        suppliers.add(new ToughJetConnector());

        return suppliers;
    }
}
