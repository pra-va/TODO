#!/bin/bash
cd BACKEND
mvn clean install -T 1C
cd ../DOCKER
docker-compose build
docker-compose up -d
