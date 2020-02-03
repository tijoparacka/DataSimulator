package com.tijo.streaming.impl.domain.transport;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import akka.actor.ActorRef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tijo.streaming.impl.domain.AbstractEventEmitter;
import com.tijo.streaming.impl.domain.gps.BackToTheFutureException;
import com.tijo.streaming.impl.domain.gps.Location;
import com.tijo.streaming.impl.domain.gps.Path;
import com.tijo.streaming.impl.domain.gps.TimestampedLocation;
import com.tijo.streaming.impl.messages.EmitEvent;

public class Truck extends AbstractEventEmitter {
	@JsonIgnore
	private static final long serialVersionUID = 9157180698115417087L;
	private Driver driver;
	private int truckId;
	@JsonIgnore
	private int messageCount = 0;
	@JsonIgnore
	private List<MobileEyeEventTypeEnum> eventTypes;
	@JsonIgnore
	private Random rand = new Random();
	@JsonIgnore
	private Path path = new Path();
	@JsonIgnore
	private TimestampedLocation startingPoint = null;

	public Truck() {
		driver = TruckConfiguration.getNextDriver();
		truckId = TruckConfiguration.getNextTruckId();
		eventTypes = Arrays.asList(MobileEyeEventTypeEnum.values());
		if (driver.getStartingPoint() == null)
			startingPoint = TruckConfiguration.getNextStartingPoint();
		else
			startingPoint = driver.getStartingPoint();
		addWayPoint(startingPoint);
	}

	public Truck(Driver driver) {
		this();
		this.driver = driver;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public MobileEyeEvent generateEvent() {
		Location nextLocation = getNextLocationNearExistingLocation(path
				.getFinish().getLocation());
		addWayPoint(new TimestampedLocation(new GregorianCalendar(),
				nextLocation));
		// System.out.println("Truck traveled: " +
		// path.getOverGroundAverageSpeed() + "MPH");
		messageCount++;
		if (messageCount % driver.getRiskFactor() == 0)
			return new MobileEyeEvent(nextLocation, getRandomUnsafeEvent(),new Timestamp(new Date().getTime()),
		truckId,driver.getDriverId());
		else
			return new MobileEyeEvent(nextLocation,
					MobileEyeEventTypeEnum.NORMAL, new Timestamp(new Date().getTime()),
																truckId,driver.getDriverId());
	}

	private Location getNextLocationNearExistingLocation(Location location) {
		Location nextLocation = new Location(location.getLongitude()
				+ Math.abs(Math.random() - 0.7), location.getLatitude()
				+ Math.abs(Math.random() - 0.7), location.getAltitude()
				+ Math.random());
		return nextLocation;
	}

	private MobileEyeEventTypeEnum getRandomUnsafeEvent() {
		return eventTypes.get(rand.nextInt(eventTypes.size() - 1));
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	private void addWayPoint(TimestampedLocation waypoint) {
		try {
			path.addWaypoint(waypoint);
		} catch (BackToTheFutureException e) {
		}
	}

	@Override
	public String toString() {
		return new Timestamp(new Date().getTime()) + "," + truckId + ","
				+ driver.getDriverId() + ",";
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof EmitEvent) {
			ActorRef actor = this.context().system()
					.actorFor("akka://EventSimulator/user/eventCollector");
			Random rand = new Random();
			int sleepOffset = rand.nextInt(200);
			while (true) {
				Thread.sleep(500 + sleepOffset);
				actor.tell(generateEvent(),getSelf());
			}
		}
	}
}
