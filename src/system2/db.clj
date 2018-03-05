(ns system2.db
    (:use korma.db)
    (:require [environ.core :refer [env]]))

(defdb db (postgres {:db (get env :database-name "system2")
                     :user (get env :database-user "postgres")
                     :password (get env :database-password "")
                     :host (get env :database-host "postgres")
                     :port (get env :database-port "5432")}))
