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
import java.lang.reflect.Method;
import java.util.Properties;

public class UnsecuredKafkaEventCollector extends AbstractEventCollector {
    private String topicName;
    private String bootstrapServer;
    private Properties props;
    private  int maxLoggingRows;
    private String  keyColumn ;
    private long startTime =System.currentTimeMillis();
    private Class eventClass ;
    private Method method;
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
            keyColumn = config.getConfig("sim.kafka.keyColumn");
            if(keyColumn !=null && keyColumn.trim().length() >0){
                eventClass = Class.forName(config.getConfig("sim.generic.eventClass"));
                method = eventClass.getDeclaredMethod("get" + keyColumn.substring(0, 1).toUpperCase()
                                                        + keyColumn.substring(1),null);
            }
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
        logger.debug(message);
        if (message instanceof DumpStats) {
            logger.info("Processed " + numberOfEventsProcessed + " events");
            return;
        }else if( message instanceof Event) {
            try {
                String data = writer.writeValueAsString ((Event) message).replace("\n", "");
                method.setAccessible(true);
                String result = (String)method.invoke(message);
                producer.send(new ProducerRecord<String, String>(topicName,result,data ));
                numberOfEventsProcessed++;
                if(numberOfEventsProcessed % maxLoggingRows == 0){
                    logger.info("Processed " + numberOfEventsProcessed + " events");
                    logger.info("Number of events processed per sec  = " + (maxLoggingRows /(  (System.currentTimeMillis() - startTime ) * 1000))) ;
                    startTime = System.currentTimeMillis();
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
