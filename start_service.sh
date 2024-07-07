#!/usr/bin/env bash
# Database Setup
set -e
echo "Dropping existing databases.."
dropdb -U postgres -h localhost --if-exists socialmedia
echo "Creating base databases"
createdb -U postgres -h localhost  socialmedia

#Build Project
mvn clean install

#Start the service
java -Dspring.config.location=src/main/resources/application.yml  -jar build/libs/oauth-server.jar