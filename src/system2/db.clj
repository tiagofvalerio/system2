(ns system2.db
    (:use korma.db)
    (:require [environ.core :refer [env]]))

(defdb db (postgres {:db (get env :database-name "system2")
                     :user (get env :database-user "postgres")
                     :password (get env :database-password "postgres")
                     :host (get env :database-host "localhost")
                     :port 5432}))
