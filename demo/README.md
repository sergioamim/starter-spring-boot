Spring Boot Starter Project


 * Spring Data JPA
 * Spring Web
 * Mapstruct
 * Lombok
 * springdoc-openapi





## Running with docker

```bash
 ./mvnw spring-boot:build-image
 docker run -it -p8080:8080 demo:0.0.1-SNAPSHOT
```


## Swagger

http://localhost:8080/swagger-ui/index.html#/