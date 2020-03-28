#!/bin/bash
bin_dir=`dirname $0`
$bin_dir/start.sh 10 -1  com.tijo.streaming.impl.domain.transport.Truck com.tijo.streaming.impl.collectors.UnsecuredKafkaEventCollector com.tijo.streaming.impl.domain.transport.MobileEyeEvent csv "$@"

