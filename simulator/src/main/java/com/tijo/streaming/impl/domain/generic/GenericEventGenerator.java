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

import java.text.SimpleDateFormat;
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
  long sleepTime = 0;
  public GenericEventGenerator() throws Exception
  {
    try {

      config = ConfigUtil.getInstance();
      //String dimFiles = config.getConfig("sim.generic.dimension.files");
      String metaDataJson=config.getConfig("sim.generic.metadata");
      this.dir=config.getConfig( "sim.cardinality.generator.folder");
      this.sleepTime = config.getConfig( "sim.generator.sleepTimeMilliSec") != null? Long.parseLong( config.getConfig( "sim.generator.sleepTimeMilliSec") ):0 ;
      ObjectMapper objectMapper = new ObjectMapper();
      this.metaDatas =objectMapper.readValue(metaDataJson, MetaData[].class);
      this.eventClass= Class.forName(config.getConfig("sim.generic.eventClass"));
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new Exception("ioooooo poneeee.....");
    }

    SimpleDateFormat sf =null;
    for (MetaData metaData : metaDatas) {
      try {
        switch (metaData.getType()) {
          case FIXED :
            List<String> dimData = new ArrayList<>(Files.readAllLines(Paths.get(dir+"/"+metaData.getFile()), StandardCharsets.UTF_8));
            dim.put(metaData.getDimension(), dimData);
            break;
          case DATE:
            if(metaData.getFormat() != null ){
              sf = new SimpleDateFormat(metaData.getFormat());
              metaData.setDateFormatter(sf);
            }else{
              throw new Exception (" Please specify a Java Date format");
            }
            break;
          case DATE_RANGE :
            if(metaData.getFormat() != null ){
              sf = new SimpleDateFormat(metaData.getFormat());
              metaData.setLongStart(sf.parse(metaData.getStart()).getTime());
              metaData.setLongEnd(sf.parse(metaData.getEnd()).getTime());
              metaData.setDateFormatter(sf);
            }else{
              throw new Exception (" Please specify a Java Date format");
            }
            break;
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
      String date =null;
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

          int intVal =rand.nextInt(0,m.getLimit().intValue());
          if(m.getFormat()!=null && m.getFormat().length() != 0 ){
            sb.append(String.format(m.getFormat(),intVal));
          }else{
            sb.append(intVal);
          }
          break;
        case LONG:
          long longVal =rand.nextLong(0,m.getLimit().longValue());
          if(m.getFormat()!=null && m.getFormat().length() != 0 ){
            sb.append(String.format(m.getFormat(),longVal));
          }else{
            sb.append(longVal);
          }
          break;
        case DOUBLE:
          double doubleVal =randomUtil.nextDouble() * m.getLimit();
          if(m.getFormat()!=null  && m.getFormat().length() != 0 ){
            sb.append(String.format(m.getFormat(),doubleVal));
          }else{
            sb.append(doubleVal);
          }
          break;
        case DATE_RANGE:
          date = m.getDateFormatter().format(new Date (rand.nextLong( m.getLongStart(),  m.getLongEnd()) ));
          sb.append('"').append(date).append('"');
          break;
        case DATE:
          date = m.getDateFormatter().format(new Date(System.currentTimeMillis() - rand.nextLong(0, m.getLimit().longValue() )));
          sb.append('"').append(date).append('"');
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + m.getType());
      }
    }
    sb.append("}");
    logger.debug(sb.toString());
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
        Thread.sleep(sleepTime);

      }
    }
  }
}
