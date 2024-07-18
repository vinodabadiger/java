FROM openjdk:23-jdk-slim
WORKDIR /app
COPY . /app
EXPOSE 8080
ENTRYPOINT ["java","main.java"]
