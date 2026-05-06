FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# instalar maven dentro del contenedor
RUN apk add --no-cache maven

# copiar todo el proyecto
COPY . .

# compilar dentro del contenedor
RUN mvn clean package -DskipTests

# mover jar generado
RUN cp target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]