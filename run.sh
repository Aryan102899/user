#!/bin/sh

mvn clean install -DskipTests
cp target/user-0.0.1-SNAPSHOT.jar .
docker build -t user-test .
docker run -d -p 8080:8080 --name pccw-user-test user-test