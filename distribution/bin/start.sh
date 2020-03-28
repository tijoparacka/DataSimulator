#!/bin/bash
bin_dir=`dirname $0`
#jarpath = `$bin_dir/../lib/simulator-1.1.0-SNAPSHOT-jar-with-dependencies.jar`
jarpath=".:$bin_dir/../lib/"
for file in $bin_dir/../lib/*
do
  jarpath=$jarpath:$file
done
echo "Files in classpath $jarpath"
java -Xmx1024m -cp "$jarpath" com.tijo.DataSimulator "$@"
