#!/bin/bash

pid_file="daemon.pid"
classpath="conf/:lib/*:apps/*"

start() {
	[ -f $pid_file ] && {
		echo "pid[$(cat $pid_file)] is running"
		return 1
	}

	setsid java -classpath "$classpath" org.bcos.depot.Server >> start.out &

	pid=$(jobs -p)
	
	[ -n "$pid" ] || {
		echo "start failed"
		exit 1
	}
	
	echo $pid > daemon.pid
}

stop() {
	[ -f "$pid_file" ] || {
		echo "no daemon running"
		return 0
	}

	kill -9 $(cat "$pid_file")
	rm "$pid_file"
}

case "$1" in
	start)
		start
		;;
	stop)
		stop
		;;
	restart)
		stop
		start
		;;
	*)
		echo "!"
		;;
esac