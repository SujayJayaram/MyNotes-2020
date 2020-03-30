- To run the server, run this class:
SpringBootDemoApplication
(From Maven you can also run the spring-boot:run target)

- application.properties file sets the
spring.profiles.active=dev

which means it will use the application-dev.properties file

- this contains the
server.port=8081 line which is the port used e.g. http://localhost:8081/


- static/index.html is the welcome page


- Anything with the @RestController will be able to map requests

