# StarkOverflow

> September 2020

## Introduction

The purpose of this project is to learn about multi tiered application by creating a simplified version of _Stackoverflow_.

## Quickstart

### Build and run locally

<u>Requirements</u> : 

- Maven: 3.6.3
- OpenJDK: 11

We just need to run the `build-image.sh` and `run-image.sh` script :

```bash
./build-image.sh && ./run-image.sh
```

### Run with docker

We can run the application just by executing the latest build:

```bash
docker run -d -p 8080:9080 ghcr.io/digitalpatate/starkoverflow:latest
```

Or docker-compose : 

```yaml
version: '3'
services:
  backend:
    image: ghcr.io/digitalpatate/starkoverflow
    ports:
     - "8080:9080"
```


(!) For now there is only the _latest_ tag available for the image

## Environment variables

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



## Site Map

![Site Map](pictures/StarkOverFlow_sitemap.png)

 ##  Model

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
- Color

### Vote

- Pointer on question or answer
- Pointer on user

## Contributors

- Simon Walther - simon.walther@heig-vd.ch
- Didier Page - didier.page@heig-vd.ch
- Eric Noel - eric.noel@heig-vd.ch
- Guillaume Laubscher -  guillaume.laubscher@heig-vd.ch
- Bruno Legrand - bruno.legrand@heig-vd.ch