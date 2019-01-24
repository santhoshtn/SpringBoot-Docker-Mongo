FROM openjdk:11
ADD ./target/muzixapplication-0.0.1-SNAPSHOT.jar /usr/src/muzixapplication-0.0.1-SNAPSHOT.jar
EXPOSE 8080
WORKDIR usr/src
ENTRYPOINT ["java","-jar","muzixapplication-0.0.1-SNAPSHOT.jar"]
