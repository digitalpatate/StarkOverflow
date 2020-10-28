#!/usr/bin/env bash
set -e

docker-compose pull
docker-compose up

docker image prune -a -f
