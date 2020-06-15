#!/bin/bash
bin_dir=`dirname $0`
$bin_dir/start.sh 1 10  com.tijo.streaming.impl.domain.generic.GenericEventGenerator com.tijo.streaming.impl.collectors.FileEventCollector,com.tijo.streaming.impl.collectors.DatabaseEventCollector json,csv  "$@"
