package com.tijo.streaming.impl.collectors;

import com.tijo.streaming.impl.domain.AbstractEventCollector;

public class DefaultEventCollector extends AbstractEventCollector {

	@Override
	public void onReceive(Object message) throws Exception {
		logger.info(message);
		numberOfEventsProcessed++;
	}
}
