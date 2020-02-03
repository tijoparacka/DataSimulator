package com.tijo.streaming.impl.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tijo.App;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Event {

	public String toText() throws JsonProcessingException
	{
		return App.getWriter().writeValueAsString(this);
	}

}
