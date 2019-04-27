# innovativeproject-ehealth-video-streams

## Prerequisites
* mvn >= 3.5.4
* java jdk >= 11
* docker

## Installation

Compile project

```bash
mvn clean install
```

Build docker image

```
docker build -t ehealth .
```

Run

```
docker-compose up
```

## Usage
Server with database will start on port 8090.
Application uses http basic authentication.


### endpoints

* /api/v1/users
    * _GET_ /
        * Returns list of all users
        * Authenticated
        * Requires admin role
     * _POST_ /
        * Registers user
        * Public
        * Request: 
        ```json
        {
           "name": string,
           "password": string
        }
        ```
    * _GET_ /{id}
        * Returns user details
        * Authenticated
    * _DELETE_ /{id}
        * Authenticated
    * _GET_ /{name}/byName
        * Returns user details of a user with specified name
        * Authenticated
    * _GET_ /{id}/emotions?(page=number&size=number&sort=string&direction=(asc|desc))
        * Returns page with emotions list
        * page, size, sort, direction - are optional
        * Authenticated
    * _POST_ /{id}/emotions
        * Saves emotion data
        * Authenticated
        * Request:
        ```json
        {
            "fear": double,
            "anger": double,
            "sadness": double,
            "happiness": double,
            "disgust": double,
            "surprise": double
        }
        ```
        * JSON request params are in range of [0.0, 1.0]
    * _GET_ /{id}/pulses?(page=number&size=number&sort=string&direction=(asc|desc)) 
        * Returns page with pulses list
        * page, size, sort, direction - are optional
        * Authenticated
    * _POST_ /{id}/pulses
        * Saves pulse data
        * Authenticated
        * Request:
        ```json
        {
          "pulse": int
        }
        ```
    * _POST_ /{id}/pulses/{pulseId}/comments
        * Adds comment to pulse readout
        * Authenticated
        * Request:
        ```json
        {
          "comment": string
        }
        ```
    * _POST_ /{id}/emotions/{emotionId}/comments
        * Adds comment to emotion readout
        * Authenticated
        * Request:
        ```json
        {
          "comment": string
        }
        ```
    
