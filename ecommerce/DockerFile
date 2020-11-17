FROM openjdk:8-jre-alpine
ADD target/*.jar app.jar
EXPOSE 9200
ENTRYPOINT [ "java", "-jar", "app.jar" ]