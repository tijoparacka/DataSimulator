package com.tijo.streaming.impl.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tijo.DataSimulator;

public class Event {

	public String toText() throws JsonProcessingException
	{
		return DataSimulator.getWriter().writeValueAsString(this);
	}

}
