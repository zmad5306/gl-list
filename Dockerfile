FROM openjdk:10-jre-slim
ARG version
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/gl-list-${version}.jar /app/gl-list.jar
ENTRYPOINT ["java", "-XX:+PrintFlagsFinal", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/app/gl-list.jar"]