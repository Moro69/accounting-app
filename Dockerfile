FROM openjdk:11
COPY target/accountingg-0.0.1-SNAPSHOT.jar accountingg.jar
ENTRYPOINT ["java","-jar","/accountingg.jar"]