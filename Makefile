include .env

mvn-wrapper:
	mvn -N wrapper:wrapper

docker-image:
	./mvnw spring-boot:build-image

run-docker:
	docker run -it -p8080:8080 demo:0.0.1-SNAPSHOT

deploy-docker:
	mvn compile jib:build