FROM openjdk:8
EXPOSE 8080
ADD target/customer-details.jar customer-details.jar
ENTRYPOINT ["java", "-jar", "/customer-details.jar"]