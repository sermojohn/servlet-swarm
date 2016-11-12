# servlet-swarm
A wildfly-swarm example with Undertow and Spring 3.1

This example demos the asynchronous Servlet API and the use of RxJava for async response generation.

# run with
mvn package -Prun


# available servlet calls
curl http://localhost:8080/sync
curl http://localhost:8080/async
curl http://localhost:8080/reactive

