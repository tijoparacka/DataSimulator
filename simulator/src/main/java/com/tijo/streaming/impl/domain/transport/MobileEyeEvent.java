package com.tijo.streaming.impl.domain.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.domain.gps.Location;

import java.sql.Timestamp;
import java.util.Date;


public class MobileEyeEvent extends Event {
	@JsonProperty("EventType")
	private MobileEyeEventTypeEnum eventType;
	@JsonProperty("Latitude")
	private double latitude;
	@JsonProperty("Longitude")
	private double longitude;
	@JsonProperty("TruckId")
	private int truckId;
	@JsonProperty("DriverId")
	private int driverId;
	@JsonProperty("TimeStamp")
	private Timestamp timestamp;
	/*
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public void setTruckId(int truckId)
	{
		this.truckId = truckId;
	}

	public void setDriverId(int driverId)
	{
		this.driverId = driverId;
	}

	public void setTimestamp(Timestamp timestamp)
	{
		this.timestamp = timestamp;
	}

	public void setEventType(MobileEyeEventTypeEnum eventType)
	{
		this.eventType = eventType;
	}

*/


	public MobileEyeEvent(Location location, MobileEyeEventTypeEnum eventType,Timestamp timestamp,int truckId, int driverId) {
		this.longitude = location.getLongitude();
		this.latitude = location.getLatitude();
		this.eventType = eventType;
		this.truckId = truckId;
		this.driverId =driverId;
		this.timestamp = timestamp;

	}

	public MobileEyeEventTypeEnum getEventType() {
		return eventType;
	}

	//public void setEventType(MobileEyeEventTypeEnum eventType) {
	//	this.eventType = eventType;
//	}

	/*
	@Override
	public String toString() {
		return truck.toString() + eventType.toString() + ","
				+ location.getLatitude() + "," + location.getLongitude();
	}
*/

}
