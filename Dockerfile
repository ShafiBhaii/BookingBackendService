FROM openjdk:8-jre-alpine
COPY ./target/BookingBackendService-0.0.1-SNAPSHOT.jar /usr/src/bookingservice/
WORKDIR /usr/src/bookingservice/
EXPOSE 9007
CMD ["java","-jar","BookingBackendService-0.0.1-SNAPSHOT.jar"]