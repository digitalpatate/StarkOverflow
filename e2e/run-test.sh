#!/bin/bash


cd src
npm install
sleep 20
npm run e2e-test
cd ..