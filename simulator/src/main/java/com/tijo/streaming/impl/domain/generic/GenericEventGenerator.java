package com.tijo.streaming.impl.domain.generic;

import akka.actor.ActorRef;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.Util;
import com.tijo.streaming.impl.domain.AbstractEventEmitter;
import com.tijo.streaming.impl.messages.EmitEvent;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Date;
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
  ConfigUtil config;
  String[] filePaths;
  MetaData[] metaDatas;
  HashMap<String,List<String>> dim = new HashMap<String,List<String>>();
  RandomDataGenerator rand = new RandomDataGenerator();
  Random randomUtil = new Random();
  ObjectMapper mapper = new ObjectMapper();
  Class eventClass ;
  public GenericEventGenerator() throws Exception
  {
    try {

      config = ConfigUtil.getInstance();
      //String dimFiles = config.getConfig("sim.generic.dimension.files");
      String metaDataJson=config.getConfig("sim.generic.metadata");
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
          List<String> dimData = new ArrayList<>(Files.readAllLines(Paths.get(metaData.getFile()), StandardCharsets.UTF_8));
          dim.put(metaData.getDimension(), dimData);
        }
        else{
          System.out.println("not loading");
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
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("\"Time\":" ).append(System.currentTimeMillis());
    for (int i = 0; i < metaDatas.length; i++) {
      MetaData m = metaDatas[i];
      //if (i != metaDatas.length-1)
      sb.append(",");

      sb.append("\"").append(m.getDimension() ).append("\":");
      switch (m.getType()) {
        case FIXED:
          int size = dim.get(m.getDimension()).size();
          int index = randomUtil.nextInt(size);
          sb.append("\"").append(dim.get(m.getDimension()).get(index)).append("\"");
          break;

        case STRING:
          sb.append("\"").append(Util.randomAlphaNumeric(m.getLimit())).append("\"");
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
      }
    }
    sb.append("}");
   // System.out.println(sb.toString());
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
