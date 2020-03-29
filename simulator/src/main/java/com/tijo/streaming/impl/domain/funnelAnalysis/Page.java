package com.tijo.streaming.impl.domain.funnelAnalysis;

import akka.actor.ActorRef;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.util.Util;
import com.tijo.streaming.impl.domain.AbstractEventEmitter;
import com.tijo.streaming.impl.messages.EmitEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Page  extends AbstractEventEmitter  {
    @Override
    public Event generateEvent() throws Exception
    {
        return null;
    }

    @Override
    public void onReceive(Object o) throws Exception
    {

    }

/*    public static final int COOKIE_ID_LENGTH = 30;
    PageDetails pageDetails;
    int[] pageConversion = {70,30,10};
    int[][] interestedClicks  = {{60,40,30,20}, {70,20,10,0},{30,20,20,30}};
//    long maxTimelimit = 3600 *1000 ;
    Random rand = new Random();
    int initialNoOfCookies =0;
    String cookie ;
    List<PageState> pageStates = Collections.synchronizedList(new ArrayList<PageState>());

    //int index;

    @Override
    public  PageDetails generateEvent() {
      int randomInt = rand.nextInt(100);
        if(randomInt > pageConversion[index] ){
            return null;
        }
        //index++;
       // return new PageDetails(randomInt,"Page"+index,cookie );
   return  null;
    }


    @Override
    public void onReceive( Object message) throws Exception {
        if (message instanceof EmitEvent) {
            initCookie();
            ActorRef actor = this.context().system()
                                 .actorFor("akka://EventSimulator/user/eventCollector");
           while(true) {
                for(PageState state : pageStates){

                }
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
    }

    private void initCookie()
    {
        for (int i = 0; i < initialNoOfCookies; i++) {
            PageState state = new PageState();
            state.setCookie(Util.randomAlphaNumeric(COOKIE_ID_LENGTH));
            pageStates.add(state);
        }

    }
    private PageState addNewPageState(){
        PageState state = new PageState();
        state.setCookie(Util.randomAlphaNumeric(COOKIE_ID_LENGTH));
        pageStates.add(state);
    }
*/
}
