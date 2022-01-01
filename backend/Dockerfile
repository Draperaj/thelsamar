FROM openjdk:11

COPY ./ ./
RUN ./gradlew clean
RUN ./gradlew build --no-daemon

ENTRYPOINT ["./gradlew","bootRun"]