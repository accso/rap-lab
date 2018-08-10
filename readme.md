# Project Library

The project consists of two modules:

 * library-server: The application core is implemented as a JavaEE application.
 * library-client: The client application of the Library based on JavaFX.

The project uses maven as build system. You can build, test and run the application with it.

The library-server uses [arquillian](http://arquillian.org/) as test-framework, which allows to run tests in a JavaEE environment based on [wildfly application-server](http://wildfly.org/). To build and test the application use this command:
```
./mvnw clean package -P run-tests
```

With a integrated wildfly-plugin you can run an appication-server:
```
./mvnw package -P run-server
```
The server is started in the context of maven. To stop the server cancel the build (crtl+c).

With a running server the library-client can also be started with:
```
./mvnw package -P run-client
```
# reference-architectures-and-patterns
