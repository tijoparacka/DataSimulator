package com.tijo.streaming.impl.collectors;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
//import com.amazonaws.services.kinesis.producer.Attempt;
//import com.amazonaws.services.kinesis.producer.KinesisProducer;
//import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
//import com.amazonaws.services.kinesis.producer.UserRecordFailedException;
//import com.amazonaws.services.kinesis.producer.UserRecordResult;
//import com.amazonaws.services.kinesis.model.PutRecordsRequest;
//import com.google.common.util.concurrent.FutureCallback;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;
import org.apache.commons.codec.digest.MurmurHash3;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


public class KinesisEventCollector2 extends AbstractEventCollector
{
  private static final String AWS_REGION ="sim.kinesis.region" ;
  private static final String MAX_CONNECTIONS ="sim.kinesis.maxConnection" ;
  private static final String MAX_BUFFER_TIME ="sim.kinesis.maxBufferTime" ;
  private static final String THREADING_MODEL = "sim.kinesis.threadingModel";
  private static final String THREAD_POOL_SIZE = "sim.kinesis.threadPoolSize";
  private static final String BATCH_SIZE = "sim.kinesis.batchSize";
  private static final String AGGREGATION_ENABLED = "sim.kinesis.aggregation.enabled";
  private static final String AGGREGATION_MAX_COUNT = "sim.kinesis.aggregation.maxCount";
  private static final String AGGREGATION_MAX_SIZE = "sim.kinesis.aggregation.maxSize";
  private static final Random RANDOM = new Random();
  private static final String REQUEST_TIMEOUT ="sim.kinesis.requestTimeout" ;
  private  String streamName ="" ;
  private AmazonKinesis kinesisClient = null;
  private PutRecordsRequest putRecordsRequest = null;
  private  ArrayList<PutRecordsRequestEntry> putRecordsRequestEntryList  = new ArrayList<PutRecordsRequestEntry>();
  private AtomicLong sequenceNumber;
  private  int batchSize =0;
  public KinesisEventCollector2() {
    super();

    try {

      ConfigUtil config = ConfigUtil.getInstance();
      streamName = config.getConfig("sim.kinesis.streamName");
      String region = (config.getConfig(AWS_REGION) != null) ? config.getConfig(AWS_REGION) : "us-east-1";
      int maxConnection =  config.getConfig(MAX_CONNECTIONS) != null ? Integer.parseInt(config.getConfig(MAX_CONNECTIONS)):1;
      int requestTimeOut = config.getConfig(REQUEST_TIMEOUT) != null ? Integer.parseInt(config.getConfig(REQUEST_TIMEOUT)):60;
      batchSize = config.getConfig(BATCH_SIZE) != null ? Integer.parseInt(config.getConfig(BATCH_SIZE)):100;
      AmazonKinesisClientBuilder clientBuilder = AmazonKinesisClientBuilder.standard();
      clientBuilder.setRegion(region);
      clientBuilder.setCredentials(new DefaultAWSCredentialsProviderChain());
      //KinesisProducerConfiguration awsConfig = new KinesisProducerConfiguration();
      ClientConfiguration awsConfig = new ClientConfiguration();
      awsConfig.setMaxConnections(maxConnection);
      awsConfig.setRequestTimeout(requestTimeOut);
      awsConfig.setMaxConsecutiveRetriesBeforeThrottling(60);
      awsConfig.setUserAgentPrefix("sim");
      awsConfig.setConnectionTimeout(50 *1000);
      awsConfig.setSocketTimeout(50 *1000);

      clientBuilder.setClientConfiguration(awsConfig);

      kinesisClient = clientBuilder.build();
      putRecordsRequest = new PutRecordsRequest();
      putRecordsRequest.setStreamName(streamName);

      sequenceNumber = new AtomicLong(0);

    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage(), e);
    }
  }

  public KinesisEventCollector2(int maxEvents) throws Exception
  {
    throw new Exception("This should not be used");
  }

  @Override
  public void onReceive(Object message) throws Exception {

    if (message instanceof DumpStats) {
      logger.info("Processed " + numberOfEventsProcessed + " events");
      return;
    }else if( message instanceof Event) {
      try {

        PutRecordsRequestEntry putRecordsRequestEntry  = new PutRecordsRequestEntry();
        byte[] bytes= (writer.writeValueAsString((Event) message)).replace("\n", "").getBytes();
        putRecordsRequestEntry.setData(ByteBuffer.wrap(bytes));
        putRecordsRequestEntry.setPartitionKey(String.format( "sim-%d",MurmurHash3.hash32x86(bytes)));
        putRecordsRequestEntryList.add(putRecordsRequestEntry);

        if(sequenceNumber.addAndGet(1) %  batchSize ==0){
          putRecordsRequest.setRecords(putRecordsRequestEntryList);
          PutRecordsResult putRecordsResult  = kinesisClient.putRecords(putRecordsRequest);
          putRecordsRequestEntryList.clear();

          if(putRecordsResult.getFailedRecordCount() > 0 ){
            logger.error(String.format("Failed to insert %d records", putRecordsResult.getFailedRecordCount()) );
            numberOfEventsProcessed = numberOfEventsProcessed  + batchSize - putRecordsResult.getFailedRecordCount();
          }
        }
        if(numberOfEventsProcessed >= 1000 ) {
          //producer.flush();
          numberOfEventsProcessed = numberOfEventsProcessed - 1000;
          logger.info("Produced 1000 event ");
        }
      }
      catch (Exception e) {
        logger.error(e.getMessage(), e);
        System.exit(0);
      }
    }
  }
}
