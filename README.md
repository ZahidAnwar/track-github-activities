# Track github activities

A RESTful API for tui employee to access github information.

## Getting Started
Below are some instructions, which will get the project up and running on your local machine for development and testing purposes.

### Prerequisites
* [Maven 3.6.1](https://maven.apache.org/)
* [openjdk 11.0.9 (Java 11)](https://openjdk.java.net/)
* [Docker](https://www.docker.com/)

## Building & Running
There are multiple steps to building / compiling and running this project:
* Build / compile the jar using Maven

### Building jar
Before you run all services you will need to compile and build the service .jar. To do this run the following command:
```
mvn clean install
```
This will also run any tests as well as any code quality checks.

## Getting started Option 1
Running the application as a Docker container

```
docker build -t track-github-activities:0.0.1 .
docker run -p 8082:8082 track-github-activities:0.0.1
```

## Getting started Option 2
Running the application as Jar

```
java -jar target/track-github-activities-{version}.jar
```


## Running the API in a Docker container using `service.Docker` file
```
mvn clean install
docker build -t track-github-activities:0.0.1 -f service.Dockerfile .
docker run --rm -it -p 8082:8082 track-github-activities:0.0.1
```


## Tech Stack
* Java 11
* Spring Boot

##### Unit test coverage should meet minimum 80%, industry standard
##### Current test coverage is 85%, which is above the expected limit
##### Coverage can be configured from following properties from pom.xml file, line 47
```
 <coverage.limit>0.80</coverage.limit>
```

## API Endpoint
Env  | Method | URL | Feature
------------- |--------| -------------| -------
local  | GET   | http://localhost:8082/v1/track/user/repositories | Get user github repository

##### GitHub access token and organization name should be injected on the container
```
GITHUB_ACCESS_TOKEN
GITHUB_ORGANIZATION
```
##### If GitHub access token and organization name not found, app should use hardcoded default one


##### Swagger Url
```
http://localhost:8082/swagger-ui/index.html
```

#####  curl to get valid response for IGSLtd organization

```

curl \
    -H "Accept: application/json" \
    "http://localhost:8082/v1/track/user/repositories?userName=ZahidAnwar"
    
API response
    
{
  "repositories": [
    {
      "repositoryName": "Android-app-test",
      "ownerName": "ZahidAnwar",
      "branches": [
        {
          "branchName": "master",
          "commitSha": "3a51ad70f437173cec6501b271ef2db72a2f253c"
        }
      ]
    },
    {
      "repositoryName": "MyTest",
      "ownerName": "ZahidAnwar",
      "branches": null
    },
    {
      "repositoryName": "nodejs-complete-guide",
      "ownerName": "ZahidAnwar",
      "branches": [
        {
          "branchName": "master",
          "commitSha": "6316dcafcec429ef668f34f81edeec4c45f0c8fa"
        }
      ]
    }
  ]
}
        
```

#####  curl without userName or empty user name, should respond with 404, message = Missing user name on the param
```

curl \
    -H "Accept: application/json" \
    "http://localhost:8082/v1/track/user/repositories?userName="


API response
    
    {
    status: 404,
    message: "Missing user name on the param"
    }
       
```

#####  if user not exist for this organization, api should response with 404, message = 
##### Supplied user not exists for the organization.

```

curl \
    -H "Accept: application/json" \
    "http://localhost:8082/v1/track/user/repositories?userName=Foo"
    

API response
    
    {
    status: 404,
    message: "Supplied user not exists for the organization"
    }
    
```


#####  curl without Accept header or empty Accept header or invalid Accept header, 
#####  api should response with 406, message = Missing/invalid header value.

```

curl http://localhost:8082/v1/track/user/repositories?userName=Foo

API response

{
status: 406,
message: "Missing/invalid header value"
}
    
```

## Running integration tests using docker-compose
```
mvn clean install
docker-compose up -d
mvn -P integration-tests post-integration-test
```
Stop running containers after integration testing
```
docker-compose down
```

## Running integration tests with app running separately against wiremock stub
```
mvn clean install
docker-compose up -d
mvn -P integration-tests post-integration-test
```

```
mvn clean install
docker-compose up -d
mvn -P integration-tests post-integration-test
```
Stop running containers after integration testing
```
docker-compose down
```
