#!/bin/bash
bin_dir=`dirname $0`

$bin_dir/start.sh 3000 -1  com.tijo.streaming.impl.domain.funnalAnalysis.Page com.tijo.streaming.impl.collectors.UnsecuredKafkaEventCollector com.tijo.streaming.impl.domain.funnalAnalysis.PageDetails csv $bin_dir/../conf/config.properties

