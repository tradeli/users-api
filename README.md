# Possible improvements/considerations
- use Lombok to reduce the amount of boilerplate code(I used the IDEs auto generate function)
- some indirections (e.g. interfaces, mappers) are clearly overkill for simple operations. The application ended becoming too verbose for what it accomplishes
- pagination: in case there are many users on the database. Spring Flux doesn't seem to offer great support for this
- capture trace-id from the request: for better observability
- logs in json: better integration with Observability tools like Splunk. Almost implemented
- have proper database management in place, e.g.: Flyway
- configure Jacoco for coverage
- implement integration tests: wiremock, etc.
- api versioning
- email validation
- proper security
- apply circuit breaker in case Reqres is not responding after trying some times with Exponential Backoff
- better errorHandling

# Running locally
The application has 2 profiles in the application.yml: local and homolog
- **local**: uses the default in-memory H2 database
- **homolog**: uses MySQL
The script **docker-compose.yml** spins up both MySQL and the application on different containers at the same time.

1. First build the application and its the container:
   ```  
   mvn clean package
   docker pull openjdk:17-jdk
   docker image build -t users-api:latest .
   ```
2. In case you wish to test with only the H2 DB, set the profile as **local** and just spin the application:
   ```
   docker run -p 8080:8080 --name users-api-container users-api:latest
   ```
3. Finally, to instead test with the MySQL, set the active profile to **homolog** and then
(**sometimes the connection between the containers fail with this method**, coudn't figure it out yet):
   ````
   docker compose up
   ````
4. To run the application locally without a container and connect to another MySQL instance, **configure the env variables**:
   ````
   MYSQL_HOST=localhost
   USERNAME=...
   PASSWORD=...
    ````
# API documentation
The specification follows OAS3 standard

Swagger Link: http://localhost:8080/swagger-ui.html

## Attention

For the friends list, the users must already be registered