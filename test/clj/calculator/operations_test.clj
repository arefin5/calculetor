(ns calculator.operations-test
  (:require [clojure.test :refer :all]
            [calculator.operations :refer :all]
            [calculator.db.core :refer [*db*]]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'calculator.db.core/*db*)
    (f)
    (mount/stop #'calculator.db.core/*db*)))

(deftest addition-test
  (testing "Addition operation"
    (let [result (addition 1 {:a 2 :b 3})]
      (is (= 5 (:result (:body result)))))))

(deftest subtraction-test
  (testing "Subtraction operation"
    (let [result (subtraction 1 {:a 5 :b 3})]
      (is (= 2 (:result (:body result)))))))

(deftest multiplication-test
  (testing "Multiplication operation"
    (let [result (multiplication 1 {:a 2 :b 3})]
      (is (= 6 (:result (:body result)))))))

(deftest division-test
  (testing "Division operation"
    (let [result (division 1 {:a 6 :b 3})]
      (is (= 2 (:result (:body result)))))))

(deftest square-root-test
  (testing "Square root operation"
    (let [result (square-root 1 {:a 9})]
      (is (= 3 (:result (:body result)))))))

;; For the random string, you would need to mock the third-party API
(deftest random-string-test
  (testing "Random string generation"
    (let [result (random-string 1 {})]
      (is (some? (:result (:body result)))))))
(ns calculator.operations-test
  (:require [clojure.test :refer :all]
            [calculator.operations :refer :all]
            [calculator.db.core :refer [*db*]]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'calculator.db.core/*db*)
    (f)
    (mount/stop #'calculator.db.core/*db*)))

(deftest addition-test
  (testing "Addition operation"
    (let [result (addition 1 {:a 2 :b 3})]
      (is (= 5 (:result (:body result)))))))

(deftest subtraction-test
  (testing "Subtraction operation"
    (let [result (subtraction 1 {:a 5 :b 3})]
      (is (= 2 (:result (:body result)))))))

(deftest multiplication-test
  (testing "Multiplication operation"
    (let [result (multiplication 1 {:a 2 :b 3})]
      (is (= 6 (:result (:body result)))))))

(deftest division-test
  (testing "Division operation"
    (let [result (division 1 {:a 6 :b 3})]
      (is (= 2 (:result (:body result)))))))

(deftest square-root-test
  (testing "Square root operation"
    (let [result (square-root 1 {:a 9})]
      (is (= 3 (:result (:body result)))))))

;; For the random string, you would need to mock the third-party API
(deftest random-string-test
  (testing "Random string generation"
    (let [result (random-string 1 {})]
      (is (some? (:result (:body result)))))))
