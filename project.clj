(defproject system2 "0.1.0-SNAPSHOT"
  :description "System2"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src"]
  :test-paths ["test"]
  :resource-paths ["config", "src/resources"]
  :dependencies [[ch.qos.logback/logback-classic "1.1.8" :exclusions [org.slf4j/slf4j-api]]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/jul-to-slf4j "1.7.22"]
                 [org.slf4j/jcl-over-slf4j "1.7.22"]
                 [org.slf4j/log4j-over-slf4j "1.7.22"]
                 [org.postgresql/postgresql "9.4-1206-jdbc42"]
                 [io.pedestal/pedestal.service "0.5.2"]
                 [io.pedestal/pedestal.jetty "0.5.2"]
                 [midje "1.8.3"]
                 [clj-time "0.13.0"]
                 [korma "0.4.3"]
                 [cumin "0.2.3"]
                 [buddy "1.0.0"]
                 [ragtime "0.6.3"]
                 [commons-io/commons-io "2.5"]
                 [com.fasterxml.jackson.core/jackson-databind "2.7.9"]
                 [com.fasterxml.jackson.core/jackson-core "2.7.9"]
                 [com.fasterxml.jackson.core/jackson-annotations "2.7.9"]
                 [clj-http "3.4.1"]
                 [com.novemberain/langohr "3.6.1"]]

  :main ^{:skip-aot true} system2.server

  :aliases {"testall" ["with-profile" "test" "midje"]
            "migrate"  ["run" "-m" "system2.migrations/migrate"]
            "rollback" ["run" "-m" "system2.migrations/rollback"]}

  :plugins [[lein-environ "1.1.0"]
            [lein-midje "3.1.3"]
            [lein-cloverage "1.0.9"]
            [s3-wagon-private "1.2.0"]]

  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "system2.server/run-dev"]}
                   :dependencies [[org.dbunit/dbunit "2.5.4"]
                                  [io.pedestal/pedestal.service-tools "0.5.2"]
                                  [midje "1.8.3"]]
                   :resource-paths ["test/resources"]
                   :env {:database-user "postgres"
                         :database-password "postgres"
                         :database-name "system2-dev"
                         :ragtime-db-spec "jdbc:postgresql://localhost:5432/system2-dev?user=postgres&password=postgres"
                         :reload-resources "true"
                         :kraken-broker-url "amqp://localhost"}}
              :test {:dependencies [[org.dbunit/dbunit "2.5.4"]
                                    [io.pedestal/pedestal.service-tools "0.5.2"]]
                     :resource-paths ["test/resources"]
                     :env {:database-user "postgres"
                           :database-password "postgres"
                           :database-name "system2-test"
                           :ragtime-db-spec "jdbc:postgresql://localhost:5432/system2-test?user=postgres&password=postgres"
                           :reload-resources "true"
                           :kraken-broker-url "amqp://localhost"}}
             :integration-test {:dependencies [[io.pedestal/pedestal.service-tools "0.5.2"]]
                                :resource-paths ["src/resources"]
                                :env {:database-user "postgres"
                                      :database-password "postgres"
                                      :database-name "system2"
                                      :database-host "postgres"
                                      :database-port "5433"
                                      :ragtime-db-spec "jdbc:postgresql://postgres:5433/system2?user=postgres&password=postgres"
                                      :reload-resources "true"
                                      :kraken-broker-url "amqp://localhost"}}
             :uberjar {:aot [system2.server]
                       :uberjar-name "system2.jar"}})
