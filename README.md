# knowit-integration

Kjør prosjektet i IntelliJ fra fila IntegrationApplication. Ingen profiler eller andre parametere må settes.

Swagger er tilgjengelig på http://localhost:8080/api/documentation/swagger for dokumentasjon av endepunktene. 

Applikasjonen kan også kjøres som en docker container:

mvn clean install

docker build -t knowit-integration .

docker run -d -p 8080:8080 knowit-integration

##