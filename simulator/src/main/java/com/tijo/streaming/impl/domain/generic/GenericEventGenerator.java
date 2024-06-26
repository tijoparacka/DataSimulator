package com.tijo.streaming.impl.domain.generic;

import akka.actor.ActorRef;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tijo.DataSimulator;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.util.Util;
import com.tijo.streaming.impl.domain.AbstractEventEmitter;
import com.tijo.streaming.impl.messages.EmitEvent;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import bsh.Interpreter;

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
  public static final String SCRIPT = "SCRIPT";
  public static final String LOOKUP = "LOOKUP";

  StringBuilder sb = new StringBuilder();
  ConfigUtil config;
  String[] filePaths;
  MetaData[] metaDatas;
  HashMap<String,Object> dim = new HashMap<String,Object>();
  RandomDataGenerator rand = new RandomDataGenerator();
  Random randomUtil = new Random();
  ObjectMapper mapper = new ObjectMapper();
  Class[] eventClasses ;
  String dir ;
  protected long sleepTime = 0;
  Interpreter interpreter = new Interpreter();
  private GenericEventGenerator() throws Exception
  {
    this(null);
  }

  public GenericEventGenerator(MetaData[] metaDatas) throws Exception
  {


  }

  private Map<String,String> convert2Map(List<String> dimData)
  {
    Iterator<String> iterator = dimData.iterator();
    Map<String,String> map = new HashMap<String,String>();
    while (iterator.hasNext()){
      String[] val = iterator.next().split(",");
      map.put(val[0],val[1]);
    }
    return map;
  }


  @Override
  public GenericEvent[] generateEvent() throws Exception
  {
    sb.setLength(0);
    sb.append("{");
    Hashtable<String,String> row = new Hashtable<String,String>();

    for (int i = 0; i < metaDatas.length; i++) {
      MetaData m = metaDatas[i];
      if (i != 0)
        sb.append(",");
      String date =null;
      sb.append("\"").append(m.getDimension() ).append("\":");
      String val = null;
      switch (m.getType()) {
        case FIXED :
          int size = ((ArrayList)dim.get(m.getDimension())).size();
          if (0==size ){
            logger.error(String.format(" Cardinality file for dimension %s is empty in file %s.",m.getDimension(),m.getFile() ));
            System.exit(1);
          }
          int index = randomUtil.nextInt(size);
          val =  (String) ((ArrayList) dim.get(m.getDimension())).get(index);
          sb.append("\"").append(val).append("\"");
          break;
        case LOOKUP:
          HashMap<String,String> lookup = (HashMap<String, String>) dim.get(m.getDimension());
          if (lookup == null){
            throw new Exception("No value found for the lookup "+m.getDimension());
          }
          val = lookup.get(row.get(m.getReferenceDim()));
          if(val == null)
            val="";
          sb.append("\"").append(val).append("\"");
          break;

        case STRING:
          val = Util.randomAlphaNumeric(m.getLimit());
          sb.append("\"").append(val).append("\"");
          break;
        case CONSTANT_STRING:
          int len = m.getConstants().length;
          val =m.getConstants() [randomUtil.nextInt(len)];
          sb.append("\"").append(val).append("\"");
          break;

        case INT:
          int intVal =rand.nextInt(0,m.getLimit().intValue());
          if(m.getFormat()!=null && m.getFormat().length() != 0 ){
            val = String.format(m.getFormat(),intVal);
            sb.append(val);
          }else{
            val = intVal+"";
            sb.append(intVal);
          }
          break;
        case LONG:
          long longVal =rand.nextLong(0,m.getLimit().longValue());
          if(m.getFormat()!=null && m.getFormat().length() != 0 ){
            val = String.format(m.getFormat(),longVal);
            sb.append(val);
          }else{
            val =longVal+"";
            sb.append(longVal);
          }
          break;
        case DOUBLE:
          double doubleVal =randomUtil.nextDouble() * m.getLimit();
          if(m.getFormat()!=null  && m.getFormat().length() != 0 ){
            val = String.format(m.getFormat(),doubleVal);
            sb.append(val);
          }else{
            val = doubleVal +"";
            sb.append(doubleVal);
          }
          break;
        case DATE_RANGE:
          date = m.getDateFormatter().format(new Date (rand.nextLong( m.getLongStart(),  m.getLongEnd()) ));
          val = date;
          sb.append('"').append(date).append('"');
          break;
        case DATE:
          long limit =0;
          if (m.getLimit() != null  || m.getLimit().intValue()!= 0  ){
            limit =rand.nextLong(0, m.getLimit().longValue());
          }
          long epoc = System.currentTimeMillis() - limit;
          //if no format mentioned then default to epoc
          if (m.getDateFormatter() != null ) {
            date = m.getDateFormatter().format(new Date(epoc));
            val = date;
          }else {
            val = epoc+"";
          }
          sb.append('"').append(val).append('"');
          break;
        case SCRIPT :
          interpreter.set("row",row);
//          interpreter.source(dir + File.separatorChar+ m.getFile());
          interpreter.eval(m.getScript());
          val = (String)interpreter.get("data")+"";
          sb.append('"').append(val).append('"');
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + m.getType());
      }
      row.put(m.getDimension(),val);
    }
    sb.append("}");

    logger.debug(sb.toString());
    return getGenericEvents(sb.toString());
  }

  protected GenericEvent[]
  getGenericEvents(String eventString) throws Exception
  {
    try {
      GenericEvent[] genericEvents = new GenericEvent[eventClasses.length];
      for (int i = 0; i <eventClasses.length ; i++) {
        genericEvents[i] = (GenericEvent) mapper.readValue(eventString, eventClasses[i] );
      }
      return genericEvents;
    }
    catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new Exception("Pani paali...");
    }
  }


  protected void initGenerator() throws Exception
  {
    try {
      config = ConfigUtil.getInstance();
      //String dimFiles = config.getConfig("sim.generic.dimension.files");

      this.dir = config.getConfig("sim.cardinality.generator.folder");
      this.sleepTime = config.getConfig("sim.generator.sleepTimeMilliSec") != null ? Long.parseLong(config.getConfig(
          "sim.generator.sleepTimeMilliSec")) : 0;
      if (null == metaDatas) {
        ObjectMapper objectMapper = new ObjectMapper();
        String metaDataJson = config.getConfig("sim.generic.metadata");
        try {
          this.metaDatas = objectMapper.readValue(metaDataJson, MetaData[].class);
        }catch (IOException e) {
          throw new Exception( "Unable to parse metadata . Please check the metadata property in config file.");
        }
      } else{
        this.metaDatas = metaDatas;
      }
      String[] eventClassName = config.getConfig("sim.generic.eventClass").split(",");
      eventClasses = new Class[eventClassName.length];
      for (int i = 0; i < eventClassName.length ; i++) {
        eventClasses[i]= Class.forName(eventClassName[i]);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new Exception("ioooooo poneeee.....Error in Initializing Generator please check  config file");
    }

    SimpleDateFormat sf =null;
    for (MetaData metaData : metaDatas) {
      try {
        switch (metaData.getType()) {
          case FIXED :

            File f = new File (dir + "/" + metaData.getFile());
            if (!f.exists()) {
              logger.error(String.format(
                  " Cardinality file for dimension \"%s\" is not defined in file - %s. Alternatively you could generate cardinality by running bin/genCardinality.sh  <config file>",
                  metaData.getDimension(),
                  metaData.getFile()));
              System.exit(1);
            }
            List<String> dimData = new ArrayList<>(Files.readAllLines(Paths.get(f.getAbsolutePath()), StandardCharsets.UTF_8));
            dim.put(metaData.getDimension(), dimData);
            break;
          case LOOKUP:
            dimData = new ArrayList<>(Files.readAllLines(Paths.get(dir+"/"+metaData.getFile()), StandardCharsets.UTF_8));
            Map<String,String > map = convert2Map(dimData);
            dim.put(metaData.getDimension(),map);
            break;
          case DATE:
//            if(metaData.getFormat() != null ){
              if ( metaData.getFormat().trim().length() >0) {
                sf = new SimpleDateFormat(metaData.getFormat());
                metaData.setDateFormatter(sf);
            } else {
              logger.info("no date format mentioned for "+ metaData.getDimension()+" . defaulting to epoc ");
              metaData.setDateFormatter(null);
            }
//            }else{
//              throw new Exception (" Please specify a Java Date format");
//            }
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
  public void onReceive(Object message) throws Exception
  {
    initGenerator();

    if (message instanceof EmitEvent) {
      Class[] eventCollectorClass = DataSimulator.getEventCollectorClasses();
      ActorRef[] actor = new ActorRef[eventCollectorClass.length];
      for (int i = 0; i < eventCollectorClass.length ; i++) {
        actor[i] = this.context().system()
                       .actorFor("akka://EventSimulator/user/"+eventCollectorClass[i].getSimpleName());
      }

      while (true) {
        GenericEvent[] event = generateEvent();
        for (int i = 0; i <actor.length ; i++) {
          actor[i].tell(event[i], this.getSelf());
        }
        Thread.sleep(sleepTime);
      }
    }
  }

}
