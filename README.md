

<img src="pictures/Readme_gh.png" style="zoom:50%;" />

# **StarkOverflow**

> September - November 2020

Table of contents
=================

   * [Table of contents](#table-of-contents)
   * [Introduction](#Introduction)
   * [Requirements](#Requirements)
   * [Quick-start](#Quick-start)
   * [Docker](#Docker)
* [Launch the project manually](#Launch-the-project-manually)
     * [Environment variables](#Environment-variables)
* [Tests](#tests)
  * [E2E - testing](#E2E---testing)
  * [Load testing](Load-testing)
  * [Integration testing](#Integration-testing)
*  [Concept Diagrams](Concept-Diagrams)
  * [Site Map](#Site-Map)
  * [Model](#Model)
* [Contributors](#Contributors)

## Introduction

The purpose of this project is to learn about multi tiered application by creating a simplified version of [_Stackoverflow_](https://stackoverflow.com).



## Requirements

- Maven: 3.6.3
- OpenJDK: 11
- docker-compose: 1.25



## Quick-start



To run the project, just launch the script.

```bash
$ ./run-application.sh
```

The script will automatically manage the life-cycle of the web application and the database containers.

To stop the project

```bash
$ ./stop-application.sh
```

The application is now accessible on : http://localhost:9080

## Docker

 All services used in this project have been containerized. They are described by the docker-compose `yaml` file below :

```yaml
version: "3.7"
services:
  db:
    image: postgres:latest
    restart: always
    environment:
      POSTGRES_DB: postgres
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: secret
      TZ: 'Europe/Zurich'
      PGTZ: 'Europe/Zurich'
    volumes:
      - ./docker/database/scripts:/docker-entrypoint-initdb.d
    ports:
      - "5432:5432"
    networks:
      - backend

# comment to avoid starting pgadmin along the database and the webapp
  pgadmin:
    image: dpage/pgadmin4:latest
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: dev@starkoverflow.ch
      PGADMIN_DEFAULT_PASSWORD: secret
      PGADMIN_LISTEN_PORT: 80
    ports:
      - "8081:80"
    links:
      - "db:pgsql-server"
    networks:
      - backend


  web:
    image: ghcr.io/digitalpatate/starkoverflow:latest
    environment:
      DB_HOST: db
      DB_PORT: 5432
      DB_NAME: stark_db
      DB_USER: admin
      DB_PASSWORD: secret
    depends_on:
      - db
    ports:
      - "9080:9080"
    networks:
      - backend
networks:
  backend:
    external: false

```

The two images of `Postgres` and `PGAdmin` are located on the Docker Hub whereas the image of the `webapp` is provided by the GitHub Container Registry ; for now there is only the _latest_ tag available for the image.  This custom image is automatically built by the Github actions when someone push code on the master branch.

## Launch the project manually 

First of all make sure to have a database up and ready with the correct configuration. To do so, run the following commands :

```bash
$ cd docker/environment/dev
$ docker-compose up -d
```

The related docker-compose file contains only database service and is the very same as previously.

Then launch liberty server by triggering the goal :

```bash
mvn liberty:dev
```

The application is now accessible on : http://localhost:9080

### Environment variables

#### Openliberty

For local dev, we use the openliberty maven plugin and use the default variable name in the server.xml file to setup the database configuration

```xml
<variable name="DB_HOST" defaultValue="localhost"/>
<variable name="DB_PORT" defaultValue="5432"/>
<variable name="DB_NAME" defaultValue="stark_db"/>
<variable name="DB_USER" defaultValue="admin"/>
<variable name="DB_PASSWORD" defaultValue="secret"/>
<dataSource jndiName="jdbc/postgresql">
        <jdbcDriver libraryRef="postgresql-library"/>
        <properties.postgresql serverName="${DB_HOST}"
                               databaseName="${DB_NAME}"
                               portNumber="${DB_PORT}"
                               user="${DB_USER}"
                               password="${DB_PASSWORD}"/>
</dataSource>
```

With this configuration, the variables can be override with environment variable. Like in the docker-compose :

```yaml
 backend:
    image: ghcr.io/digitalpatate/starkoverflow
    environment:
      DB_HOST: db
      DB_PORT: 5432
      DB_NAME: stark_db
      DB_USER: admin
      DB_PASSWORD: secret
    depends_on:
      - db
    ports:
     - "8080:9080"
    networks:
      - backend
```

### E2E - testing

[See dedicated documentation ](./e2e/README.md)


### Load testing

[See dedicated documentation ](./loadTests/README.md)

### Integration testing

A try to set arquillian integration tests up was made in the feature branch *fb-arquillian*. Unfortunately it did not work and we ran out of time in order to write new integration tests. 

By running the script `run-arquillian-integration-tests.sh` we get the following error

```
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ArquillianTest
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.064 s <<< FAILURE! - in ArquillianTest
[ERROR] initializationError  Time elapsed: 0.039 s  <<< ERROR!
java.lang.Exception: No runnable methods

[INFO] Running ch.heigvd.amt.starkoverflow.infrastructure.memory.comment.InMemoryCommentRepositoryTest
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.002 s <<< FAILURE! - in ch.heigvd.amt.starkoverflow.infrastructure.memory.comment.InMemoryCommentRepositoryTest
[ERROR] ch.heigvd.amt.starkoverflow.infrastructure.memory.comment.InMemoryCommentRepositoryTest  Time elapsed: 0.002 s  <<< ERROR!
java.lang.NoClassDefFoundError: org/junit/platform/commons/util/ClassNamePatternFilterUtils
Caused by: java.lang.ClassNotFoundException: org.junit.platform.commons.util.ClassNamePatternFilterUtils

...
```
## Implementation choice

### Gamification engine

This project includes gamification principles thanks to the gamification engine called [Gamificator](https://github.com/digitalpatate/Gamificator). This allows the user to be motivated by earning points or badges, and to highlight the importance of his or her position through rankings.

#### REST API 

When a user perform actions such as asking or answering question, requests are performed on the corresponding endpoints to update and retrieve the gamification data related to the user.

##### Application

The Gamificator is able to manage several applications and so the Starkoverflow project use one application created beforehand. This is done by authenticating to the gamification engine with an API-KEY and an API-SECRET.

```java
// example of API-KEY and an API-SECRET
private String key = "b911018e-da71-4000-82b5-388e4b8fb5c5";
private String secret = "YnZdXMdUkP";
```

When using these credentials the Starkoverflow application with its designated application.

##### Gamificator endpoints

By issuing requests on the corresponding Gamificator endpoints the Starkoverflow application update the user progression. This progression can be retrieve on the endpoint */reputations/{UUID}*, where the UUID represent the user. It is given as list of badges and  list of point scales.

##### Progression

The progression of the current user is available through his profile page. A progression example looks like the figure below.

![User Progression](pictures/user_progression.jpg)
=======
### Known problems

### Environment variables

For now the _key_ and the _secret_ and _url_ used to connect to _gamificator_ has been hard codded in the `RestService` file. 

It works until you want to run _StarkOverflow_ in a docker container aside the _Gamificator_ app. Because you'll need to configure the url with the container name.

## Concept Diagrams

### Site Map

![Site Map](pictures/StarkOverFlow_sitemap.png)

 ###  Model

![Domain model](./pictures/domaine-model.png)

### Question

- Title
- Content
- Tag list (opt)
- Creation date
- Author (user_ID)

### Comments

- Content 
- Creation date
- Author
- Pointer on question or answer

### Answer

- Content 
- Creation date
- Author
- Pointer on question or answer
- State (Approved)

### Tag

- Name 
- Colors

### Vote

- Pointer on question or answer
- Pointer on user

## Rules

To earn points or badges, one should do :

| Rule                  | Condition(s) to trigger             | Point(s) awarded | Badge(s)   | Point scale(s) |
|-----------------------|-------------------------------------|------------------|------------|----------------|
| createaquestion       | create a question                   | 2                | -          | learning       |
| answerataggedquestion | answer a tagged question            | 5                | -          | \<tag\>        |
| commentaquestion      | comment a question                  | 1                | -          | commentator    |
| validanswer           | get approval for one of your answer | 0                | liechti    |                |
| cookies               | vote for an answer                  | 1                | cookie     | cookies        |
| findthekey            | but where is the key ?              | 0                | heart, key |                |

## Contributors

- Simon Walther - simon.walther@heig-vd.ch
- Didier Page - didier.page@heig-vd.ch
- Eric Noel - eric.noel@heig-vd.ch
- Guillaume Laubscher -  guillaume.laubscher@heig-vd.ch
- Bruno Legrand - bruno.legrand@heig-vd.ch
