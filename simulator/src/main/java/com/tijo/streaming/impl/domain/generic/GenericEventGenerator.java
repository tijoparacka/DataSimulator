package com.tijo.streaming.impl.domain.generic;

import akka.actor.ActorRef;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.util.Util;
import com.tijo.streaming.impl.domain.AbstractEventEmitter;
import com.tijo.streaming.impl.messages.EmitEvent;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GenericEventGenerator extends AbstractEventEmitter
{
  public static final String FIXED = "FIXED";
  public static final String RANDOM = "RANDOM";
  public static final String STRING = "STRING";
  public static final String LONG = "LONG";
  public static final String INT = "INT";
  public static final String DOUBLE = "DOUBLE";
  public static final String DATE = "DATE";
  public static final String DATE_RANGE = "DATE_RANGE";
  public static final String WITH_CARDINALITY = "WITH_CARDINALITY";
  public static final String CONSTANT_STRING = "CONSTANT_STRING";

  StringBuilder sb = new StringBuilder();
  ConfigUtil config;
  String[] filePaths;
  MetaData[] metaDatas;
  HashMap<String,List<String>> dim = new HashMap<String,List<String>>();
  RandomDataGenerator rand = new RandomDataGenerator();
  Random randomUtil = new Random();
  ObjectMapper mapper = new ObjectMapper();
  Class eventClass ;
  String dir ;
  public GenericEventGenerator() throws Exception
  {
    try {

      config = ConfigUtil.getInstance();
      //String dimFiles = config.getConfig("sim.generic.dimension.files");
      String metaDataJson=config.getConfig("sim.generic.metadata");
      this.dir=config.getConfig( "sim.cardinality.generator.folder");
      ObjectMapper objectMapper = new ObjectMapper();
      this.metaDatas =objectMapper.readValue(metaDataJson, MetaData[].class);
      this.eventClass= Class.forName(config.getConfig("sim.generic.eventClass"));
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new Exception("ioooooo poneeee.....");
    }

    for (MetaData metaData : metaDatas) {
      try {
        if (metaData.getType().equalsIgnoreCase(FIXED)) {
          List<String> dimData = new ArrayList<>(Files.readAllLines(Paths.get(dir+"/"+metaData.getFile()), StandardCharsets.UTF_8));
          dim.put(metaData.getDimension(), dimData);
        }
        else{
          System.out.println("Cardinality not loading for "+ metaData.getDimension());
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public GenericEvent generateEvent() throws Exception
  {
   sb.setLength(0);
    sb.append("{");

    for (int i = 0; i < metaDatas.length; i++) {
      MetaData m = metaDatas[i];
      if (i != 0)
        sb.append(",");

      sb.append("\"").append(m.getDimension() ).append("\":");
      switch (m.getType()) {
        case FIXED :
          int size = dim.get(m.getDimension()).size();
          int index = randomUtil.nextInt(size);
          sb.append("\"").append(dim.get(m.getDimension()).get(index)).append("\"");
          break;

        case STRING:
          sb.append("\"").append(Util.randomAlphaNumeric(m.getLimit())).append("\"");
          break;
        case CONSTANT_STRING:
          int len = m.getConstants().length;
          sb.append("\"").append(m.getConstants() [randomUtil.nextInt(len)]).append("\"");
          break;
        case INT:
          sb.append(rand.nextInt(0,m.getLimit().intValue()));
          break;

        case LONG:
          sb.append(rand.nextLong(0,m.getLimit().longValue()));
          break;
        case DOUBLE:
          sb.append(randomUtil.nextDouble() * m.getLimit());
          break;
        case DATE_RANGE:
          sb.append(rand.nextLong( m.getStart().longValue(),  m.getEnd().longValue()));
          break;
        case DATE:
          sb.append(System.currentTimeMillis()- rand.nextLong(0,m.getLimit().longValue()));
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + m.getType());
      }
    }
    sb.append("}");
    System.out.println(sb.toString());
    try {
      return (GenericEvent) mapper.readValue(sb.toString(), eventClass );
    }
    catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new Exception("Pani paali...");
    }
  }

  @Override
  public void onReceive(Object message) throws Exception
  {
    if (message instanceof EmitEvent) {
      ActorRef actor = this.context().system()
                           .actorFor("akka://EventSimulator/user/eventCollector");

      while (true) {
        GenericEvent event = generateEvent();
        actor.tell(event, this.getSelf());

      }
    }
  }
}
