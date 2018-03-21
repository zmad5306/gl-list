# gl-list
Grocery list, list service

## Build with Docker

`gradlew build`
`docker build . --build-arg version=0.0.1-SNAPSHOT -t gl-list:latest`

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