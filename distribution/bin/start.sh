#!/bin/bash
bin_dir=`dirname $0`
java -Xmx1024m -cp $bin_dir/../lib/simulator-1.1.0-SNAPSHOT-jar-with-dependencies.jar:$bin_dir/../lib/com.tijo.adv-1.0-SNAPSHOT.jar com.tijo.DataSimulator "$@"
