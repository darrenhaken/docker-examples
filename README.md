# docker-examples
Docker examples demonstrating it's capabilities running multiple applications at the same time

## Docker guide

#### 1. Local run

Run Spring Boot locally (via IDE)

#### Show version 1.0 running in Docker
``` bash
docker-compose up
docker ps
```

[Browse to Java app](http://localhost:8080 "Java App")

[Browse to Python app](http://localhost:5000 "Python App")

#### Docker image versioning
Increase the tag to 1.1 in Docker compose
``` bash
docker-compose build
docker-compose up
```
[Browse to localhost:8080](http://localhost:8080 "Spring Boot Homepage")

[Browse to localhost:5000](http://localhost:5000 "Flask Homepage")

### Orchestration

#### Deploy to Swarm
Setup Docker Swarm on master
``` bash
#Get IP of manager
docker-machine ip manager1

# SSH on manager
docker-machine ssh manager1

#Initialise swarm with node as leader
#Will skip this step as I did it ahead of time
docker swarm init --advertise-addr <MANAGER-IP>

#Show nodes connected to swarm
docker node ls
```

Join Swarm on the workers

``` bash

docker-machine ssh worker1

docker swarm join \
    --token SWMTKN-1-5ivfibz1i7mok4mh919aleuh9ksbskok8boj3v4tt4ifzd7g72-bxrfrwg5w842f2jj6d6pyzffu \
    192.168.99.100:2377
```

Deploy stack to swarm

#### Change Docker Daemon 
eval $(docker-machine env manager1)

#### Build cluster on Docker on manager1
docker-compose build

#### Set the docker cli to use manager1
docker-machine env manager1

#### Optional but might be needed
docker-machine regenerate-certs manager1

#### Deploy cluster to manager1
docker stack deploy --compose-file docker-compose.yml helloworld 

#### On the Docker manager
docker service ls

#### Lets scale Web up
docker service scale helloworld_web=8

#### Applying rolling updates
docker service update --image <imagename>:<version> web

#### Let's kill the containers
docker service rm helloworld_redis
docker stop $(docker ps -a -q)

#### Removing services
docker service rm helloworld_flask helloworld_web helloworld_redis

#### Revert docker daemon back to Docker for Mac
eval $(docker-machine env -u)

