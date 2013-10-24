package com.siping.jco;

import com.sap.conn.jco.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Daisy
 * Date: 13-10-23
 * Time: 下午10:11
 * To change this template use File | Settings | File Templates.
 */
public class JcoClientTest
{
    String destName = "ABAP_AS";
    private JCoDestination destination;
    private JcoClient jcoClient;

    @Before
    public void setUp() throws Exception {
        jcoClient = new JcoClient();
    }

    @Test
    public void shouldConnectToDestination() throws Exception {
        destination = jcoClient.getDestination(destName);

        JCoAttributes jcoAttributes = destination.getAttributes();
        assertThat(jcoAttributes.getClient(),is("800"));
    }

    @Test
    public void shouldGetFunctionModule() throws Exception {
        destination = jcoClient.getDestination(destName);
        JCoFunction function = destination.getRepository().getFunction("BAPI_OBJCL_GETDETAIL");
        function.getImportParameterList().setValue("OBJECTKEY","T-F101");
        function.getImportParameterList().setValue("OBJECTTABLE","MARA");
        function.getImportParameterList().setValue("CLASSNUM",100);
        function.getImportParameterList().setValue("CLASSTYPE","001");

        function.execute(destination);

        System.out.println(function.toXML());
        assertNotNull(function);
    }

    @Test
    public void shouldGetTable() throws Exception {
        destination = jcoClient.getDestination(destName);
        JCoFunction function = destination.getRepository().getFunction("ZBTOB_RFC");
        function.execute(destination);
//        System.out.println(function.getTableParameterList().getValue(0));

    }
}
