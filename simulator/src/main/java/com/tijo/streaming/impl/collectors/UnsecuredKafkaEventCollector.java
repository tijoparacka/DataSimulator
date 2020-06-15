package com.tijo.streaming.impl.collectors;

import com.tijo.DataSimulator;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Properties;

public class UnsecuredKafkaEventCollector extends AbstractEventCollector {
    private String topicName;
    private String bootstrapServer;
    private Properties props;
    private  int maxLoggingRows;
    private long startTime =System.currentTimeMillis();
    private org.apache.kafka.clients.producer.Producer<String, String> producer;

    public UnsecuredKafkaEventCollector() {
        super();
        try {
            ConfigUtil config = ConfigUtil.getInstance();
            bootstrapServer = config.getConfig("sim.kafka.bootstrap.servers");
            topicName = config.getConfig(
                    "sim.kafka.topicName");
            logger.info("Setting up bootstrap servers " +  bootstrapServer+ " and producing to topic "+topicName);
            props = new Properties();
            props.put("bootstrap.servers", bootstrapServer);
            props.put("key.serializer",
                    "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer",
                    "org.apache.kafka.common.serialization.StringSerializer");
            producer = new KafkaProducer<String, String>(props);
            String rows =config.getConfig("sim.logging.rowCount");
            if(rows != null &&  rows.trim().length() == 0 ){
                maxLoggingRows = 5000000;
                logger.info("Max Rows is not configured. Defaulting to 5,000,000");
            }else{
                maxLoggingRows =Integer.parseInt(rows );
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    public UnsecuredKafkaEventCollector(int maxEvents) {
        super(maxEvents);
    }

    @Override
    public void onReceive(Object message) throws Exception {
//        logger.debug(message);
        if (message instanceof DumpStats) {
            logger.info("Processed " + numberOfEventsProcessed + " events");
            return;
        }else if( message instanceof Event) {
            try {
                producer.send(new ProducerRecord<String, String>(topicName,
                                                                 (writer.writeValueAsString ((Event) message)).replace("\n", "")));
                numberOfEventsProcessed++;
                if(numberOfEventsProcessed % maxLoggingRows == 0){
                    logger.info("Processed " + numberOfEventsProcessed + " events");
                 //   logger.info("Number of events processed per sec  = " + (maxLoggingRows /(  (System.currentTimeMillis() - startTime ) * 1000))) ;
                 //   startTime = System.currentTimeMillis();
                }
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            if(DataSimulator.getNumberOfEvents() >0 )
                if(numberOfEventsProcessed > DataSimulator.getNumberOfEvents()){
                    System.exit(0);
                }
        }
    }
}
