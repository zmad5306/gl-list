# gl-list
Grocery list, list service

## Build with Docker

`gradlew build`

`docker build . --build-arg version=0.0.1-SNAPSHOT -t zmad5306/gl-list:latest`

`docker push zmad5306/gl-list:latest`

## Deploy with Kubernetes

`kubectl apply -f k8s/deployment.yml`

`kubectl apply -f k8s/service.yml`

# API

| URL                                    | Method | Description                                    |
| -------------------------------------- | ------ | ---------------------------------------------- |
| /api/list/                             |GET     | Gets all lists                                 |
| /api/list/{listId}                     |GET     | Gets a single list by id                       |
| /api/list/                             |POST    | Adds a new list                                |
| /api/list/{listId}                     |PUT     | Updates a list                                 |
| /api/list/{listId}                     |DELETE  | Deletes a list                                 |