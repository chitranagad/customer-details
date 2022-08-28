FROM openjdk:8
EXPOSE 9090
ADD target/customer-details.jar customer-details.jar
ENTRYPOINT ["java", "-jar", "/customer-details.jar"]