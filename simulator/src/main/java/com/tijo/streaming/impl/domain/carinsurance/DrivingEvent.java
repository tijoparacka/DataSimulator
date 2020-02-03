package com.tijo.streaming.impl.domain.carinsurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tijo.streaming.impl.domain.Event;

import java.sql.Timestamp;
import java.util.Date;

public class DrivingEvent extends Event {
	@JsonProperty("EventType")
	private DrivingEventTypeEnum eventType;
	@JsonProperty("TruckId")
	private int truckId;
	@JsonProperty("DriverId")
	private int driverId;
	@JsonProperty("Timestamp")
	private Timestamp timestamp;

	public DrivingEvent() {
	}

	public DrivingEvent(DrivingEventTypeEnum eventType, int  truckId,int driverId) {
		this.eventType = eventType;
		this.truckId = truckId;
		this.driverId = driverId;
		this.timestamp= new Timestamp(new Date().getTime());
	}

	public DrivingEventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(DrivingEventTypeEnum eventType) {
		this.eventType = eventType;
	}
	
//	@Override
//	public String toString() {
//		return truck.toString() + eventType.toString();
//	}

}
