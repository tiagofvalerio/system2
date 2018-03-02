FROM clojure
RUN lein with-profile integration-test migrate
RUN lein uberjar
ADD target/system2.jar /opt/system2/
WORKDIR "/opt/system2/"
CMD ["java","-jar","system2.jar"]
EXPOSE 8080
