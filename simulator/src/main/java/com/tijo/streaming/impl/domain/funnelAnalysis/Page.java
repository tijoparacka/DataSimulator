package com.tijo.streaming.impl.domain.funnelAnalysis;

import akka.actor.ActorRef;
import com.tijo.streaming.impl.domain.AbstractEventEmitter;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.EmitEvent;
import com.tijo.streaming.impl.messages.StopSimulation;

import java.util.Random;


public class Page  extends AbstractEventEmitter  {

    public static final int SESSION_ID_LENGTH = 15;
    PageDetails pageDetails;
    int[] pageConversion = {70,30,10};
    int[][] interestedClicks  = {{60,40,30,20}, {70,20,10,0},{30,20,20,30}};
    Random rand = new Random();
    String cookie ;
    int index;

    @Override
    public  PageDetails generateEvent() {
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
            cookie = randomAlphaNumeric(SESSION_ID_LENGTH);
            index = 0;
            for (int pageId = 0; pageId < pageConversion.length; pageId++) {
                PageDetails pageEvent = generateEvent();
                if (null == pageEvent) // use Optional
                    break;
                for (int clickId = 0; clickId < interestedClicks.length; clickId++) {
                    boolean outSide = rand.nextInt(100) >
                                      interestedClicks[pageId][pageId];
                    if (!outSide) {
                        pageEvent.setClickId(clickId);
                    } else {
                        continue;
                    }
                    actor.tell(pageEvent, this.getSelf());
                    Thread.sleep(1);
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
