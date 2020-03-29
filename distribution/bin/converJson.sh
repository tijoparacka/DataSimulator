#!/bin/bash
bin_dir=`dirname $0`

jarpath="."
for file in $bin_dir/../lib/*
do
  jarpath=$jarpath:$file
done
echo "Files in classpath $jarpath"
java -Xmx1024m -cp "$jarpath" com.tijo.InferJSON $bin_dir/../ "$@"
