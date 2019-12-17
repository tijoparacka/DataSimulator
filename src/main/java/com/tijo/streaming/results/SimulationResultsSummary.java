package com.tijo.streaming.results;

public class SimulationResultsSummary {
	private int eventEmitters = 0;

	public SimulationResultsSummary(int numberOfMessages) {
		this.eventEmitters = numberOfMessages;
	}

	public String toString() {
		return "System generated " + eventEmitters + " EventEmitters";
	}
}
