FROM eclipse-temurin:17
RUN apt-get update
RUN apt-get install -y net-tools dnsutils telnet iputils-ping traceroute ca-certificates curl jq
WORKDIR /opt
EXPOSE 8080
COPY target/*.jar /opt/app.jar
CMD java -jar /opt/app.jar