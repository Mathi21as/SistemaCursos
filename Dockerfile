FROM openjdk:18-alpine
COPY target/sistemaCursos-0.0.1-SNAPSHOT.jar /app/sistemaCursos.jar
ENTRYPOINT ["java", "-jar", "/app/sistemaCursos.jar"]