# solucionretotecnico

solucion aplicativo oferta laboral

Se desarrolla una aplicacón con un modelo de negocio enfocado registro de usuarios con mas de un rol paar un sistema de registro  de usuarios. Utiliza spring security
usando JWT con un algoritmo RS256.

El aplicativo propuesto consiste en una aplicacion restfull siguiendo los principios de una arquitectura limpia y principios SOLID.

La aplicación esta desarrollada con Spring Boot 3.4.2, maven y usando java jdk 17


1-DISEÑO DE LA APLICACION:


ENFOQUE:ARQUITECTURA LIMPIA
JUSTIFICACIÓN: SEPARAR RESPONSABILIDADES
CAPAS: 
DOMAIN -> Entidades de negocios, repositorios, y excepciones de negocio. No depdende de nadie
APPLICATION -> DTO DE ENTRADA Y SALIDA, INTERFACES DE ENTRADA, MAPPER, CASO DE USO, VALIDACIONES. Depende de DOMAIN
INFRAESTRUCTURE ->Excepciones transversales, servicios transversales, interfaces, entidades jpa, implementacion de repositorios jpa, security y utilidades transversales. Depende de DOMAIN
WEB: Presentacion se encarga de presentar los controladores al usuario depende de todo lo anterior.

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

c- si se va ejecutar con docker compose:
 > docker-compose up -d

 c- Luego en postman o en un navegador ejecutar: http://localhost:8080/swagger-ui/index.html

 

 







