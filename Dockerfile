FROM clojure
ADD system2/target/system2.jar /opt/system2/
WORKDIR "/opt/system2/"
CMD ["java","-jar","system2.jar"]
EXPOSE 8080
