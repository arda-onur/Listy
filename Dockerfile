FROM openjdk:21
LABEL authors="ardao"
EXPOSE 8080
ADD target/listy-0.0.1-SNAPSHOT.jar listy-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/listy-0.0.1-SNAPSHOT.jar"]