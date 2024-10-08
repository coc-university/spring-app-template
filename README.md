# Spring App Template
CodeCamp:N Spring Boot template

Given example config:
* Spring Boot 3, Java 21
* REST API
* Security with Form Login and HTTP Basic
* H2 database, Hibernate (JPA), Liquibase
* Tests for all layers
* Swagger
* Management endpoint (info, health, ...)
* OpenAPI yaml example for code generation of example REST Controller
* Checkstyle
* environment variables with defaults in application.yml
* github actions:
  * build (feature-) branches on pull requests
  * release build: use of conventional commits; docker build and push
  * TODO in your project: set MASTER_PUSH_TOKEN, COCREGISTRY_USER, COCREGISTRY_TOKEN, SONARQUBE_HOST, SONARQUBE_TOKEN in your project (settings -> Secrets and variables -> Actions)
* dependabot config (github) for docker and maven dependencies (rename depdendabot_template.yml to dependabot.yml)

# for developers
## initialize conventional commit settings local
`git config core.hooksPath .githooks`

## run from command line
`mvn spring-boot:run -Dspring-boot.run.profiles=dev`
