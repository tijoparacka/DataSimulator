#!/bin/bash
bin_dir=`dirname $0`
$bin_dir/start.sh 1 -1 com.tijo.streaming.impl.domain.generic.GenericEventGenerator  com.tijo.streaming.impl.collectors.UnsecuredKafkaEventCollector  json $bin_dir/../conf/config.properties
