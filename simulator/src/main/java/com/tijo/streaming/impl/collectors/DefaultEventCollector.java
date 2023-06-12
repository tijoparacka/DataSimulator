package com.tijo.streaming.impl.collectors;

import com.tijo.streaming.impl.domain.AbstractDomainObject;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;

import java.util.Date;

public class DefaultEventCollector extends AbstractEventCollector {
	long start = System.currentTimeMillis();

	@Override
	public void onReceive(Object message) throws Exception {
		if( message instanceof Event){
			logger.info(writer.writeValueAsString((Event)message ).replace( "\n",""));
			numberOfEventsProcessed++;
		}
		if (numberOfEventsProcessed % 1000 == 0 ){
			System.out.println(" ########################################################################### ");
			System.out.println(" time taken for 1000 "+ (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();
		}
	}
}
