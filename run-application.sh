#!/usr/bin/env bash
set -e

docker-compose pull
docker-compose up -d

docker image prune -a -f
