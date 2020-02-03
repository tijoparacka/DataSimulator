package com.tijo.streaming.impl.collectors;

import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class UnsecuredKafkaEventCollector extends AbstractEventCollector {
    private String topicName;
    private String bootstrapServer;
    private Properties props;
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
                                                                 ((Event) message).toText().replace("\n", "")));
                // logger.debug(((Event)message ).toText().replace( "\n",""));
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            numberOfEventsProcessed++;
        }
    }
}
