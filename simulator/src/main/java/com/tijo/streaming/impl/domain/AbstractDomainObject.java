package com.tijo.streaming.impl.domain;

import java.io.Serializable;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.tijo.DataSimulator;
import org.apache.commons.lang.builder.ToStringBuilder;

import akka.actor.UntypedActor;

import com.tijo.streaming.interfaces.DomainObject;
import org.apache.log4j.Logger;


public abstract class AbstractDomainObject extends UntypedActor implements DomainObject,
		Serializable {
	private static final long serialVersionUID = -2630503054916573455L;
	protected Logger logger =Logger.getLogger(this.getClass().toString());

	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}

//	public String toText() throws JsonProcessingException
//	{
//		return DataSimulator.getWriters().writeValueAsString(this);
//	}
}
