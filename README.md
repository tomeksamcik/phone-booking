# Read Me First
This project has been implemented as a test assignment
- It's based on Spring Boot web starter
- It doesn't use any persistence store, it stores its data in collections for maximum simplicity and to limit the use of libraries
- It's not thread-safe, so may present some issues with concurrent requests
- Getters & setters are mainly used for serializing/deserializing (it likely may be addressed differently, but would require some investigation)
- All relevant comments are in the code
- UML diagram in phone-booking.png file has been created with UML Generator IntelliJ plugin
# Getting Started
## Building & Testing
```
mvn clean test
```
## Running
```
mvn spring-boot:run
```
## Example calls
### Getting phones
```
> GET /phones HTTP/1.1
> Host: localhost:8080
> User-Agent: insomnia/9.3.2
> Accept: */*

* Mark bundle as not supporting multiuse

< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Tue, 09 Jul 2024 10:19:28 GMT
[{"id":2,"name":"Samsung Galaxy S8"},{"id":3,"name":"Samsung Galaxy S8"},{"id":4,"name":"Motorola Nexus 6"},{"id":5,"name":"Oneplus 9"},{"id":6,"name":"Apple iPhone 11"},{"id":7,"name":"Apple iPhone 12"},{"id":8,"name":"Apple iPhone 13"},{"id":9,"name":"iPhone X"},{"id":10,"name":"Nokia 3310"},{"id":1,"name":"Samsung Galaxy S9"}]
```
### Creating a booking
```
> POST /bookings HTTP/1.1
> Host: localhost:8080
> Content-Type: application/json
> User-Agent: insomnia/9.3.2
> Accept: */*
> Content-Length: 100

| {
| 	"phone": {
| 		"id": 4
| 	},
| 	"user": {
| 		"firstName": "test",
| 		"lastName": "test"
| 	}
| }

* Mark bundle as not supporting multiuse

< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Tue, 09 Jul 2024 10:20:09 GMT
{"phone":{"id":4,"name":"Motorola Nexus 6"},"user":{"firstName":"test","lastName":"test"},"createdAt":"2024-07-09T12:20:09.548093"}
```
### Getting bookings
```
> GET /bookings HTTP/1.1
> Host: localhost:8080
> User-Agent: insomnia/9.3.2
> Accept: */*

* Mark bundle as not supporting multiuse

< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Tue, 09 Jul 2024 10:20:14 GMT
[{"phone":{"id":1,"name":"Samsung Galaxy S9"},"user":{"firstName":"test","lastName":"test"},"createdAt":"2024-07-09T12:19:54.17755"},{"phone":{"id":4,"name":"Motorola Nexus 6"},"user":{"firstName":"test","lastName":"test"},"createdAt":"2024-07-09T12:20:09.548093"}]
```
### Canceling a booking
```
> DELETE /bookings HTTP/1.1
> Host: localhost:8080
> Content-Type: application/json
> User-Agent: insomnia/9.3.2
> Accept: */*
> Content-Length: 87

| {
| 	"phone": {
| 		"id": 4
| 	},
| 	"user": {
| 		"firstName": "test",
| 		"lastName": "test"
| 	}
| }

* Mark bundle as not supporting multiuse

< HTTP/1.1 200 
< Content-Length: 0
< Date: Tue, 09 Jul 2024 10:19:03 GMT
```
