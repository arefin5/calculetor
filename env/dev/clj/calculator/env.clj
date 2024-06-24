(ns calculator.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [calculator.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[calculator started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[calculator has shut down successfully]=-"))
   :middleware wrap-dev})
