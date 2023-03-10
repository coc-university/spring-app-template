# Spring App Template
CodeCamp:N Spring Boot template

Given example config:
* Spring Boot 3, Java 17
* H2 database, Hibernate
* Liquibase
* Swagger
* Management endpoint (info, health, ...)
* OpenAPI yaml example for code generation of example REST Controller
* checkstyle
* github actions:
  * build (feature-) branches on pull requests
  * release build: use of conventional commits; docker build and push
* dependabot config (github) for docker and maven dependencies

# for developers
## initialize conventional commit settings local
`git config core.hooksPath .githooks`

## run from command line
`mvn spring-boot:run -Dspring-boot.run.profiles=dev`

## swagger
http://localhost:8080/api/swagger-ui/index.html#/