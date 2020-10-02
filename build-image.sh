#!/usr/bin/env -S bash -e

mvn clean package
docker build -t digitalpatate/starkoverflow .
