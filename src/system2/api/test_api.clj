(ns system2.api.test-api
  (:require [clojure.walk :as walk]
            [clojure.tools.logging :as log]
            [system2.integration.system1-integration :as system1-integration]))

(defn create-test-in-system1
  [{:keys [json-params] :as request}]
  (system1-integration/send-system1-message json-params)
  {:status 200})
