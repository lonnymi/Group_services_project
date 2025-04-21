# Étape 1: Utiliser une image officielle de Java (OpenJDK 17)
FROM openjdk:17-jdk-slim

# Étape 2: Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3: Copier le fichier JAR de l'application dans le conteneur
COPY target/group-service-0.0.1-SNAPSHOT.jar /app/group-service.jar

# Étape 4: Exposer le port 8080 pour l'application
EXPOSE 8080

# Étape 5: Commande pour démarrer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "group-service.jar"]
