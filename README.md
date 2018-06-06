# gl-list
Grocery list, list service

## Build with Docker

`docker build . --build-arg version=0.0.1-SNAPSHOT -t zmad5306/gl-list:latest`

`docker push zmad5306/gl-list:latest`

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

## Continious Integration Builds

### GitHub Integration

#### Storage Bucket

A storage bucket is required to store an encrypted GitHub OAuth authenticaion token. This is utilzed by the builds to authenticate to GitHub to push changes, create branches and create pull requests. The name of the storage bucket is passed to the builds as Substitution Variables (see _GIT_HUB_KEY_BUCKET_NAME below).

#### Cryptographic Keys

In the Google Cloud Platform Console there must be a Cryptographic Key-Ring and Key created. The name of the key-ring and key are passed to the builds (see _KMS_KEY and _KMS_KEYRING below).

In addition to creating the keys a GitHub access token must be encrypted in a hub.enc and uploaded to the storage bucked created above.

Create a `hub` file in the following format:

```yaml
github.com:
  - protocol: https
    user: ${GITHUB_USERNAME}
    oauth_token: <git hub token here>
```

See Creating a personal access token for the command line for more details: https://help.github.com/articles/creating-a-personal-access-token-for-the-command-line/

Once the `hub` file is created it needs encrypted using the gcloud API:

`gcloud kms encrypt --location=global --keyring=gl-keyring --key=gl --plaintext-file=hub --ciphertext-file=hub.enc`

Then finally, the hub.enc needs uploaded to the storage bucked created above.

### Staging

**Name:** List Micro-Service Staging CI Build

**Trigger type:** Branch

**Branch (regex):** ^develop$

**Build configration:** cloudbuild.yaml

**cloudbuild.yaml location:** /staging/cloudbuild.yaml

#### Staging Substitution variables

| Variable                    | Value               |
| --------------------------- | -----               |
| _CLOUDSDK_COMPUTE_ZONE      | us-central1-f       |
| _CLOUDSDK_CONTAINER_CLUSTER | staging             |
| _KMS_KEY                    | gl                  |
| _KMS_KEYRING                | gl-keyring          |
| _PROJECT_ID                 | grocery-list-205220 |
| _GIT_HUB_KEY_BUCKET_NAME    | gl-git-hub-key      |

### QA

**Name:** List Micro-Service QA CI Build

**Trigger type:** Tag

**Tag (regex):** .*

**Build configration:** cloudbuild.yaml

**cloudbuild.yaml location:** /qa/cloudbuild.yaml

#### QA Substitution variables

| Variable                    | Value               |
| --------------------------- | -----               |
| _CLOUDSDK_COMPUTE_ZONE      | us-central1-f       |
| _CLOUDSDK_CONTAINER_CLUSTER | qa                  |
| _KMS_KEY                    | gl                  |
| _KMS_KEYRING                | gl-keyring          |
| _PROJECT_ID                 | grocery-list-205220 |
| _GIT_HUB_KEY_BUCKET_NAME    | gl-git-hub-key      |
