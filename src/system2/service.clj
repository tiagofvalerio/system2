(ns system2.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.ring-middlewares :as ring-mw]
            [ring.util.response :as ring-resp]
            [system2.api.test-api :refer [create-test-in-system1]]))

(defn home-page
  [request]
  (ring-resp/response {:message "pong"}))

(defroutes routes
  [[["/" {:get home-page}
     ^:interceptors [(body-params/body-params) http/json-body]

     ["/v1/create-test" ^:interceptors {:post create-test-in-system1}]]]])

(def service {:env :prod
              ::http/routes routes
              ;::http/allowed-origins ["http://localhost:3000"]
              ::http/resource-path "/public"
              ::http/type :jetty
              ::http/port 8080
              ::http/container-options {:h2c? true
                                        :h2? false
                                        :ssl? false}})
