Spring Boot Starter Project


 * Spring Data JPA
 * Spring Web
 * Mapstruct
 * Lombok
 * springdoc-openapi



## Steps to deploy:

1. mvn package
2. docker build -t springboot-k8s-example .
3. kubectl apply -f k8s/deployment.yml
4. kubectl apply -f k8s/service.yml


## Skaffold commands

- skaffold init
- skaffold dev
- skaffold debug



## Running with docker

```bash
 ./mvnw spring-boot:build-image
 docker run -it -p8080:8080 demo:0.0.1-SNAPSHOT
```


## Swagger

http://localhost:8080/swagger-ui/index.html#/