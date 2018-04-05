FROM openjdk:10-jdk-slim AS build
COPY . /src
WORKDIR /src
RUN ["./gradlew", "build"]

FROM openjdk:10-jre-slim AS production
ARG version
EXPOSE 8080
RUN mkdir -p /app/
COPY --from=build /src/build/libs/src-0.0.1-SNAPSHOT.jar /app/gl-list.jar
ENTRYPOINT ["java", "-XX:+PrintFlagsFinal", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/app/gl-list.jar"]