package com.travix.medusa.busyflights.connector;

import com.travix.medusa.busyflights.connector.impl.CrazyAirConnector;
import com.travix.medusa.busyflights.connector.impl.ToughJetConnector;
import com.travix.medusa.busyflights.enums.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 * @since 0.0.1
 */
public final class ConnectorFactory {

    private ConnectorFactory() {
    }

    /**
     * This initialize a given {@link Supplier}
     *
     * @param supplier a given {@link Supplier}
     *
     * @return a registered {@link SupplierConnector}.
     */
    public static SupplierConnector resolve(Supplier supplier) {

        switch (supplier) {
            case CRAZY_AIR:
                return new CrazyAirConnector();
            case TOUGH_JET:
                return new ToughJetConnector();
        }
        return null;
    }

    /**
     * This get all the registered {@link SupplierConnector}.
     *
     * @return a list of registered {@link SupplierConnector}
     */
    public static List<SupplierConnector> getAllConnectors() {

        List<SupplierConnector> suppliers = new ArrayList<>();
        suppliers.add(new CrazyAirConnector());
        suppliers.add(new ToughJetConnector());

        return suppliers;
    }
}
