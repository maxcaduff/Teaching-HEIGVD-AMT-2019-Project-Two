#!/usr/bin/env bash

cd auth\ api/
mvn clean install
mv target/authApi-1.0.0.jar docker/authApi-1.0.0.jar
cd ../booking\ api/
mvm clean install
mv target/bookingApi-1.0.0.jar docker/bookingApi-1.0.0.jar
cd ../
docker-compose up -d --build
