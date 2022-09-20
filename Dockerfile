FROM openjdk:11
EXPOSE 8080
# RUN ./gradlew jar
COPY ./build/libs/mockdacs-0.0.1.jar app.jar
ENTRYPOINT ["java", "-Duser.timezone=GMT+7", "-jar", "app.jar"]