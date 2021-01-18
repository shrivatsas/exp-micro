## Docker debugging

1. Debug a crashing container
    docker run -it --entrypoint="" <container-id> /bin/bash

2. List all available docker
    docker network ls

3. Get ID and Name of all running containers
    docker ps --format 'table {{.ID}}\t{{.Names}}'

4. Get IPAddress of given container in specified docker-network
    docker inspect <container-name> --format '{{.NetworkSettings.Networks.<docker-network>.IPAddress}}'

5. Delete docker container
    docker ps -a | grep <container-name> | awk '{print $1}' | xargs docker rm

6. Delete docker image
    docker images | grep <image-name> | awk '{print $3}' | xargs docker rmi

## Consul

1. Registration (internal)
    curl --request PUT --data @<consul-service-json> localhost:8500/v1/agent/service/register

2. Registration (external)
    

3. Deregister (internal)
    curl -X PUT http://localhost:8500/v1/agent/service/deregister/<service-id>
