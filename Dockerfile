FROM openjdk:11

COPY backend ./
RUN ./gradlew clean
RUN ./gradlew build --no-daemon

EXPOSE 1901

ENTRYPOINT ["./gradlew","bootRun"]