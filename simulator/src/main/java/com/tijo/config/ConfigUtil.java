package com.tijo.config;


import com.tijo.DataSimulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ConfigUtil
{
  HashMap<String, String> config = new HashMap<String,String>();
  private static ConfigUtil configUtil;

  public static ConfigUtil getInstance() throws IOException
  {
    if (configUtil == null) {
      configUtil = new ConfigUtil();
    }
    return configUtil;
  }

  public String getConfig(String propertyKey)
  {
    return config.get(propertyKey);
  }
  public Map<String,String> getConfigProperties(){
    return config;
  }

  private ConfigUtil() throws IOException
  {

    // Check if property file exists in class path
    Properties prop = new Properties();
    try (FileInputStream is = new FileInputStream(new File(DataSimulator.getConfigFilePath()))) {
      prop.load(is);
      populateConfigMap(prop);
    }
    catch (FileNotFoundException e) {
      // if not load the default property in resource dir
      Properties properties = new Properties();
      properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
      populateConfigMap(prop);
    }
    // Override any properties which are part of system properties
    Properties sysProp = System.getProperties();
    for (final Map.Entry<Object, Object> entry : sysProp.entrySet()) {
      if (entry.getKey().toString().startsWith("sim.")) {
        config.put(entry.getKey().toString(), entry.getValue().toString());
      }
    }
  }
  public ConfigUtil(String path) throws IOException
  {
    Properties prop = new Properties();
    try (FileInputStream is = new FileInputStream(new File(path))) {
      prop.load(is);
      populateConfigMap(prop);
    }
  }

  private void populateConfigMap(Properties prop)
  {
    for (final Map.Entry<Object, Object> entry : prop.entrySet()) {
      config.put((String) entry.getKey(), (String) entry.getValue());
    }
  }
}
