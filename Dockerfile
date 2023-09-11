






FROM openjdk:8
WORKDIR /app
ADD build/libs/spring-boot-docker.jar spring-boot-docker.jar
EXPOSE 8000
CMD ["java","-jar","spring-boot-docker.jar"]

