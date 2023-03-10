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
* environment variables with defaults in application.yml
* github actions:
  * build (feature-) branches on pull requests
  * release build: use of conventional commits; docker build and push
  * TODO in your project: add PUSH_PAT and PUSH_USERNAME to your project (settings -> Secrets and variables -> Actions)
* dependabot config (github) for docker and maven dependencies

# for developers
## initialize conventional commit settings local
`git config core.hooksPath .githooks`

## run from command line
`mvn spring-boot:run -Dspring-boot.run.profiles=dev`

## swagger
http://localhost:8080/swagger-ui/index.html#/