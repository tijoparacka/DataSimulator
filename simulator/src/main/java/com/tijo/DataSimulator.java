package com.tijo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.tijo.streaming.impl.messages.StartSimulation;
import com.tijo.streaming.impl.messages.StopSimulation;
import com.tijo.streaming.listeners.SimulatorListener;
import com.tijo.streaming.masters.SimulationMaster;

public class DataSimulator
{
	private static ObjectWriter writer;
	private static String configFilePath;

	public static ObjectWriter getWriter(){
		return writer;
	}
	public static String getConfigFilePath(){return configFilePath;}
	private static  Class eventClass ;

	public static int getNumberOfEvents()
	{
		return numberOfEvents;
	}

	private static int numberOfEvents;
	public static Class getEventClass()
	{
		return eventClass;
	}


	public static void main(String[] args) throws Exception
	{
		if (args != null && args.length == 7) {
			try {
				final int numberOfEventEmitters = Integer.parseInt(args[0]);
				numberOfEvents = Integer.parseInt(args[1]);
				final Class eventEmitterClass = Class.forName(args[2]);
				final Class eventCollectorClass = Class.forName(args[3]);
				eventClass = Class.forName(args[4]);
				if(args[5].equalsIgnoreCase("csv")){
					CsvMapper csvMapper = new CsvMapper();
					CsvSchema csvSchema = csvMapper.schemaFor(eventClass);
					writer = csvMapper.writer(csvSchema );
				}else if (args[5].equalsIgnoreCase("json") ){
					ObjectMapper mapper = new ObjectMapper();
					writer = mapper.writer();
				}else {
					throw new Exception(" Unrecognized data format type. Currently only csv and json are supported");
				}
				configFilePath=args[6];
				ActorSystem system = ActorSystem.create("EventSimulator");
				final ActorRef listener = system.actorOf(
						Props.create(SimulatorListener.class), "listener");
				final ActorRef eventCollector = system.actorOf(
						Props.create(eventCollectorClass), "eventCollector");
				System.out.println(eventCollector.path());
				final ActorRef master = system.actorOf(new Props(
						new UntypedActorFactory() {
							public UntypedActor create() {
								return new SimulationMaster(
										numberOfEventEmitters,
										eventEmitterClass, listener);
							}
						}), "master");
				Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
					public void run() {
						master.tell(new StopSimulation(), master);
					}
				}));
				master.tell(new StartSimulation(), master);
			} catch (NumberFormatException e) {
				System.err.println("Invalid number of emitters: "
						+ e.getMessage());
			} catch (ClassNotFoundException e) {
				System.err.println("Cannot find classname: " + e.getMessage());
			}
		} else {
			System.err
					.println("Please specify the number of event emitters and the class that you would like to use.");
		}
	}
}
