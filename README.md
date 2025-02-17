# solucionretotecnico

solucion aplicativo oferta laboral

Se desarrolla una aplicacón con un modelo de negocio enfocado registro de usuarios con mas de un rol para un sistema de registro  de usuarios. Utiliza spring security
usando JWT con un algoritmo RS256.

HERRAMIENTAS DE DESARRROLLO
JIDEA
JDK 17
POSTGRESQL
DOCKER DESKTOP

A nivel de base  de datos tenemos
usuarios,
rol,
usuariorol
Excepciones,

![image](https://github.com/user-attachments/assets/fff353cc-c9b0-4563-b7c3-b68233993243)


El aplicativo propuesto consiste en una aplicacion restfull siguiendo los principios de una arquitectura limpia y principios SOLID.

La aplicación esta desarrollada con Spring Boot 3.4.2, maven y usando java jdk 17


1-DISEÑO DE LA APLICACION:


ENFOQUE:ARQUITECTURA LIMPIA

![image](https://github.com/user-attachments/assets/9443afc1-0931-4506-8c9e-da213fdd4fa0)

JUSTIFICACIÓN: SEPARAR RESPONSABILIDADES


CAPAS: 
DOMAIN -> Entidades de negocios, repositorios, y excepciones de negocio. No depdende de nadie
APPLICATION -> DTO DE ENTRADA Y SALIDA, INTERFACES DE ENTRADA, MAPPER, CASO DE USO, VALIDACIONES. Depende de DOMAIN
INFRAESTRUCTURE ->Excepciones transversales, servicios transversales, interfaces, entidades jpa, implementacion de repositorios jpa, security y utilidades transversales. Depende de DOMAIN
WEB: Presentacion se encarga de presentar los controladores al usuario depende de todo lo anterior.
TEST -> Módulo de prueba unitarias con MOCK Y JUNIT


PATRONES DE DISEÑO: REPOSITORY

Nota: APPLICATION esta desacoplada de INFRAESTRUCTURE Y VICERVERSA YA QUE USAMOS INTERFACES PARA NO USAR DEPEDENCIA , USANDO PRINCIPIO DE INTERFACES Por ejmeplo EN DOAMIN En lugar de que tu lógica de negocio dependa directamente de la base de datos (por ejemplo, a través de una implementación concreta de un repositorio), la lógica de negocio debería depender de interfaces o abstracciones. Estas interfaces luego se implementan en niveles inferiores, como el acceso a la base de datos o la integración con servicios externos.

2-FUNCIONAMIENTO DE LA APLICACIÓN

CONFIGURACION PREVIAS:

-TENER INSTALADOR DOCKER
-CREAR UNA BASE DE DATOS EN POSTGRESQL llamada test
-CONFIGURAR EL ARCHIVO APPLICATION.PROPERTIES 

Como se observa hay tres cadenas de acuerdo como se quiera conectar

#LOCAL
# spring.datasource.url=jdbc:postgresql://localhost:5432/test?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
#DOCKER COMPOSE
spring.datasource.url=jdbc:postgresql://postgresql:5432/test?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
# DOCKER DESTOP
# spring.datasource.url=jdbc:postgresql://host.docker.internal:5432/test?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=postgres
spring.datasource.password=admin

CONFIGURAR EL ARCHIVO DE RUTAS
Como se observa hay cuatros archivos de rutas por defecto se usa rutapublica.txt y rutaprivada.txt esto por que busca la ruta de las llaves pública y privada para el RS256

a-Para ejecutar la aplicación primero se debe ejecutar el comando: mvn clean package -DskipTests -Dmaven.test.skip=true 
b-Si se va ejecutar en docker desktop: 
> docker build -t web-spring-app .
> >docker run -p 8080:8080 web-spring-app
> >
> Crear uun rol, un usuario y asociarlo en la tabla usuariosrol

c- si se va ejecutar con docker compose:
 > docker-compose up -d
 > detener el servicio de postgres sql , recordar que estamos ejecutando el de contenedor
> 
 > Crear un rol, un usuario y asociarlo en la tabla usuariosrol

 c- Luego en postman o en un navegador ejecutar: http://localhost:8080/swagger-ui/index.html

 
FUNCIONAMIENTO EN LA NUBE EJEMPLO AWS

1-USANDO EC2 CONTROL TOTAL


![image](https://github.com/user-attachments/assets/523f28a4-1b9d-4087-b43a-403f927f074d)


   -CREAR UN USUARIO IAM
   -DARLE PERMISOS FULL ACCESS EC2

   -CREAR GRUPO DE SEGURIDAD

  -CREAR UNA MAQUINA VIRTUAL PARA BASE DE DATOS
   -INSTALAR JAVA Y POSTGRESQL
   
   -CREAR UNA MAQUINA VIRTUAL PARA LA API
   -INSTALAR JAVA Y POSTGRESQL
   -INSTALAR INTELLIJDEA
   -INSTALAR DOCKER
   -CREAR IMAGEN
   -CREAR CONTENEDOR   
   -CORRER EL CONTENEDOR

    -CREAR UN VPC
    RED
    -PRIVADA PARA LA BASE DE DATOS
    -PUBLICAR PARA LA APLICACIÓN
    -CREAR UN NAT GATEWAY
    -CREAR UN INTERNET GATEWAY
    -CREAR UN LOAD BALANCER

   también se pdoria gestionar con kubernetes dentro de la maquina virtual de aplicación

   2 USANDO AWS ECS
   Crear  Base de Datos (RDS/PostgreSQL o MySQL)
   Empaquetar la aplicación Spring Boot en un archivo JAR
   Crear un Dockerfile para tu Aplicación
   Crear una Imagen Docker
   Subir la Imagen a Amazon ECR (Elastic Container Registry)
   Crear una Tarea en ECS
   Crear un Clúster ECS
   Configurar un Load Balancer (opcional)
   Crear un Servicio ECS
   Acceso Público

   3 USANDO AWS LAMBDA ->IMPLICA MODIFICAR EL CODIGO


 







