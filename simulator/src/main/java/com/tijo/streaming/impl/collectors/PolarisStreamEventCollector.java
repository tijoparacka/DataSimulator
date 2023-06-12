package com.tijo.streaming.impl.collectors;

import com.tijo.DataSimulator;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class PolarisStreamEventCollector extends AbstractEventCollector {
    private String connectionName;
    private String psEndPointURL;
    private String psTableName;
    private String psConnJsonPayload;
    private String token;

    private Properties props;
    private  int maxLoggingRows;
    private long startTime =System.currentTimeMillis();
    private Class eventClass ;
    private Method method;
    HttpPost request ;

    public PolarisStreamEventCollector() {
        super();
        try {
            ConfigUtil config = ConfigUtil.getInstance();
            connectionName = config.getConfig("sim.polaris.stream.connectionName");
            psEndPointURL= config.getConfig("sim.polaris.stream.psEndPointURL");
            psTableName= config.getConfig("sim.polaris.stream.psTableName");
            psConnJsonPayload= config.getConfig("sim.polaris.stream.psConnJsonPayload");
            token= config.getConfig("ssim.polaris.stream.token");

            String rows =config.getConfig("sim.logging.rowCount");
            eventClass = Class.forName(config.getConfig("sim.generic.eventClass"));

            if(rows != null &&  rows.trim().length() == 0 ){
                maxLoggingRows = 5000000;
                logger.info("Max Rows is not configured. Defaulting to 5,000,000");
            }else{
                maxLoggingRows =Integer.parseInt(rows );
            }
            //
            URL url = new URL(psEndPointURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Authorization","Bearer "+ token.trim());
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestMethod("POST");


        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    public PolarisStreamEventCollector(int maxEvents) {
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
//                if (method != null ) {
//                    method.setAccessible(true);
//                    String result = (String) method.invoke(message);
//                    //producer.send(new ProducerRecord<String, String>(topicName, result, data));
//                }else {
//                    //producer.send(new ProducerRecord<String, String>(topicName, data));
//                }
                numberOfEventsProcessed++;
                if(numberOfEventsProcessed % maxLoggingRows == 0){
                    logger.info("Processed " + numberOfEventsProcessed + " events");
                    logger.info("Number of events processed per sec  = " + (maxLoggingRows /(  (System.currentTimeMillis() - startTime ) * 1000))) ;
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
