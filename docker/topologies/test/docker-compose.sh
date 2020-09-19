#!/bin/bash
#
# docker-compose.sh - build and run docker image with correpsonding war file for deployment
# usage: docker-compose.sh
#
# 
#

# building war file
cd ../../../
mvn clean install
if [[ $? -ne 0 ]]; then
    echo "Error during maven clean install, $0"
    exit 1
else
    echo "run successful, bye !"
fi
cd docker/topologies/test/

# paths
#PATH_WAR_FILE="../../../src/main/target/starkOverFlow.war"                     # to be completed
PATH_IMAGE_DIRECTORY="../../images/wildfly/"


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