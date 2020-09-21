#!/bin/bash
#
# docker-compose.sh - build and run docker image with correpsonding war file for deployment
# usage: docker-compose.sh
#
# 
#


# paths
PATH_WAR_FILE="../../../target/StarkOverflow.war"
PATH_IMAGE_DIRECTORY="../../images/wildfly/"


# building war file
cd ../../../
mvn clean install
if [[ $? -ne 0 ]]; then
    echo "Error during maven clean install, $0"
    exit 1
else
    echo "deployment archive ready"
fi
cd docker/topologies/test/


# Copy the war files in the docker image directory
rsync -aq  $PATH_WAR_FILE $PATH_IMAGE_DIRECTORY
if [[ $? -ne 0 ]]; then
    echo "$0 : Error while copying file"
    exit 1
else
    echo "copy successful !"
fi


# docker-compose build and run
docker-compose up --build
if [[ $? -ne 0 ]]; then
    echo "Error docker-compose, $0"
    exit 1
else
    echo "run successful, bye !"
fi