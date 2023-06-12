package com.tijo.streaming.impl.domain;

import com.tijo.streaming.impl.domain.generic.GenericEvent;

public abstract class AbstractEventEmitter extends AbstractDomainObject {
	private static final long serialVersionUID = 3553392748138862662L;

	public AbstractEventEmitter() {

	}

	public abstract GenericEvent[] generateEvent() throws Exception;
}