package com.tijo.streaming.impl.domain.transport;

import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.domain.gps.Location;

public class MobileEyeEvent extends Event {
	private MobileEyeEventTypeEnum eventType;
	private Truck truck;
	private Location location;

	public MobileEyeEvent() {
	}

	public MobileEyeEvent(Location location, MobileEyeEventTypeEnum eventType,
			Truck truck) {
		this.location = location;
		this.eventType = eventType;
		this.truck = truck;
	}

	public MobileEyeEventTypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(MobileEyeEventTypeEnum eventType) {
		this.eventType = eventType;
	}

	public Location getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return truck.toString() + eventType.toString() + ","
				+ location.getLatitude() + "," + location.getLongitude();
	}
}
