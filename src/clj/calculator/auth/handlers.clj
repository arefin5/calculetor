(ns calculator.auth.handlers
  (:require [clojure.java.jdbc :as jdbc]
            [buddy.hashers :as hashers]
            [buddy.sign.jwt :as jwt]
            [ring.util.http-response :as response]
            [calculator.db.core :refer [*db*]]))

(def secret "my-secret-key")

(defn register [{:keys [params]}]
  (let [{:keys [username password]} params
        hashed-password (hashers/derive password)]
    (jdbc/insert! *db* :users {:username username :password hashed-password :status "active" :balance 100.0})
    (response/ok {:message "User registered successfully"})))

(defn login [{:keys [params]}]
  (let [{:keys [username password]} params
        user (first (jdbc/query *db* ["SELECT * FROM users WHERE username=?" username]))]
    (if (and user (hashers/check password (:password user)))
      (let [token (jwt/sign {:username username} secret)]
        (response/ok {:token token}))
      (response/unauthorized {:error "Invalid credentials"}))))

(defn token-validate [{:keys [headers]}]
  (let [token (get headers "authorization")]
    (try
      (let [decoded-token (jwt/unsign token secret)]
        (response/ok {:valid true :username (:username decoded-token)}))
      (catch Exception e
        (response/unauthorized {:error "Invalid token"})))))
