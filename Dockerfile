FROM clojure
RUN mkdir system2
RUN curl -sSL -H 'Accept: application/vnd.github.v3.raw' https://api.github.com/repos/tiagofvalerio/system2/tarball/master | tar zx --strip-components 1 -C system2
RUN cd system2 && lein uberjar && cd ../
ADD system2/target/system2.jar /opt/system2/
WORKDIR "/opt/system2/"
CMD ["java","-jar","system2.jar"]
EXPOSE 8080
