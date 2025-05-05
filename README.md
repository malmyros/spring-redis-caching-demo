# Spring Redis Caching

This demo was created to investigate the capabilities
of caching data with spring boot and Redis to reduce
I/O in PostgreSQL and to improve performance.

## Setup

To start the application we first need to have docker
and Postgres and Redis running. 


### Docker
To do this we need to run the following 
command in the root directory:

```
docker-compose up
```

After running the command we should be able to see all
containers up and running. 

To stop our containers from running we can run the 
following command:

```
docker-compose down --volumes
```

This would also get rid off any residual data to ensure
we test from scratch.

### Application

Finally we can start the application in:

```
src/main/java/com/example/demo/Application.java
```

Once the application is up and running we can use
Postman to test the functionality. A collection is
already created for this repository here [Spring Redis Cache.postman_collection.json](./docs)

## Postman

The postman collection contains a set of requests for issuing
Debit Virtual or Physical Cards. We can use the Order card endpoint
and then the get card endpoint to see the solution in action.

## Redis

Once we have issued a debit card we can log in to the Redis
container and use redis cli to view the keys. To achieve this
we can run the following commands:

```
// Connect to the redis docker container
docker run --name redis-1 bash

// Connect to redis with redis cli
redis-cli -u redis://localhost:6379

// List all redis keys
KEYS *

// Delete a key
DEL key cards::707292847096071138
```
