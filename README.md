# Spring - School Management API

## Overview

This application is designed to provide API for managing educational institutions using the Spring framework.

## Requirements

* [JDK 17.0.2](https://jdk.java.net/archive/)
* [Maven 3.9.6](https://maven.apache.org/download.cgi)

## Configuration

1. Go to project main folder.
2. Run "mvn clean install" command.
3. Go to "targer" folder.
4. Run the generated .jar file, using for example "java -jar file_name.jar" command.

## REST API

### Create a new Address

#### Request

`POST /api/address`

#### Body

```
{
    "houseNumber":"13",
    "street":"Uśmiechu",
    "city":"Lublin",
    "zipCode":"25-250"
}
```

#### Response

```
HTTP/1.1 201 
connection: keep-alive
content-length: 0
date: Mon, 15 Jan 2024 13:01:01 GMT
keep-alive: timeout=60
```

### Get all Addresses

#### Request

`GET /api/address`

#### Response

```
HTTP/1.1 200 
connection: keep-alive
content-type: application/json
date: Mon, 15 Jan 2024 13:01:58 GMT
keep-alive: timeout=60
transfer-encoding: chunked

[{"id":1,"houseNumber":"13","street":"Uśmiechu","city":"Lublin","zipCode":"25-250"}]
```

### Create a new Teacher

#### Request

`POST /api/teacher`

#### Body

```
{
    "firstName":"Jan",
    "lastName":"Kowal",
    "phone":"345765876",
    "pesel":"85121494537"
}
```

#### Response

```
HTTP/1.1 201 
connection: keep-alive
content-length: 0
date: Mon, 15 Jan 2024 13:03:12 GMT
keep-alive: timeout=60
```

### Get Teacher by ID

#### Request

`GET /api/teacher/1`

#### Response

```
HTTP/1.1 200 
connection: keep-alive
content-type: application/json
date: Mon, 15 Jan 2024 13:04:52 GMT
keep-alive: timeout=60
transfer-encoding: chunked

{"id":1,"firstName":"Jan","lastName":"Kowal","pesel":"85121494537","phone":"345765876","supervisedClass":null}
```

### Create a new Classroom

#### Request

`POST /api/classroom`

#### Body

```
{
    "symbol":"A1",
    "supervisorId":1
}
```

#### Response

```
HTTP/1.1 201 
connection: keep-alive
content-length: 0
date: Mon, 15 Jan 2024 13:05:57 GMT
keep-alive: timeout=60
```

### Get Classroom by ID

#### Request

`GET /api/classroom/1`

#### Response

```
HTTP/1.1 200 
connection: keep-alive
content-type: application/json
date: Mon, 15 Jan 2024 13:06:34 GMT
keep-alive: timeout=60
transfer-encoding: chunked

{"id":1,"symbol":"A1","studentList":[],"supervisorId":1}
```

### Create a new Student

#### Request

`POST /api/student`

#### Body

```
{
    "firstName":"Adam",
    "lastName":"Nowak",
    "pesel":"05232405578",
    "addressId":1
}
```

#### Response

```
HTTP/1.1 201 
connection: keep-alive
content-length: 0
date: Mon, 15 Jan 2024 13:09:17 GMT
keep-alive: timeout=60
```

### Get Student by ID

#### Request

`GET /api/student/1`

#### Response

```
HTTP/1.1 200 
connection: keep-alive
content-type: application/json
date: Mon, 15 Jan 2024 13:09:49 GMT
keep-alive: timeout=60
transfer-encoding: chunked

{"id":1,"firstName":"Adam","lastName":"Nowak","pesel":"05232405578","studentNumber":"0","address":{"id":1,"houseNumber":"13","street":"Uśmiechu","city":"Lublin","zipCode":"25-250"},"classroomId":null}
```

### Add Student to Classroom

#### Request

`PUT /api/student/1/classroom/1`

#### Response

```
HTTP/1.1 200 
connection: keep-alive
content-length: 0
date: Mon, 15 Jan 2024 13:12:52 GMT
keep-alive: timeout=60
```

### Update Teacher data

#### Request

`PUT /api/teacher/1`

#### Body

```
{
    "firstName":"Jan",
    "lastName":"Kowalski",
    "phone":"345765876"
}
```

#### Response

```
HTTP/1.1 200 
connection: keep-alive
content-length: 0
date: Mon, 15 Jan 2024 13:14:21 GMT
keep-alive: timeout=60
```
