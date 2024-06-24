(ns calculator.auth-test
  (:require [clojure.test :refer :all]
            [calculator.auth :as auth]
            [calculator.db.core :refer [*db*]]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'calculator.db.core/*db*)
    (f)
    (mount/stop #'calculator.db.core/*db*)))

(deftest register-login-test
  (testing "User registration and login"
    (auth/register "test@example.com" "password123")
    (let [login-response (auth/login "test@example.com" "password123")]
      (is (some? (:token login-response))))))

(deftest token-validation-test
  (testing "Token validation"
    (auth/register "valid@example.com" "password123")
    (let [login-response (auth/login "valid@example.com" "password123")
          token (:token login-response)
          validation-response (auth/validate-token token)]
      (is (= "valid@example.com" (:username validation-response)))
      (is (= true (:valid validation-response))))))

(deftest insufficient-balance-test
  (testing "Insufficient balance for operation"
    (auth/register "lowbalance@example.com" "password123")
    ;; Assuming new users start with a balance less than the cost of random_string
    (let [login-response (auth/login "lowbalance@example.com" "password123")
          token (:token login-response)
          user-id (:id (auth/validate-token token))]
      (is (= 0 (:balance (auth/get-user user-id))))
      (let [response (handlers/random-string user-id {})]
        (is (= 403 (:status response)))
        (is (= "Insufficient balance" (:error (:body response))))))))
