# Possible improvements/considerations
- Use Lombok to reduce the amount of boilerplate code(I used the IDEs auto generate function)
- some indirections (e.g. interfaces, mappers) are clearly overkill for simple operations. The application ended becoming too verbose for what it accomplishes
- pagination: in case there are many users on the database. Spring Flux doesn't seem to offer great support for this
- capture trace-id from the request: for better observability
- logs in json: better integration with Observability tools like Splunk
- have proper database management in place, e.g.: Flyway
- configure Jacoco for coverage
- implement integration tests: wiremock, etc.
- api versioning
- email validation
- security
- apply circuit breaker in case Reqres is not responding after trying some times with Exponential Backoff
- Logs in Json
- integration tests. Only did some unit
- Better erroHandling

# Running locally
1. Spin up a MySQL instance (only necessary for a profile different than **local**):
    ```
   docker-compose up
    ```
   
2. Configure the necessary USERNAME and PASSWORD environment variables for the MySQL

3. Spin up the application. By default, it use the in-memory H2 database to not need the MySQL:
   ```
   docker image build -t users-api:latest .
   docker run -p 8080:8080 --name users-api-container users-api:latest
   ```

# API documentation
The specification follows OAS3 standard

Swagger Link: http://localhost:8080/swagger-ui.html

## Attention

For the friends list, the users must already be registered