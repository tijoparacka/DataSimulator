#!/bin/bash
bin_dir=`dirname $0`
kill `cat $bin_dir/../app.pid`