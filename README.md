# Discussify

## Introduction

A Spring Boot JPA application that demonstrates how to work with JSON types in SQL. Databases such as Yugabyte support JSON / JSONB data types, which require different techniques than standard data types (varchar, integer, etc.).

## Prerequisites

* A [YugabyteDB](https://docs.yugabyte.com/preview/quick-start/) instance
* JDK 17
* [Maven 3.9](https://maven.apache.org/download.cgi)

## Building and running

This is a standard Spring Boot application. You can build and run it using Maven. First, an environment variable called `YB_URL` with the JDBC URL for your Yugabyte instance. Alternatively, set `YB_USERNAME` and `YB_PASSWORD` with the username and password for your Yugabyte instance.

```bash
export YB_URL=jdbc:yugabytedb://localhost:5433/yugabyte?load-balance=true
export YB_USERNAME=yugabyte
export YB_PASSWORD=yugabyte
```

You can also set these properties directly in the [`application.properties`](src/main/resources/application.properties) file.

Once these are set, you can use Maven to build and run the application.

```bash
mvn clean package spring-boot:run
```

You can then open the home page at [http://localhost:8080](http://localhost:8080). The comments for posts are stored as JSON types. Add a post and a few comments, and then query the database.

