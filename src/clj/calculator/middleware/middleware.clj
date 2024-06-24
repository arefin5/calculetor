(ns calculator.middleware
  (:require [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [buddy.auth.middleware :refer [wrap-authentication]]
            [buddy.auth.backends.token :refer [jws-backend]]
            [calculator.auth.handlers :refer [secret]]))

(def auth-backend (jws-backend {:secret secret}))

(defn wrap-base [handler]
  (-> handler
      wrap-params
      wrap-json-body
      wrap-json-response
      (wrap-authentication auth-backend)))
