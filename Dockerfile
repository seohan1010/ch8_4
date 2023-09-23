FROM openjdk:11

RUN mkdir /app
WORKDIR /app

ADD ./build/libs/*.jar /app/app.jar

EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar"]