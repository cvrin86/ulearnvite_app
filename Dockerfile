# # Base de l'image Docker - On utilise une image oficielle fourne par Eclipse Temurin 
# # Cette image contient tout ce qu'il faut pour executer des application Java compilées
# FROM eclipse-temurin:21-jdk


# # 📁 Répertoire de travail :
# # On crée (ou utilise) le dossier /app à l'intérieur du conteneur, et toutes les commandes suivantes (comme COPY, RUN, etc.) se feront à partir de ce répertoire.
# WORKDIR /app


# # 📂 Copie du fichier JAR compilé :
# # On prend le fichier .jar qui se trouve dans le dossier target/ de ton projet (sur ta machine ou dans le build Render), et on le copie dans le conteneur sous le nom app.jar.
# # Ce fichier est le résultat de mvn package.
# COPY target/ulearnvite-0.0.1-SNAPSHOT.jar app.jar


# # On indique que l’application écoute sur le port 8080.
# EXPOSE 8080

# # 🚀 Commande de démarrage :
# #     Cela lance ton application Spring Boot à l’intérieur du conteneur.
# ENTRYPOINT ["java","-jar","app.jar"]



# Étape 1 : build du JAR
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : exécution
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/ulearnvite-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
