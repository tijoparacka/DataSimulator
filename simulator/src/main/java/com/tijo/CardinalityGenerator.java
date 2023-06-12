package com.tijo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.util.Util;
import com.tijo.streaming.impl.domain.generic.GenericEventGenerator;
import com.tijo.streaming.impl.domain.generic.MetaData;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CardinalityGenerator
{
  private static MetaData[] metaDatas;
  private static String baseDir ;
  public static void main(String[] args) throws Exception
  {
    if (args != null && args.length != 2) {
      System.out.println(" Please provide a config file .");
      System.exit(0);
    }
    baseDir = args[0];
    ConfigUtil conf = new ConfigUtil(args[1]);
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
    String dir = baseDir+File.separator+conf.getConfig("sim.cardinality.generator.folder");
    File dirFile = new File(dir);
    dirFile.mkdirs();
    for (int i = 0; i < cardinality; i++) {
      sb.append(Util.randomAlphaNumeric(limit.intValue()) ).append("\n");
    }
    Path path = Paths.get(dir+File.separator+file);
    Files.write(path,sb.toString().getBytes(StandardCharsets.UTF_8));
    System.out.println("Written Random data to "+file);
  }
}
