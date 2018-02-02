#!/bin/sh

#dbIP
IP=""
#dbPORT
PORT=""
#dbUser
DBUSER=""
#dbPass
PASSWD=""
#dbName
DBNAME="depot"

#connect to database then execute init
cat depot_tables.list | mysql --user=$DBUSER --password=$PASSWD --host=$IP --database=$DBNAME --port=$PORT --default-character-set=utf8;

exit