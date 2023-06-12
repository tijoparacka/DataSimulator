package com.tijo.streaming.impl.collectors;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.kinesis.producer.Attempt;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import com.amazonaws.services.kinesis.producer.UserRecordFailedException;
import com.amazonaws.services.kinesis.producer.UserRecordResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


public class KinesisEventCollector extends AbstractEventCollector
{
  private static final String AWS_REGION ="sim.kinesis.region" ;
  private static final String MAX_CONNECTIONS ="sim.kinesis.maxConnection" ;
  private static final String MAX_BUFFER_TIME ="sim.kinesis.maxBufferTime" ;
  private static final String THREADING_MODEL = "sim.kinesis.threadingModel";
  private static final String  THREAD_POOL_SIZE = "sim.kinesis.threadPoolSize";
  private static final String AGGREGATION_ENABLED = "sim.kinesis.aggregation.enabled";
  private static final String AGGREGATION_MAX_COUNT = "sim.kinesis.aggregation.maxCount";
  private static final String AGGREGATION_MAX_SIZE = "sim.kinesis.aggregation.maxSize";
  private static final Random RANDOM = new Random();
  private static final String REQUEST_TIMEOUT ="sim.kinesis.requestTimeout" ;
  private  String streamName ="" ;
  private  KinesisProducer producer = null;
  //private Logger logger;
  private FutureCallback<UserRecordResult> callback =null;
  private ExecutorService callbackThreadPool = null;
  private ThreadLocal<Integer> kinesisSuccessCount = new ThreadLocal<Integer>();
  public KinesisEventCollector() {
    super();

    try {

      ConfigUtil config = ConfigUtil.getInstance();
      streamName = config.getConfig("sim.kinesis.streamName");
      KinesisProducerConfiguration awsConfig = new KinesisProducerConfiguration();
      String region = (config.getConfig(AWS_REGION) != null) ? config.getConfig(AWS_REGION) : "us-east-1";
      long maxConnection =  config.getConfig(MAX_CONNECTIONS) != null ? Long.parseLong(config.getConfig(MAX_CONNECTIONS)):1;
      long requestTimeOut = config.getConfig(REQUEST_TIMEOUT) != null ? Long.parseLong(config.getConfig(REQUEST_TIMEOUT)):60;
      long maxBufferTime = config.getConfig(MAX_BUFFER_TIME) != null ? Long.parseLong(config.getConfig(MAX_BUFFER_TIME)) : 60;
      String threadingModel = config.getConfig(THREADING_MODEL) != null ? config.getConfig(THREADING_MODEL) : "PER_REQUEST" ;
      int threadPoolSize = config.getConfig(THREAD_POOL_SIZE) != null ? Integer.parseInt(config.getConfig(THREAD_POOL_SIZE)):1;
      boolean aggregationEnabled  = config.getConfig(AGGREGATION_ENABLED) != null
                                    && Boolean.parseBoolean(config.getConfig(AGGREGATION_ENABLED));
      long aggregationMaxCount = config.getConfig(AGGREGATION_MAX_COUNT) != null ? Long.parseLong(config.getConfig(AGGREGATION_MAX_COUNT)) : 100;
      long aggregationMaxSize = config.getConfig(AGGREGATION_MAX_SIZE) != null ? Long.parseLong(config.getConfig(AGGREGATION_MAX_SIZE)) : 10;

      awsConfig.setRegion(region);
      awsConfig.setCredentialsProvider(new DefaultAWSCredentialsProviderChain());

      awsConfig.setMaxConnections(maxConnection);

      awsConfig.setRequestTimeout(requestTimeOut);

      awsConfig.setRecordMaxBufferedTime(maxBufferTime);

      awsConfig.setThreadingModel(threadingModel);
      awsConfig.setThreadPoolSize(threadPoolSize);

      awsConfig.setAggregationEnabled(aggregationEnabled);
      awsConfig.setAggregationMaxCount(aggregationMaxCount);
      awsConfig.setAggregationMaxSize(aggregationMaxSize);

      producer = new KinesisProducer(awsConfig);
      kinesisSuccessCount.set(0);

      // The monotonically increasing sequence number we will put in the data of each record
      final AtomicLong sequenceNumber = new AtomicLong(0);

      // The number of records that have finished (either successfully put, or failed)
      final AtomicLong completed = new AtomicLong(0);

      // KinesisProducer.addUserRecord is asynchronous. A callback can be used to receive the results.
      callback = new FutureCallback<UserRecordResult>() {
        @Override
        public void onFailure(Throwable t) {
          // If we see any failures, we will log them.
          int attempts = ((UserRecordFailedException) t).getResult().getAttempts().size()-1;
          Attempt last = ((UserRecordFailedException) t).getResult().getAttempts().get(attempts);
          if(attempts > 1) {
            Attempt previous = ((UserRecordFailedException) t).getResult().getAttempts().get(attempts - 1);
            logger.error(String.format(
                "Record failed to put - %s : %s. Previous failure - %s : %s",
                last.getErrorCode(), last.getErrorMessage(), previous.getErrorCode(), previous.getErrorMessage()));
          }else{
            logger.error(String.format(
                "Record failed to put - %s : %s.",
                last.getErrorCode(), last.getErrorMessage()));
          }
          logger.error("Exception during put", t);
        }

        @Override
        public void onSuccess(UserRecordResult result) {
         completed.getAndIncrement();
         if(completed.get() %1000 ==0 ){
           logger.info("Successfully inserted 1000 records.");
         }
        }
      };

     callbackThreadPool = Executors.newCachedThreadPool();
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage(), e);
    }
  }

  public KinesisEventCollector(int maxEvents) {
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
            //ByteBuffer data = Utils.generateData(sequenceNumber.get(), config.getDataSize());
            // TIMESTAMP is our partition key
            ListenableFuture<UserRecordResult> f =null;
            try {
              f = producer.addUserRecord(streamName, Long.toString(System.nanoTime()),
                                         new BigInteger(128, RANDOM).toString(10),
                                         ByteBuffer.wrap((writer.writeValueAsString((Event) message)).replace("\n", "").getBytes())
              );
            }
            catch (JsonProcessingException e) {
              logger.error(e);
              logger.error("Error while insert data to Kinesis "+e.getMessage());
            }
            assert callback != null;
            Futures.addCallback(f, callback, callbackThreadPool);



            numberOfEventsProcessed++;
            if(numberOfEventsProcessed %1000 ==0) {
              //producer.flush();
              logger.info("Produced 1000 event ");
            }
      }
      catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
  }
}
