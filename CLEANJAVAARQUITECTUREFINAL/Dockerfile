# Usar una imagen base de OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR de la aplicación al contenedor
COPY web/target/*.jar app.jar


COPY web/src/main/resources/rutaprivada.txt /app/rutaprivada.txt
COPY web/src/main/resources/rutapublica.txt /app/rutapublica.txt
COPY private.pem /app/private.pem
COPY public.pem /app/public.pem

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
