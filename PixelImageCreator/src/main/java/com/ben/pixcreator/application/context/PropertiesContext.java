
package com.ben.pixcreator.application.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesContext
{

      private Properties	       defaultProp, properties;
      private File		       propFile;
      private final String	       defaultPropertiesFileName = "default.properties";

      private static PropertiesContext instance;


      private PropertiesContext() throws IOException
      {

	    initProperties();
      }


      private void initProperties() throws IOException
      {

	    // initialize default properties

	    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    InputStream is = classloader.getResourceAsStream(defaultPropertiesFileName);
	    defaultProp.load(is);

	    // initialize properties with default in case there are new ones
	    properties = new Properties(defaultProp);

	    propFile = new File("data/config.properties");
	    if (propFile.exists())
	    {

		  properties.load(new FileInputStream(propFile.toString()));

	    }

      }


      public void reset()
      {

	    properties = new Properties(defaultProp);
      }

      public void reset(String key){
    	  properties.setProperty(key, defaultProp.getProperty(key));
      }

      public String getDefault(String key)
      {

	    return defaultProp.getProperty(key);
      }


      public String get(String key)
      {

	    return properties.getProperty(key);
      }


      public void set(String key, String value)
      {

	    properties.setProperty(key, value);
      }


      public static PropertiesContext getInstance() throws IOException
      {

	    if (instance == null)
	    {
		  instance = new PropertiesContext();
	    }
	    return instance;
      }

}
