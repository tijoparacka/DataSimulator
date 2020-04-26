#!/bin/bash
bin_dir=`dirname $0`
kill -9 `cat $bin_dir/../app.pid`