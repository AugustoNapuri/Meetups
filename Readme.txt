Aplicativo Meetups

-Ver modelo de entrada y salida de datos mediante Swagger en

    Local: http://localhost:8080/swagger-ui.html
    Servidor: https://meetups-rio.herokuapp.com/swagger-ui.html

-Usar la coleccion de Postman para probar el funcionamiento de las APIS
-Ver diagrama de base de datos database_diagram.png.

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



