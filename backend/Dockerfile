FROM eclipse-temurin:17-jre
ADD target/moodle_plus_plus-0.0.1-SNAPSHOT.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-jar","/myapp.jar"]
