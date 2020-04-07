Data Simulator 
==============
The goal of this project is to create a very lightweight framework to quickly generate data for demo purposes.  The framework uses Akka to simplify concurrency and messaging, and is simple to extend.

This project provides a generic simulation framework which is capable for simulating data based on configurations.  This simulator have capbilities to display the simulated data in console for verifying if the simulated data is correct or not . 
At present this have implementation for pushing the simulated data to kafka or  to a file. You are welcome to contribute to create collectors which can push the data to other sources like kafka or blob storages. 

Prerequisites
-------------


You need to have jdk 1.8 installed in the machine where you would like to run the simulator. Please note jre is not enough for executing all the features provided by this simulator. Refer the [link](https://openjdk.java.net/install/) on how  to install jdk in your machine   


For pushing the data to kafka you need to have a  kafka cluster and broker nodes and zookeeper should be accessible from 
the node where you are running the simulator. 

Building From Source
------------------ 

1. Setup maven to build binaries from the source. [maven download](https://maven.apache.org/download.cgi)
2. Download the source code from github. [DataSimulator](https://github.com/tijoparacka/DataSimulator)
3. Execute the following maven command to build the binaries. 
     `` mvn package -DskipTests``
4. The binaries are created with in the distribution/target/data-simulator-1.1.1-bin.zip
5. Unzip this binary to get started . 

Downloading from s3 location
---------------------------- 
Imply employees can download the binaries from s3 location: s3://imply-ps-tools/datasimulator/ 

Running the simulator
-------------------- 
Generic simulator have the capbility to 
1. infer the schema from a json file and generate the data based on that. 
2. This has the capability to generate a required cardinality based on the metadata mentioned in the config.properties file .
See the below section to to get more details on how to configure the metadata to generate the required cardinality 
3. Generate the data based on the data type and condition required. 

Config Properties
-----------------
 
 |Property | Details |Default |
 |---------|---------|--------|
 |sim.kafka.bootstrap.servers|Kafka bootstrap server ip/hostname with port no eg:``localhost:9092``|This parameter is considered only if the Collector is ``com.tijo.streaming.impl.collectors.UnsecuredKafkaEventCollector`` mentioned in the start script|
 |sim.kafka.topicName| The data will be pushed to the topic mentioned |This parameter is considered only if the Collector is ``com.tijo.streaming.impl.collectors.UnsecuredKafkaEventCollector`` mentioned in the start script|
 |sim.generic.infer.packageName| Used by inferjson and refering to java package naming.| none|
 |sim.generic.infer.jsonPath| Name of the sample json file used for infering the schema | The infered java code will be created with the same name as json file name , excluding the extension|
 |sim.generic.infer.generateCode|True if json to java  code generation is required.  This can be set to false if any modification is done in the java code generated in earlier tries.|true|
 |sim.generic.infer.pojoOutputDir|Folder path for generating the java code corresponding to the sample json|  ``pojoOutput``|
 |sim.cardinality.generator.folder| If the metadata configuration is to use fixed no of cardinatiles , this will be generated and saved as file in this folder. |none|
 |sim.file.maxrows|If the collector is to save the data to file , then this property is limit the no of records in the file |none|
 |sim.file.outputdir|The folder in which the generated files are saved|none|
 |sim.file.fileNameLength| random file names are generated for storing the files in disk. The length of the file name is controlled with this |none|
 |sim.generic.eventClass| Name of the class for the json file . If infer json is used to generate the java class, same  class name need to be mentioned here.|none| 
 |sim.generic.metadata| Refer the below metata data section |none|
 
 ## Generic Metadata Config
 
 | Type| Details | Options |
 |-----|---------|---------|
 |`FIXED`| Used to create random data from a fixed  list of values.| 1. "file":name of the file where the list of data is saved. 2. "limit" :the size of the data ( this is used when gencardinality.sh is used to create the list of random data) 3. "cardinality": cardinality of the data|
 |`STRING`| Used to geneate random  String | 1. "limit" : the length of the string to be generated|
 |`INT`| Used for creating random integer value | 1. "limit" : Max value of the Integer to be generated. This should not be more than Integer.MAX_VALUE.|
 |`LONG`| Used for creating random Long value | 1. "limit" : Max value of the Long to be generated. This should not be more than Long.MAX_VALUE.|
 |`DOUBLE`|  Used for creating random Double value | 1. "limit" : Max value of the Double  to be generated. This should not be more than Double.MAX_VALUE.|
 |`DATE_RANGE`| Used to generate date from a start time  to end time .|1. "start" : start time  considering for simulation,   2. "end": end time in date format mentioned . eg : {"dimension" : "start_time" ,"type":"DATE","start":"2010-06-01 00:00:00","end":"2020-06-01 00:00:00","limit":10000,"format":"yyyy-MM-dd mm:HH:ss"}|
 |`DATE`| Generate the current time at which the simulation happens.| With the option limit you can give a variance to simulate the late arrival of data|
 
  
 Please refer the example configs to write a your own metadata.
 
 This simulator can approximately generate 50,000 data points in a 8 core machine with high speed Hard disk.  There is no limit to generate data to push the data to kafka by running the simulator to multiple machine. 
 
 
 If you want to simulate specific scenarios , please refer the advance guide. [advance simulation](simulator/README.md) 
 





 