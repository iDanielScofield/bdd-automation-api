# Automação de Testes de API usando BDD
## bdd-automation-api

### Requisitos:
* Java 1.8 +
* Gradle 6 +
* Docker

### Executar os Testes Localmente
* Subir a loja Swagger Pet Store - `docker run  --name swaggerapi-petstore3 -d -p 8080:8080 swaggerapi/petstore3:unstable`
* Rodar os testes - `./gradlew test`
* Relatório do Cucumber - `app/build/reports/feature.html`
