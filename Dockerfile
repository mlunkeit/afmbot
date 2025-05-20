FROM eclipse-temurin:23 AS build

WORKDIR /build
COPY . .
RUN ./gradlew shadowJar

FROM eclipse-temurin:23-jre AS runtime

WORKDIR /app
COPY --from=build /build/build/libs/afmbot-*-all.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]