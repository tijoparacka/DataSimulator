package com.tijo.streaming.impl.collectors;

import com.tijo.DataSimulator;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.util.Util;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileEventCollector extends AbstractEventCollector{

  private  String outputPath;
  private  int maxRows;
  private Path path ;
  private int fileNameLength;
  BufferedWriter fileWriter;
  private long startTime =System.currentTimeMillis();
  public FileEventCollector()
  {
    super();
    try {
      ConfigUtil config = ConfigUtil.getInstance();
      outputPath = config.getConfig("sim.file.outputdir");
      String rows =config.getConfig("sim.file.maxrows");
      fileNameLength = Integer.parseInt( config.getConfig("sim.file.fileNameLength"));

      if(rows != null &&  rows.trim().length() == 0 ){
        maxRows = 5000000;
        logger.info("Max Rows is not configured. Defaulting to 5,000,000");
      }else{
        maxRows =Integer.parseInt(rows );
      }

      logger.info("Setting up output Folder :" +  outputPath+ " with max rows in each file is "+maxRows);
       this.path = getNewPath(fileNameLength);

      fileWriter = new BufferedWriter(new FileWriter(String.valueOf(path)));

    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage(), e);
      System.exit(1);
    }
  }

  private Path getNewPath(int fileNameLength) throws Exception
  {
    if (fileNameLength <=0 )
      fileNameLength =10;
    String fileName = Util.randomAlphaNumeric(fileNameLength);
    String path =outputPath + "/" + fileName ;
    File folder = new File(outputPath);
    folder.mkdirs();
    if (!folder.exists()){
      logger.error( "*** The folder "+outputPath + " does not exist. Please check the property sim.file.outputdir in config.properties file");
      System.exit(1);
    }
    File file = new File(path);
    if(!file.exists())
      file.createNewFile();
    return Paths.get(path);
  }
  @Override
  public void onReceive(Object message) throws Exception {
    if (message instanceof DumpStats) {
      logger.info("Processed " + numberOfEventsProcessed + " events");
    }else if( message instanceof Event ) {
      numberOfEventsProcessed ++;
     // logger.info("Event " +numberOfEventsProcessed );
//      Files.write(path, (((Event)message ).toText()+"\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        writeFile(path, (writer.writeValueAsString((Event)message )+"\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }
    if(numberOfEventsProcessed % maxRows == 0){
      logger.info("Processed " + numberOfEventsProcessed + " events");
      logger.info("Number of events processed per sec  = " + (maxRows /(  (System.currentTimeMillis() - startTime ) /1000))) ;
      startTime = System.currentTimeMillis();
      this.path = getNewPath(fileNameLength);
      fileWriter.flush();
      fileWriter.close();
      fileWriter = new BufferedWriter(new FileWriter(path.toString()));
    }
    if(DataSimulator.getNumberOfEvents() >0 )
      if(numberOfEventsProcessed > DataSimulator.getNumberOfEvents()){
        System.exit(0);
       }
  }

  private void writeFile(Path path, byte[] bytes, StandardOpenOption append) throws IOException
  {
    fileWriter.write(new String(bytes));
  }

}