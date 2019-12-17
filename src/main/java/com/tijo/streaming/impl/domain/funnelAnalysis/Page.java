package com.tijo.streaming.impl.domain.funnelAnalysis;

import akka.actor.ActorRef;
import com.tijo.streaming.impl.domain.AbstractEventEmitter;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.EmitEvent;

import java.util.Random;


public class Page  extends AbstractEventEmitter  {

    PageDetails pageDetails;
    int[] pageConversion = {70,30,10};
    Random rand = new Random();
    String cookie ;
    int index;

    public Event generateEvent() {
        int randomInt = rand.nextInt(100);
        if(randomInt > pageConversion[index] ){
            return null;
        }
        index++;
        return new PageDetails(randomInt,"Page"+index,cookie );
    }

    @Override
    public void onReceive( Object message) throws Exception {
        if (message instanceof EmitEvent) {
            ActorRef actor = this.context().system()
                    .actorFor("akka://EventSimulator/user/eventCollector");

            int sleepOffset = rand.nextInt(200);
            while(true) {
                cookie = randomAlphaNumeric(10);
                index=0;
                for (int i = 0; i < pageConversion.length; i++) {
                    Event event = generateEvent();
                    if (null == event) // use Optional
                        break;
                    actor.tell(event, this.getSelf());
                }
            }
        }
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
