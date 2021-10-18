#!/bin/bash
bin_dir=`dirname $0`
#jarpath = `$bin_dir/../lib/simulator-1.1.0-SNAPSHOT-jar-with-dependencies.jar`
jarpath=".:$bin_dir/../lib/"
#confpath=".:$bin_dir/../conf/"
for file in $bin_dir/../lib/*
do
  jarpath=$jarpath:$file
done
echo "Files in classpath $jarpath"
java -server -Xmx2048m \
-XX:+UseG1GC \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:+ExitOnOutOfMemoryError \
-XX:+PrintGC \
-Xloggc:gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=2M \
-XX:HeapDumpPath=/tmp/druid-heap.hprof -cp "$jarpath" com.tijo.DataSimulator $bin_dir/../ "$@" 2>$1 &
_pid=$!
echo "$_pid" > $bin_dir/../app.pid

