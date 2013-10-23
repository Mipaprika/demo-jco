package com.siping.jco;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import org.junit.Before;
import org.junit.Test;

import static com.sap.conn.jco.ext.Environment.registerDestinationDataProvider;

/**
 * Created with IntelliJ IDEA.
 * User: Daisy
 * Date: 13-10-23
 * Time: 下午10:11
 * To change this template use File | Settings | File Templates.
 */
public class JcoClientTest
{
    MyDestinationDataProvider myProvider;
    String destName = "ABAP_AS";
    private JCoDestination dest;

    @Before
    public void setUp() throws Exception {
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
        dest = JCoDestinationManager.getDestination(destName);

    }

    @Test(expected = JCoException.class)
    public void shouldConnectToDestination() throws Exception {
        JCoDestination destination = JCoDestinationManager.getDestination(destName);
        destination.ping();
    }
}
