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
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.messages.StartSimulation;
import com.tijo.streaming.impl.messages.StopSimulation;
import com.tijo.streaming.listeners.SimulatorListener;
import com.tijo.streaming.masters.SimulationMaster;

import java.util.ArrayList;
import java.util.HashMap;

public class DataSimulator
{
	private static ArrayList<ObjectWriter> writers= new ArrayList<ObjectWriter>();
	private static String configFilePath;

	public static ArrayList<ObjectWriter>  getWriters(){
		return writers;
	}
	public static String getConfigFilePath(){return configFilePath;}
	private static  Class[] eventClasses ;

	public static long getNumberOfEvents()
	{
		return numberOfEvents;
	}

	private static long  numberOfEvents;
	//mapping of collectors and its corrrespoinging writer.
	private static HashMap<String , ObjectWriter> collectorWriter = new HashMap<String,ObjectWriter>();
	public static Class[] getEventClass()
	{
		return eventClasses;
	}

	private static Class[] eventCollectorClasses;

	public static Class[] getEventCollectorClasses()
	{
		return eventCollectorClasses;
	}

	;

	public static void main(String[] args) throws Exception
	{
		if (args != null && args.length == 7) {
			String baseDir = args[0];
			try {
				final int numberOfEventEmitters = Integer.parseInt(args[1]);
				numberOfEvents = Integer.parseInt(args[2]);

				final Class[] eventEmitterClasses = getClassObj(args[3]);
				eventCollectorClasses = getClassObj(args[4]);

				//eventClass =
				configFilePath=args[6];
				ConfigUtil conf = new ConfigUtil(configFilePath);
				String eventClassName = conf.getConfig("sim.generic.eventClass");
				eventClasses =  getClassObj(eventClassName);
				String mapperInput = args[5];
				String[] mappers = mapperInput.split(",");
				for (int i = 0; i < mappers.length ; i++) {
					ObjectWriter writer ;
					if(mappers[i].equalsIgnoreCase("csv")){
						CsvMapper csvMapper = new CsvMapper();
						CsvSchema csvSchema = csvMapper.schemaFor(eventClasses[i]);
						writer = csvMapper.writer(csvSchema );

					}else if (mappers[i].equalsIgnoreCase("json") ){
						ObjectMapper mapper = new ObjectMapper();
						writer = mapper.writer();
					}else {
						throw new Exception(" Unrecognized data format type. Currently only csv and json are supported");
					}
					writers.add(writer);
				}


				ActorSystem system = ActorSystem.create("EventSimulator");
				final ActorRef listener = system.actorOf(
						Props.create(SimulatorListener.class), "listener");

				for (int i = 0; i <eventCollectorClasses.length ; i++) {
					final ActorRef eventCollector = system.actorOf(
							Props.create(eventCollectorClasses[i]), eventCollectorClasses[i].getSimpleName());
					collectorWriter.put(eventCollectorClasses[i].getSimpleName(), writers.get(i));
				}
				final ActorRef master = system.actorOf(new Props(
						new UntypedActorFactory() {
							public UntypedActor create() {
								return new SimulationMaster(
										numberOfEventEmitters,
										eventEmitterClasses[0], listener);
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

	private static Class[] getClassObj(String className) throws ClassNotFoundException
	{
		String[] classNames = className.split(",");
		Class[] classes = new Class[classNames.length];
		for (int i = 0; i < classes.length ; i++) {
			classes[i] = Class.forName(classNames[i]);
		}
		return classes;
	}

	public static ObjectWriter getWriter(String collectorClassName)
	{
		return collectorWriter.get(collectorClassName);
	}
}
