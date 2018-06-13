# Spring 4 RESTFul Controller Example (REST CRUD Example)
Template example for Spring 4 MVC + RESTful Service with pure Java Configuration (no XML), using Maven build tool.

###1. Technologies
* Spring 4.3.1.RELEASE
* Maven 3.3.3
* JPA with Hibernate
* JPARepository


###2. Create Table Estudiante
1. Execute script to create table. File name
createTableEstudiante.sql

###3. To Run this project locally
```shell
$ git clone https://github.com/viralpatel/spring4-restful-example
$ mvn tomcat7:run
```
Get All Example ```http://localhost:8080/springrest/estudiantes```

Get One Example ```http://localhost:8080/springrest/estudiantes/<id existente para consultar>```

Post example
 ```
 http://localhost:8080/springrest/estudiantes
 ```
 ```
     {
         "id": <valor de algun id para modificar>,
         "firstName": "juan",
         "lastName": "perez",
         "email": "juan.perez@gmail.com"
     }
 ```
Put example 
```
http://localhost:8080/springrest/estudiantes
```

   ``` 
   {
        "id": <valor de algun id existente para modificar>,
        "firstName": "juanmodificado",
        "lastName": "perez",
        "email": "luciana.bazan@globant.com"
    }
```

![Spring 4 REST Tutorial](http://img.viralpatel.net/2016/06/spring-4-mvc-rest-controller-service-restful.png) 

###4. To import this project in Eclipse IDE
1. ```$ mvn eclipse:eclipse```
2. Import into Eclipse via **existing projects into workspace** option.
3. Done. 


###5. Project Demo
Please refer to this article [Spring 4 RESTFul Service Tutorial](http://viralpatel.net/blogs/spring-4-mvc-rest-example-json/)
