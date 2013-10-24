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
    private static JCoDestination destination = null;
    private MyDestinationDataProvider myProvider = null;

    public JCoDestination getDestination(String destName) throws JCoException {
        if (this.destination == null){
            myProvider = new MyDestinationDataProvider();
            try
            {
                registerDestinationDataProvider(myProvider);
            }
            catch(IllegalStateException providerAlreadyRegisteredException)
            {
                throw new Error(providerAlreadyRegisteredException);
            }
            myProvider.changeProperties(destName,DestinationPropertiesHelper.getDestinationPropertiesFromUI());
            this.destination = JCoDestinationManager.getDestination(destName);
            return this.destination;
        }
        else {
            return destination;
        }
    }

}
