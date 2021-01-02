## Docker debugging

1. Debug a crashing container
    docker exec -it --entrypoint="" <container-id> /bin/bash

2. List all available docker
    docker network ls

    docker ps --format 'table {{.ID}}\t{{.Names}}'