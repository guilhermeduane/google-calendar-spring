FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN

COPY pom.xml /tmp/
COPY api /tmp/api/
COPY calendar /tmp/calendar
COPY domain /tmp/domain/
COPY api /tmp/api/
COPY config /tmp/config/
COPY data /tmp/data/


WORKDIR /tmp/
RUN mvn clean install -Pdocker

WORKDIR /tmp/calendar
EXPOSE 8080
EXPOSE 3306
EXPOSE 8888
EXPOSE 5005
ENTRYPOINT mvn spring-boot:run -o -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005 -Xms2048m -Xmx4096m"
