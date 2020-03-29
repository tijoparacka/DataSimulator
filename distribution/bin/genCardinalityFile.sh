#!/bin/bash
bin_dir=`dirname $0`

jarpath="."
for file in $bin_dir/../lib/*
do
  jarpath=$jarpath:$file
done
echo "Files in classpath $jarpath"
java  -cp "$jarpath" com.tijo.CardinalityGenerator $bin_dir/../ "$@"

