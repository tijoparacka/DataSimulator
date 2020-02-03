package com.tijo.streaming.impl.collectors;

import com.tijo.streaming.impl.domain.AbstractDomainObject;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;

public class DefaultEventCollector extends AbstractEventCollector {

	@Override
	public void onReceive(Object message) throws Exception {
		if( message instanceof Event){
			logger.info(((Event)message ).toText().replace( "\n",""));
			numberOfEventsProcessed++;
		}
	}
}
