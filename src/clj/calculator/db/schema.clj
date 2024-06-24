(ns calculator.db.schema
  (:require [calculator.db.core :refer [*db*]]
            [clojure.java.jdbc :as jdbc]))

(defn init-schema []
  (jdbc/db-do-commands *db*
    (jdbc/create-table-ddl :users
      [:id "INTEGER PRIMARY KEY"]
      [:username "TEXT"]
      [:password "TEXT"]
      [:status "TEXT"]
      [:balance "REAL"])
    (jdbc/create-table-ddl :operations
      [:id "INTEGER PRIMARY KEY"]
      [:type "TEXT"]
      [:cost "REAL"])
    (jdbc/create-table-ddl :records
      [:id "INTEGER PRIMARY KEY"]
      [:operation_id "INTEGER"]
      [:user_id "INTEGER"]
      [:amount "REAL"]
      [:user_balance "REAL"]
      [:operation_response "TEXT"]
      [:date "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]
      [:deleted "BOOLEAN DEFAULT FALSE"])))
