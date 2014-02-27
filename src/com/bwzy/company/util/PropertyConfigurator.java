package com.bwzy.company.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

import javax.naming.NamingException;

import org.apache.log4j.helpers.LogLog;

/**
 * 
 * @author lvyangjun
 *
 */
public class PropertyConfigurator implements Configurator {

    private static PropertyConfigurator propertyConfigurator;
    private Properties properties;
    
    public static PropertyConfigurator getInstance() {
        if (propertyConfigurator == null)
            return new PropertyConfigurator();
        return propertyConfigurator;
    }
    public Properties getProperties( String templates) throws NamingException {
        properties = new Properties();
        Reader reader=new StringReader(templates);
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return properties;
    }

    static
    public void configure(String configFilename) {
        new PropertyConfigurator().doConfigure(configFilename);
    }

    public void doConfigure(String configFileName) {
        Properties props = new Properties();
        FileInputStream istream = null;
        try {
            istream = new FileInputStream(configFileName);
            props.load(istream);
            istream.close();
        } catch (Exception e) {
            if (e instanceof InterruptedIOException || e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not read configuration file [" + configFileName + "].", e);
            LogLog.error("Ignoring configuration file [" + configFileName + "].");
            return;
        } finally {
            if (istream != null) {
                try {
                    istream.close();
                } catch (InterruptedIOException ignore) {
                    Thread.currentThread().interrupt();
                } catch (Throwable ignore) {
                }

            }
        }
    }
}
