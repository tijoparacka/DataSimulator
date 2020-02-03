package com.tijo.streaming.listeners;

import akka.actor.UntypedActor;

import com.tijo.streaming.results.SimulationResultsSummary;

public class SimulatorListener extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof SimulationResultsSummary)
			System.out.println(message.toString());
		getContext().system().shutdown();
	}
}
