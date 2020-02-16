package com.tijo.streaming.impl.collectors;

import com.tijo.DataSimulator;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.Util;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileEventCollector extends AbstractEventCollector{

  private  String outputPath;
  private  int maxRows;
  private Path path ;
  public FileEventCollector()
  {
    super();
    try {
      ConfigUtil config = ConfigUtil.getInstance();
      outputPath = config.getConfig("sim.file.outputdir");
      maxRows =Integer.parseInt( config.getConfig("sim.file.maxrows"));
      logger.info("Setting up output Folder " +  outputPath+ " with max rows in each file is "+maxRows);
       this.path = getNewPath();

    } catch (Exception e) {
    e.printStackTrace();
    logger.error(e.getMessage(), e);
    System.exit(0);
    }
  }

  private Path getNewPath()
  {
    String fileName = Util.randomAlphaNumeric(10);
    return Paths.get(outputPath + File.pathSeparatorChar + fileName);
  }
  @Override
  public void onReceive(Object message) throws Exception {
    if (message instanceof DumpStats) {
      logger.info("Processed " + numberOfEventsProcessed + " events");
      return;
    }else if( message instanceof Event ) {
      numberOfEventsProcessed ++;
      Files.write(path, ((String )message).getBytes(StandardCharsets.UTF_8));
    }
    if(numberOfEventsProcessed > maxRows){
      this.path = getNewPath();
    }
  }

}