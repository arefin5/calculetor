(ns calculator.db.core
  (:require
    [next.jdbc.date-time]
    [next.jdbc.result-set]
    [conman.core :as conman]
    [mount.core :refer [defstate]]
    [calculator.config :refer [env]]))

(defstate ^:dynamic *db*
          :start (conman/connect! {:jdbc-url (env :database-url)})
          :stop (conman/disconnect! *db*))

(conman/bind-connection *db* "sql/queries.sql")

(extend-protocol next.jdbc.result-set/ReadableColumn
  java.sql.Timestamp
  (read-column-by-label [^java.sql.Timestamp v _]
    (.toLocalDateTime v))
  (read-column-by-index [^java.sql.Timestamp v _2 _3]
    (.toLocalDateTime v))
  java.sql.Date
  (read-column-by-label [^java.sql.Date v _]
    (.toLocalDate v))
  (read-column-by-index [^java.sql.Date v _2 _3]
    (.toLocalDate v))
  java.sql.Time
  (read-column-by-label [^java.sql.Time v _]
    (.toLocalTime v))
  (read-column-by-index [^java.sql.Time v _2 _3]
    (.toLocalTime v)))
(ns calculator.db.core
  (:require [clojure.java.jdbc :as jdbc]
            [mount.core :refer [defstate]]
            [conman.core :as conman]))

(defstate ^:dynamic *db*
  :start (conman/connect! {:jdbc-url "jdbc:sqlite:./calculator.db"})
  :stop (conman/disconnect! *db*))

(defn create-tables []
  (jdbc/execute! *db* ["CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username TEXT, password TEXT, status TEXT, balance REAL)"])
  (jdbc/execute! *db* ["CREATE TABLE IF NOT EXISTS operations (id INTEGER PRIMARY KEY, type TEXT, cost REAL)"])
  (jdbc/execute! *db* ["CREATE TABLE IF NOT EXISTS records (id INTEGER PRIMARY KEY, operation_id INTEGER, user_id INTEGER, amount REAL, user_balance REAL, operation_response TEXT, date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, deleted BOOLEAN DEFAULT FALSE)"]))
