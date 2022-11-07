Spring Boot Starter Project


 * Spring Data JPA
 * Spring Web
 * Mapstruct
 * Lombok
 * springdoc-openapi
 * Jib
 * Kubernetes
 * Skaffold

## Requisitops

1. minikube
2. ingress habilitado
3. skaffold instalado


## Habiliar Ingerss no minikube
```bash
minikube addons enable ingress
```



## Skaffold commands

- skaffold init
- skaffold dev (fica monitorando alteracoes e aplica automaticamente)
- skaffold debug


## Running with docker

```bash
 ./mvnw spring-boot:build-image
 docker run -it -p8080:8080 demo:0.0.1-SNAPSHOT
```



## Listar IP e porta exposta pelo Ingress para poder acessar o servico

```bash
kubectl get ingress    
```


## Swagger

{IP_K8S}/swagger-ui/index.html#


