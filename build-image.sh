#!/usr/bin/bash -e

mvn clean package
docker build -t digitalpatate/starkoverflow .