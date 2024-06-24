FROM openjdk:8-alpine

COPY target/uberjar/calculator.jar /calculator/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/calculator/app.jar"]
