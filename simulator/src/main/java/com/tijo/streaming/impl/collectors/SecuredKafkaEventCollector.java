package com.tijo.streaming.impl.collectors;

import com.tijo.DataSimulator;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.lang.reflect.Method;
import java.util.Properties;

public class SecuredKafkaEventCollector extends AbstractEventCollector {
    private String topicName;
    private String bootstrapServer;
    private Properties props;
    private  int maxLoggingRows;
    private String  keyColumn ;
    private long startTime =System.currentTimeMillis();
    private Class eventClass ;
    private Method method;
    private org.apache.kafka.clients.producer.Producer<String, String> producer;

    public SecuredKafkaEventCollector() {
        super();
        try {
            ConfigUtil config = ConfigUtil.getInstance();
            bootstrapServer = config.getConfig("sim.kafka.bootstrap.servers");
            topicName = config.getConfig("sim.kafka.topicName");
            logger.info("Setting up bootstrap servers " +  bootstrapServer+ " and producing to topic "+topicName);
            String username = config.getConfig("sim.kafka.username");
            String password = config.getConfig("sim.kafka.password");
            props = new Properties();

            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            // Configure SASL/SCRAM properties
            props.put("security.protocol", "SASL_SSL");
            props.put("sasl.mechanism", "PLAIN");
            props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required " +
                                               "username=\"" + username + "\" " +
                                               "password=\"" + password + "\";");
//            props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required " +
//                                               "username=\"" + username + "\" " +
//                                               "password=\"" + password + "\";");


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

    public SecuredKafkaEventCollector(int maxEvents) {
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
                if (method != null ) {
                    method.setAccessible(true);
                    String result = (String) method.invoke(message);
                    producer.send(new ProducerRecord<String, String>(topicName, result, data));
                }else {
                    producer.send(new ProducerRecord<String, String>(topicName, data));
                }
                numberOfEventsProcessed++;
                if(numberOfEventsProcessed % maxLoggingRows == 0){
                    logger.info("Processed " + numberOfEventsProcessed + " events");
                    logger.info("Number of events processed per sec  = " + (maxLoggingRows *1.00 /(  (System.currentTimeMillis() - startTime ) / 1000))) ;
                    startTime = System.currentTimeMillis();
                }
                if(DataSimulator.getNumberOfEvents() >0 )
                    if(numberOfEventsProcessed > DataSimulator.getNumberOfEvents()){
                        System.exit(0);
                    }
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
