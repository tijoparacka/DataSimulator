#!/bin/bash
bin_dir=`dirname $0`
java  -cp $bin_dir/../lib/simulator-1.1.0-SNAPSHOT-jar-with-dependencies.jar com.tijo.CardinalityGenerator $bin_dir/conf/config.properties

