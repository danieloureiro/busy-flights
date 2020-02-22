package com.travix.medusa.busyflights.connector;

import com.travix.medusa.busyflights.connector.impl.CrazyAirConnector;
import com.travix.medusa.busyflights.connector.impl.ToughJetConnector;
import com.travix.medusa.busyflights.enums.Supplier;

import java.util.ArrayList;
import java.util.List;

public class ConnectorFactory {
    public static SupplierConnector resolve(Supplier supplier) {

        switch(supplier){
            case CrazyAir: return new CrazyAirConnector();
            case ToughJet: return new ToughJetConnector();
        }
        return null;
    }

    public static List<SupplierConnector> getAllConnectors() {

        //TODO: perhaps make dynamic
        List<SupplierConnector> suppliers = new ArrayList<>();
        suppliers.add(new CrazyAirConnector());
        suppliers.add(new ToughJetConnector());

        return suppliers;
    }
}
