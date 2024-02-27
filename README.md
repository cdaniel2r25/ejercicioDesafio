#  Desafio Tecnico: Creacion de usuarios" Spring Boot

Esta es una aplicación Java/Gradle/Spring Boot (versión 3.2.0) de muestra que se puede usar como iniciador para que exponga una API RESTful de creación de usuarios.

##  Cómo ejecutar


* Clonar este repositorio
* Asegúrese de estar usando Java 17
* Una vez limpiado con éxito, puede ejecutar el servicio mediante este método:
```
        gradle bootRun
```

Una vez que se ejecute la aplicación, debería ver algo como esto

```
INFO 20272 --- [  restartedMain] o.s.b.w.e.t.TomcatWebServer              : Tomcat started on port(s): 8080 (http) with context path ''
INFO 20272 --- [  restartedMain] d.s.w.p.DocumentationPluginsBootstrapper : Context refreshed
INFO 20272 --- [  restartedMain] d.s.w.p.DocumentationPluginsBootstrapper : Found 1 custom documentation plugin(s)
INFO 20272 --- [  restartedMain] s.d.s.w.s.ApiListingReferenceScanner     : Scanning for api listing references
INFO 20272 --- [  restartedMain] c.e.b.d.DemoApplication                  : Started DemoApplication in 4.796 seconds (JVM running for 5.6)
```

##  Sobre el Servicio

El servicio es simplemente un servicio REST de Creacion de usuarios. Utiliza una base de datos en memoria (H2) para almacenar los datos. puede acceder los recursos REST definidos en ```com.example.ejercicio.controller.UserController``` en el **puerto 8080** . (vea abajo)
 
Esto es lo que demuestra esta pequeña aplicación:

* Integración completa con el último Framework **Spring** : inversión de control, inyección de dependencia, etc.
* Escribir un servicio RESTful usando anotación: admite solicitudes/respuestas JSON
* Asignación de excepciones  de aplicaciones a la respuesta HTTP correcta con detalles de excepción en el cuerpo
*  *Spring Data* Integración con JPA/Hibernate con solo unas pocas líneas de configuración y anotaciones familiares.
* Funcionalidad CRUD automática contra la fuente de datos usando el patrón Spring *Repository*
* Todas las API están "autodocumentadas" por  spring doc Swagger usando anotaciones

Estos son los endPoint a los que puede llamar:

###  Creacion de usuario.

```
http://localhost:8080/api/user/register
POST /api/user/register
accept: aplicación/json
content-type: aplicación/json

{
	"name": "Juan Rodriguez",
	"email": "juan@rodriguez.org",
	"password": "Hunter2",
	"phones": [
		{
			"number": "1234567",
			"citycode": "1",
			"contrycode": "57"
		}
	]
}


```


###  Para ver la documentación de la API de spring doc 

Ejecute el servidor y busque http://localhost:8080/swagger-ui/index.html#/


###  Para ver su base de datos en memoria H2

El perfil de "testdb" se ejecuta en la base de datos en memoria H2. Para ver y consultar la base de datos, puede navegar a http://localhost:8080/console. El nombre de usuario predeterminado es 'sa' con una contraseña '123456'


#  Preguntas y Comentarios: cdaniel2r.dr@gmail.com