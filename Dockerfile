FROM maven:3.8.4-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests

FROM maven:3.6.3-openjdk-17-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]