(ns calculator.routes.services
  (:require [calculator.auth.handlers :refer [register login token-validate]]
            [reitit.ring :as ring]
            [ring.util.http-response :as response]))

(defn services-routes []
  (ring/ring-handler
   (ring/router
    [["/api/register" {:post register}]
     ["/api/login" {:post login}]
     ["/api/token-validate" {:get token-validate}]])
   (ring/create-default-handler)))
