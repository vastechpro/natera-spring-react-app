# Java bacend + UI Task

## This is a full stack implementation of the New York Times news Feed application. It contains the following components:
### 1. Spring Boot backend service with Kafka and Redis support
### 2. React frontend deployed with Spring Boot using Hilla integration

## Spring Boot Service Initialization
### Spring Initializr was used to generate the initial project setup with Kafka, Redis, and React dependencies
### Project has support for docker. Service dependencies are added to compose.yaml.

## Service Start
### News feed producer is scheduled to run every 5 min and starts pulling the feed at multiples of 5 min in the hour

## How to run
### 1. Run mvn clean install
### 2. Run mvn spring-boot:run

#### This will pull and install Kafka and Redis docker images, start these services, and bring up Spring Boot application on 
#### http://localhost:8080

### Open a browser window (if not already opened by the app) and access http://localhost:8080 to see the News feed
#### You may not see articles right away as feed is pulled at multiples of 5 min marks. Refresh the page after a few minutes to see the news.


