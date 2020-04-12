Aplicativo Meetups

Datos a tener en cuenta:

-Ver diagrama de base de datos.
-Usar la coleccion de Postman para probar el funcionamiento de las APIS
-Ver modelo de entrada y salida de datos mediante Swagger en 
    http://localhost:8081/swagger-ui.html

Herramientas necesarias para ejecucion:

-Java jdk 1.8
-Maven 3.6.3
-Postman (Recomendado para el testeo de las apis)

Como ejecutarlo:

-Ingrese a la carpeta Meetups
-Ejecute "mvn clean install"
-Ingrese a carpeta target
-Ejecute "java -jar MeetUps-0.0.1-SNAPSHOT.war"
-Importe la coleccion "meetups.postman_collection.json" al Postman.

Herramientas usadas para el diseño del aplicativo:

-HSQL base de datos en memoria
-Spring starter web (Servlet Container) (Tomcat embebido)
-Spring security con Auth basic
-Spring data JPA
-Swagger 2
-Circuit breaker

Credenciales:

-Username : "user"
-Password : "pass"



