package com.tijo.streaming.impl.collectors;

import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.Message;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.nio.AsyncRequestProducer;
import org.apache.hc.core5.http.nio.entity.AsyncEntityProducers;
import org.apache.hc.core5.http.nio.entity.StringAsyncEntityConsumer;
import org.apache.hc.core5.http.nio.support.AsyncRequestBuilder;
import org.apache.hc.core5.http.nio.support.BasicRequestProducer;
import org.apache.hc.core5.http.nio.support.BasicResponseConsumer;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;

import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import static org.apache.hc.core5.http.Method.POST;


public class PolarisPushAPIAsyncEventCollector extends AbstractEventCollector {

    private String orgName;
    private String token;
    private String connection;

    private CloseableHttpAsyncClient client;
    //private HttpPost httpPost;
    private String requestUri;
    private  AsyncRequestProducer requestProducer ;
    private HttpHost target;

    private ConcurrentHashMap futureHandler = new ConcurrentHashMap();
    public PolarisPushAPIAsyncEventCollector() {
        super();
        try {
            ConfigUtil config = ConfigUtil.getInstance();
            orgName = config.getConfig("sim.polaris.pushapi.orgname");
            token = config.getConfig("sim.polaris.pushapi.token");
            connection = config.getConfig("sim.polaris.pushapi.connection");



            IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                    .setSoTimeout(Timeout.ofSeconds(30))
                    .build();

            client = HttpAsyncClients.custom()
                    .setIOReactorConfig(ioReactorConfig)
                    .build();

            client.start();

            //httpPost = new HttpPost(orgName+".api.imply.io");

            target = new HttpHost(orgName+".api.imply.io");
            requestUri = "/v1/events/"+connection;




        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void onReceive(Object message) throws Exception {
        logger.debug(message);
        if (message instanceof DumpStats) {
            logger.info("Processed " + numberOfEventsProcessed + " events");
            return;
        }else if( message instanceof Event) {
            numberOfEventsProcessed++;
            try {
                String data = writer.writeValueAsString ((Event) message).replace("\n", ""); //TODO whats this doing??
                AsyncRequestBuilder builder = AsyncRequestBuilder.create(POST.name())
                        .setHttpHost(target)
                        .setPath(requestUri)
                        .addHeader(new BasicHeader("Accept","application/json"))
                        .addHeader(new BasicHeader("Authorization","Bearer "+token))
                        .setEntity(AsyncEntityProducers.create(data, ContentType.APPLICATION_JSON));

                requestProducer = builder.build();

                //System.out.println("Executing POST request to " + requestUri + " at time :"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
                if( numberOfEventsProcessed % 10000 ==0){
                    System.out.println( new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()) +"  >" +numberOfEventsProcessed);
                }

                final Future<Message<HttpResponse, String>> future = client.execute(
                        requestProducer,
                        new BasicResponseConsumer<String>(new StringAsyncEntityConsumer()),
                        new FutureCallback<Message<HttpResponse, String>>() {

                            @Override
                            public void completed(final Message<HttpResponse, String> message) {
                                int  responseCode = message.getHead().getCode();
                               // System.out.println(Thread.currentThread().getName() + "->" + responseCode + "at time "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
                               // System.out.println(message.getBody());

                                if(responseCode > 200 && responseCode < 300) {
                                    logger.info(Thread.currentThread().getName() +" -> "+ responseCode + " event "+data);
                                } else if(responseCode != 200) {
                                    logger.error("response code " + responseCode + " event "+data);
                                }
                                String  name = this.toString();
                                futureHandler.remove(name);
                            }

                            @Override
                            public void failed(final Exception ex) {
                                System.out.println(requestUri + "->" + ex);
                                String  name = this.toString();
                                futureHandler.remove(name);
                            }

                            @Override
                            public void cancelled() {
                                System.out.println(requestUri + " cancelled");
                                String  name = this.toString();
                                futureHandler.remove(name);
                            }

                        }
                        );
                //System.out.println(requestUri + " before get "+numberOfEventsProcessed);
               // futureHandler.put(future.toString(), future);


            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
            }


        }
    }
}