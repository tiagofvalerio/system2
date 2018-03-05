FROM clojure as system2build
WORKDIR /opt/system2
RUN curl -sSL -H 'Accept: application/vnd.github.v3.raw' https://api.github.com/repos/tiagofvalerio/system2/tarball/master | tar zx --strip-components 1
RUN lein uberjar


FROM openjdk:jre-alpine
RUN pwd && ls
WORKDIR /opt/system2
COPY --from=system2build /opt/system2/target/system2.jar .
CMD ["java","-jar","system2.jar"]
EXPOSE 8080
