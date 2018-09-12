Project Title
=================
A simple web service that allows manipulating a list of users with all basic RESTful operations.

Web service end points: 

Get all users: http://localhost:8080/rest/users/all

Get a user: http://localhost:8080/rest/users/<id>
	
Add a user: http://localhost:8080/rest/users/add	

Update a user: http://localhost:8080/rest/users/update/<id>

Remove a user: http://localhost:8080/rest/users/remove/<id>
	
Getting Started
============

Prerequisities
-----------------
Have maven installed on the system. 

If not, please follow [this link](https://maven.apache.org/install.html) to install maven: 


Installing
-----------------

Download the program [users-service](https://github.com/ajenniechau/master/tree/master/users-service) from  https://github.com/ajenniechau/master

Compiling 
-----------------
Open a Terminal in Linux/Mac.
Navigate to the root directory of the project (users-service), where the pom.xml resides.
Execute the following Maven command to compile the code.

```
mvn clean compile 
```

Running 
-----------------
Follow steps above to compile the project
Navigate to the root directory of the project (users-service), where the pom.xml resides.
While still at the root directory of the project (users-service), where the pom.xml resides , execute the following Maven command to compile the code.

```
mvn exec:java
```

Running Unit Tests
============ 
There is 95% test coverage for this project, reported by [EclEmma tool](http://www.eclemma.org/)

Open a Terminal in Linux/Mac.
Navigate to the root directory of the project (users-service), where the pom.xml resides.
Execute the following Maven command to run unit test the code.

```
mvn clean compile test
```
User Data
============ 
A user has the following data fields:
id (auto-generated),
firstName (required field to create a user),
lastName (required field to create a user),
email  (required field to create a user),
gender,
city,
state,
birthday


When adding a user, userID is auto generated and is unique. 
A new user needs to have at least  first name, last name, and a valid email address in order to be added to the system. 

Web Service Operation
============ 

While having the program run, you can operate : 
Get All Users: 
-----------------
```
curl -X GET http://localhost:8080/rest/users/all | python -mjson.tool
```

Get A Specific User: 
-----------------
A user id is needed

Example:
```
curl -X GET http://localhost:8080/rest/users/101 | python -mjson.tool
```
Update User: 
-----------------
To update a user, you only need to specify fields that need to be updated. 

Example:
```
curl -X PUT -i -H "Content-Type:application/json" -d '{"id":103,"firstName":"Abigail","lastName":"Happy","email":"aadams@rubiconproject.com","city":"Braintree","state":"MA","birthday":"11-22-1978"}' http://localhost:8080/rest/users/update/103
```

Add User: 
-----------------
When adding a user, userID is auto generated and is unique. 

A new user needs to have at least  first name, last name, and a valid email address in order to be added to the system.

If email address is not in the right format (e.g., username@domain.com) a response with bad request status is returned. 

If birthday field is present, birthday needs to be in MM-DD-YYYY or M-D-YYYY format. A bad-request response will be returned if birthday is not in the right format, if present 

Example:

```
curl -X POST -i -H "Content-Type: application/json" -d '{"firstName":"Laura","lastName":"Joe","email":"lstuart@domain.com","city":"Braintree","state":"MA","birthday":"01-22-1980"}' http://localhost:8080/rest/users/add
```

Delete User: 
-----------------
A user id is needed

Example:
```
curl -X DELETE -i http://localhost:8080/rest/users/remove/101
```


