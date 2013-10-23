package com.siping.jco;

import com.sap.conn.jco.ext.DestinationDataProvider;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Daisy
 * Date: 13-10-23
 * Time: 下午10:29
 * To change this template use File | Settings | File Templates.
 */
public class DestinationPropertiesHelper {
    static Properties getDestinationPropertiesFromUI()
    {
        //adapt parameters in order to configure a valid destination
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "appserver");
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  "00");
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "000");
        connectProperties.setProperty(DestinationDataProvider.JCO_USER,   "JCOTESTER");
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "JCOTESTERSPASSWORD");
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG,   "en");
        return connectProperties;
    }
}
