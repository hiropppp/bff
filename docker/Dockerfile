#FROM gradle:7.5.1-jdk17 as builder
#COPY --chown=gradle:gradle ./ ./
#RUN gradle build -i --no-deamon -x check

FROM amazoncorretto:17.0.4
#COPY --from=builder /home/gradle/build/libs/*.jar /app/webapi.jar

ARG JAR_FILE="*.jar"
COPY ${JAR_FILE} /bin/webapi.jar

RUN ls -l /bin

ENTRYPOINT ["java","-jar","/bin/webapi.jar"]
#ENTRYPOINT ["java","-javaagent:/app/newrelic.jar","-jar","/app/webapi.jar"]