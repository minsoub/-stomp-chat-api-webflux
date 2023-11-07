#!/bin/bash

export CLASSPATH=$CLASSPATH:.

echo "profile0 => $1"
echo "profile1 => $2"


java -Dspring.profiles.active=$1 -jar -Dserver.port=8080 ./fantoo-app-api-SNAPSHOT.jar