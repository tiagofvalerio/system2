(ns system2.migrations
  (:require [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]
            [environ.core :refer [env]]))

(def db-spec (env :ragtime-db-spec))

(defn load-config
  []
  {:datastore  (jdbc/sql-database db-spec)
   :migrations (jdbc/load-resources "migrations")})

(defn migrate
  []
  (repl/migrate (load-config)))

(defn rollback
  []
  (repl/rollback (load-config)))
