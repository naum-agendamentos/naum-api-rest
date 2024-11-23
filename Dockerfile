# Use uma imagem base com o JDK do OpenJDK
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o arquivo JAR para o diretório de trabalho do container
COPY target/api-naum.jar app.jar

# Comando para executar o arquivo JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
