package com.tijo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.Util;
import com.tijo.streaming.impl.domain.generic.GenericEventGenerator;
import com.tijo.streaming.impl.domain.generic.MetaData;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CardinalityGenerator
{
  private static MetaData[] metaDatas;

  public static void main(String[] args) throws Exception
  {
    if (args != null && args.length != 1) {
      System.out.println(" Please provide a config file .");
      System.exit(0);
    }
    ConfigUtil conf = new ConfigUtil(args[0]);
    String metaDataJson = conf.getConfig("sim.generic.metadata");
    ObjectMapper objectMapper = new ObjectMapper();
    metaDatas = objectMapper.readValue(metaDataJson, MetaData[].class);
    for (MetaData metaData : metaDatas) {
      try {
        if (metaData.getType().equalsIgnoreCase(GenericEventGenerator.FIXED)) {
          generateRandomData(
              metaData.getFile(),
              metaData.getDimension(),
              metaData.getLimit(),
              metaData.getCardinality(),
              conf
          );

        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private static void generateRandomData(
      String file,
      String dimension,
      Double limit,
      int cardinality,
      ConfigUtil conf
  )
      throws IOException
  {

    StringBuilder sb = new StringBuilder();
    String dir = conf.getConfig("sim.cardinality.generator.folder");
    for (int i = 0; i < cardinality; i++) {
      sb.append(Util.randomAlphaNumeric(limit.intValue()) ).append("\n");
    }
    Path path = Paths.get(dir+"/"+file);
    Files.write(path,sb.toString().getBytes(StandardCharsets.UTF_8));
    System.out.println("Written Random data to "+file);
  }
}
