package com.siping.jco;

import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

import java.util.HashMap;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Daisy
 * Date: 13-10-23
 * Time: 下午9:56
 * To change this template use File | Settings | File Templates.
 */
public class MyDestinationDataProvider implements DestinationDataProvider {
    private DestinationDataEventListener eL;
    private HashMap<String, Properties> secureDBStorage = new HashMap<String, Properties>();
    @Override
    public Properties getDestinationProperties(String destinationName) {
        try
        {
            //read the destination from DB
            Properties p = secureDBStorage.get(destinationName);

            if(p!=null)
            {
                //check if all is correct, for example
                if(p.isEmpty())
                    throw new DataProviderException(DataProviderException.Reason.INVALID_CONFIGURATION, "destination configuration is incorrect", null);

                return p;
            }

            return null;
        }
        catch(RuntimeException re)
        {
            throw new DataProviderException(DataProviderException.Reason.INTERNAL_ERROR, re);
        }
    }

    @Override
    public boolean supportsEvents() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDestinationDataEventListener(DestinationDataEventListener eventListener) {
        this.eL = eventListener;
    }

    void changeProperties(String destName, Properties properties)
    {
        synchronized(secureDBStorage)
        {
            if(properties==null)
            {
                if(secureDBStorage.remove(destName)!=null)
                    eL.deleted(destName);
            }
            else
            {
                secureDBStorage.put(destName, properties);
                eL.updated(destName); // create or updated
            }
        }
    }
}
