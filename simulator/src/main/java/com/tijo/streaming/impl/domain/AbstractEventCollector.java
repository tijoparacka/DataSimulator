package com.tijo.streaming.impl.domain;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.tijo.DataSimulator;
import org.apache.log4j.Logger;

import akka.actor.UntypedActor;

public abstract class AbstractEventCollector extends UntypedActor {
	protected int maxNumberOfEvents = -1;
	protected Logger logger = Logger.getLogger(this.getClass());
	protected long numberOfEventsProcessed = 0;
	protected  ObjectWriter writer;

	public AbstractEventCollector() {
		writer = DataSimulator.getWriter(this.getClass().getSimpleName());
	}

	public AbstractEventCollector(int maxNumberOfEvents) {
		this();
		this.maxNumberOfEvents = maxNumberOfEvents;
	}
}
