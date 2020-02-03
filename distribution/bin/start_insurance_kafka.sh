#!/bin/bash
bin_dir=`dirname $0`
$bin_dir/start.sh 30 -1  com.tijo.streaming.impl.domain.carinsurance.Car com.tijo.streaming.impl.collectors.UnsecuredKafkaEventCollector com.tijo.streaming.impl.domain.carinsurance.DrivingEvent csv $bin_dir/../conf/config.properties

