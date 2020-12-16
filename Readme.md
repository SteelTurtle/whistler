# Whistler

> Minimalistic back-end prototype for a Twitter-like service.

![Java Version][java-image]
![Build Status][spring-image]

**Whistler** is a work-in-progress version for a simplified Twitter clone. At the moment it is just a skeleton
permitting very few usage patterns. In spite of including a database model capable already of associating
users in hierarchies of followers/followee, the only functionality provided by the client-side API are those
related to the following operations:

- **creating new users**
- **getting a list of all the users on the system**
- **creating "whistles" (basically, "tweets") that can be seen by any user, by tagging a specific user in the
  text with an "@" mark.**
- **getting whistles from any user on the system**

Being a demonstrative prototype, at the moment all the following operational aspects of the API are left
unimplemented:

- security concerns (all the users on the system are not subject to any form of authentication or
  authorization process)
- error handling (the API does not enforce any consistency check on the data inserted by the user, neither
  provides customised error messages to the caller in case of a failed operation)
- Extensive unit and integration testing

The following picture shows the main data model of the service and the relations of the various data entities:
![](header.png)

## Installation and environment setup

#### Linux, Windows & OS X:

Before running this project on a local machine with any of these 3 OSs, you must install and configure the
following dependencies:

1. A Java SDK (version: 15 or newer). Any open source JDK, like [OpenJDK] will work.
2. If you are running or building the code without the support of an IDE (like IntelliJ, Eclipse, NetBeans),
   you will need the latest version of the [Maven] build tool (version 3.6.3 or newer)

#### Build process

Assuming the development environment was set correctly, you can invoke the complete build process for the
service platform by running the following command from the repository folder root:

```sh
$> mvn clean install
```

Another prerequisite, in case you want to run the Whistler service on containers and with **PostgresSQL** as
database server instead of the Spring Boot in-memory **H2** database, is to have a working installation of
[Docker][Docker] and [docker-compose][docker-compose]. The process to start Whistler is identical for any of
the three main OSs; from the root directory of the repository just run:

```sh
$> docker-compose up -d --build
```

_Notice that the before running the whistler API container, you must have run the preceding Maven command to
generate the API jar artifact at least once. The Whistler API container's Dockerfile looks for the
generated `whistler.jar` file in the `target/` directory upon execution._

## Usage example

The examples below refer to the use of the command line tool `HTTPie` (https://httpie.io/)
to interact with the server API. Of course any CLI utility like `curl`
will work just fine. Once the server is started, its minimal REST API can be used to execute the following
operations:

**Get a specific user:**

```sh
http GET localhost:9090/users/<USERNAME>
```

The output should be similar to the one below:

```sh
HTTP/1.1 200 OK
Content-Length: 81
Content-Type: application/json

{
    "id": "d5b288fd-7d52-4b76-aa1d-7c3bb4e6ecf8",
    "userName": "dummy_user1"
}
```

**Create a new user:**

```sh
echo '{"userName": "A_NEW_USER"}' | http POST localhost:9090/users
```

The output should be similar to the one below:

```sh
HTTP/1.1 200 OK
Content-Length: 81
Content-Type: application/json

{
    "id": "91e616f2-a6e8-4488-8856-99f0b354f51e",
    "userName": "SomeNewUser"
}
```

**Get whistles from a users OR whistles in which the user has been tagged with `@username`**
Let's use `dummy_user1` as an example:

```sh
http GET localhost:9090/whistles/dummy_user1
```

The output should be similar to the one below:

```sh
HTTP/1.1 200 OK
Content-Type: application/json
transfer-encoding: chunked

[
    {
        "id": "fcc76f55-0190-4eaa-81b7-a1597c68673b",
        "whistle": "@dummy_user1 balalalalala"
    },
    {
        "id": "b86fb1a5-e739-49f5-bf8d-63b737e3d157",
        "whistle": "From dummy_user_2: Hi @dummy_user1!"
    },
    {
        "id": "6e85399d-627b-42d4-b8e3-f12f6fd38506",
        "whistle": "From dummy_user3: @dummy_user1 lorem ipsum"
    }
]
```

**Create a whistle for a specific user**

In this example, we are using `dummy_user1` as reference:

```sh
echo '{"whistle": "lorem ipsum"}' | http POST localhost:9090/whistles/dummy_user1
```

The output should be similar to the one below:

```sh
HTTP/1.1 200 OK
Content-Length: 80
Content-Type: application/json

{
    "id": "2a06e411-aada-43a2-a7b7-0e5067baf54f",
    "whistle": "lorem ipsum"
}
```

## Release History

* 0.0.1
    * Work in progress
    
## Meta

Author – [DannyBoy](https://www.linkedin.com/in/dannyboy/) – Feel free to get in touch!

This repository is freely redistributable and not subject to any license.


[java-image]: https://img.shields.io/static/v1?label=Java&message=15&color=blue&style=flat-square

[spring-image]: https://img.shields.io/static/v1?label=Spring%20Boot&message=v2.4.1&color=green&style=flat-square

[Docker]: https://docs.docker.com/engine/install/

[docker-compose]: https://docs.docker.com/compose/install/

[OpenJDK]: https://openjdk.java.net/

[Maven]: https://maven.apache.org/