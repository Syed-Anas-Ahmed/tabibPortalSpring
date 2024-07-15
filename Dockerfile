FROM tomcat:9.0-jre11-slim

# Remove default webapps
RUN rm -fr /usr/local/tomcat/webapps/*

# Copy WAR file
COPY target/tabib-2.6.2.war /usr/local/tomcat/webapps/tabib.war

# Expose HTTP and HTTPS ports
EXPOSE 8080

CMD ["catalina.sh", "run"]
