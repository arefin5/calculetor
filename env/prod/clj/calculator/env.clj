(ns calculator.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[calculator started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[calculator has shut down successfully]=-"))
   :middleware identity})
