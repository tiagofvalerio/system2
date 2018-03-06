(ns system2.integration.system1-integration
  (:require [langohr.core      :as rmq]
            [langohr.channel   :as lch]
            [langohr.queue     :as lq]
            [langohr.consumers :as lc]
            [langohr.basic     :as lb]))

(def ^{:const true}
  default-exchange-name "")

(defn send-system1-message
  [message]
  (prn "********************************")
  (prn "[BEGIN] start-integration")
  (let [conn  (rmq/connect {:host "amqp://rabbitmq" :username "guest" :password "guest"})
        ch    (lch/open conn)
        qname "system2queue"]
    (println (format "[main] Connected. Channel id: %d" (.getChannelNumber ch)))
    (lq/declare ch qname {:exclusive false :auto-delete true})
    (lb/publish ch default-exchange-name qname message {:content-type "text/plain" :type "test"})))


(get env :database-host "postgres")
