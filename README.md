<<<<<<< HEAD
# gl-list
Grocery list, list service

## Build with Docker

`gradlew build`

`docker build . --build-arg version=0.0.1-SNAPSHOT -t gl-list:latest`

## Deploy with Kubernetes

`kubectl apply -f k8s/deployment.yml`

`kubectl apply -f k8s/service.yml`

# API

| URL                              | Method | Description                      |
| -------------------------------- | ------ | -------------------------------- |
| /api/list/                       |GET     | Gets all lists                   |
| /api/list/{listId}               |GET     | Gets a single list by id         |
| /api/list/                       |POST    | Adds a new list                  |
| /api/list/{listId}               |PUT     | Updates a list                   |
| /api/list/{listId}               |DELETE  | Deletes a list                   |
| /api/list/{listId}/item          |GET     | Gets items in the list           |
| /api/list/{listId}/item/{itemId} |GET     | Gets a single item from the list |
| /api/list/{listId}/item          |POST    | Adds a new item to the list      |
| /api/list/{listId}/item/{itemId} |PUT     | Updates an item in the list      |
| /api/list/{listId}/item/{itemId} |DELETE  | Deletes an item from the list    |

test
=======
# gl-list
Grocery list, list service

## Build with Docker

`docker build . --build-arg version=0.0.1-SNAPSHOT -t zmad5306/gl-list:latest`

`docker push zmad5306/gl-list:latest`

## Circle CI Build

https://circleci.com/gh/zmad5306/gl-list

# API

| URL                                    | Method | Description                                    |
| -------------------------------------- | ------ | ---------------------------------------------- |
| /api/list/                             |GET     | Gets all lists                                 |
| /api/list/{listId}                     |GET     | Gets a single list by id                       |
| /api/list/                             |POST    | Adds a new list                                |
| /api/list/{listId}                     |PUT     | Updates a list                                 |
| /api/list/{listId}                     |DELETE  | Deletes a list                                 |


## GET /api/list/

### Sample response

```json
[
    {
        "listId": "5ab4da845f15030001942891",
        "name": "my second list",
        "username": "sue",
        "links": [
            {
                "rel": "self",
                "href": "http://minikube:31726/api/list/5ab4da845f15030001942891",
                "hreflang": null,
                "media": null,
                "title": null,
                "type": null,
                "deprecation": null
            },
            {
                "rel": "self",
                "href": "/api/item/5ab4da845f15030001942891",
                "hreflang": null,
                "media": null,
                "title": "items",
                "type": null,
                "deprecation": null
            }
        ]
    }
]
```

## GET /api/list/{listId}

### Sample response

```json
{
    "listId": "5ab4da845f15030001942891",
    "name": "my second list",
    "username": "sue",
    "_links": {
        "self": [
            {
                "href": "http://minikube:31726/api/list/5ab4da845f15030001942891"
            },
            {
                "href": "/api/items/5ab4da845f15030001942891",
                "title": "items"
            }
        ]
    }
}
```

## POST /api/list/

### Sample request

```json
{
	"name": "my list"
}
```

### Sample response

```json
{
    "listId": "5ab4e1b45f15030001942893",
    "name": "my list",
    "username": "sue",
    "_links": {
        "self": [
            {
                "href": "http://minikube:31726/api/list/5ab4e1b45f15030001942893"
            },
            {
                "href": "/api/items/5ab4e1b45f15030001942893",
                "title": "items"
            }
        ]
    }
}
```

## PUT /api/list/{listId}

### Sample request

```json
{
    "name": "my cool list"
}
``` 

### Sample response

```json
```

## DELETE /api/list/{listId} 

### Sample response

```json
```

