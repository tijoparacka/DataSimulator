package com.tijo.streaming.impl.collectors;

import com.tijo.DataSimulator;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.Util;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileEventCollector extends AbstractEventCollector{

  private  String outputPath;
  private  int maxRows;
  private Path path ;
  BufferedWriter writer ;
  private long startTime =System.currentTimeMillis();
  public FileEventCollector()
  {
    super();
    try {
      ConfigUtil config = ConfigUtil.getInstance();
      outputPath = config.getConfig("sim.file.outputdir");
      String rows =config.getConfig("sim.file.maxrows");

      if(rows != null &&  rows.trim().length() == 0 ){
        maxRows = 5000000;
        logger.info("Max Rows is not configured. Defaulting to 5,000,000");
      }else{
        maxRows =Integer.parseInt(rows );
      }

      logger.info("Setting up output Folder " +  outputPath+ " with max rows in each file is "+maxRows);
       this.path = getNewPath();

       writer = new BufferedWriter(new FileWriter(String.valueOf(path)));

    } catch (Exception e) {
    e.printStackTrace();
    logger.error(e.getMessage(), e);
    System.exit(1);
    }
  }

  private Path getNewPath() throws Exception
  {
    String fileName = Util.randomAlphaNumeric(10);
    String path =outputPath + "/" + fileName ;
    File file = new File(path);
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
        writeFile(path, (((Event)message ).toText()+"\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }
    if(numberOfEventsProcessed % maxRows == 0){
      logger.info("Processed " + numberOfEventsProcessed + " events");
      logger.info("Number of events processed per sec  = " + (maxRows /(  (System.currentTimeMillis() - startTime ) /1000))) ;
      startTime = System.currentTimeMillis();
      this.path = getNewPath();
      writer.flush();
      writer.close();
      writer = new BufferedWriter(new FileWriter(path.toString()));
    }
    if(DataSimulator.getNumberOfEvents() >0 )
      if(numberOfEventsProcessed > DataSimulator.getNumberOfEvents()){
        System.exit(0);
       }
  }

  private void writeFile(Path path, byte[] bytes, StandardOpenOption append) throws IOException
  {
    writer.write(new String(bytes));
  }

}