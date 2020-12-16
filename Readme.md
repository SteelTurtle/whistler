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

Another prerequisite, in case you want to run the Whistler service on containers, is to have a working
installation of
[Docker][Docker] and [docker-compose][docker-compose]. The process to start Whistler is identical for any of
the three main OSs; from the root directory of the repository just run:

```sh
docker-compose up -d --build
```

_Notice that the before running the whistler API container, you must have run the preceding Maven command to
generate the API jar artifact at least once. The Whistler API container's Dockerfile looks for the
generated `whistler.jar` file in the `target/` directory upon execution._

## Usage example

A few motivating and useful examples of how your product can be used. Spice this up with code blocks and
potentially more screenshots.

_For more examples and usage, please refer to the [Wiki][wiki]._

## Development setup

Describe how to install all development dependencies and how to run an automated test-suite of some kind.
Potentially do this for multiple platforms.

```sh
make install
npm test
```

## Release History

* 0.0.1
    * Work in progress

## Meta

Your Name – [@YourTwitter](https://twitter.com/dbader_org) – YourEmail@example.com

Distributed under the XYZ license. See ``LICENSE`` for more information.

[https://github.com/yourname/github-link](https://github.com/dbader/)

## Contributing

1. Fork it (<https://github.com/yourname/yourproject/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request

<!-- Markdown link & img dfn's -->

[java-image]: https://img.shields.io/static/v1?label=Java&message=15&color=blue&style=flat-square

[spring-image]: https://img.shields.io/static/v1?label=Spring%20Boot&message=v2.4.1&color=green&style=flat-square

[Docker]: https://docs.docker.com/engine/install/

[docker-compose]: https://docs.docker.com/compose/install/

[OpenJDK]: https://openjdk.java.net/

[Maven]: https://maven.apache.org/