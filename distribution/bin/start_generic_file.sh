#!/bin/bash
bin_dir=`dirname $0`
$bin_dir/start.sh 10 -1 com.tijo.streaming.impl.domain.generic.GenericEventGenerator  com.tijo.streaming.impl.collectors.FileEventCollector com.tijo.streaming.impl.domain.generic.AdvImpressionEvent json $bin_dir/../conf/config.properties
