package com.siping.jco;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;

import static com.sap.conn.jco.ext.Environment.registerDestinationDataProvider;

/**
 * Created with IntelliJ IDEA.
 * User: Daisy
 * Date: 13-10-23
 * Time: 下午9:56
 * To change this template use File | Settings | File Templates.
 */
public class JcoClient {
    public JCoDestination connectToDestination(String destName) throws JCoException {
        MyDestinationDataProvider myProvider = new MyDestinationDataProvider();
        try
        {
            registerDestinationDataProvider(myProvider);
        }
        catch(IllegalStateException providerAlreadyRegisteredException)
        {
            throw new Error(providerAlreadyRegisteredException);
        }

        myProvider.changeProperties(destName,DestinationPropertiesHelper.getDestinationPropertiesFromUI());
        return JCoDestinationManager.getDestination(destName);
    }
}
