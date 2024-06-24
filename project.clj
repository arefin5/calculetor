(defproject calculator "0.1.0-SNAPSHOT"
  :description "Your project description"
  :url "http://example.com"

  ;; Define project dependencies
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [buddy/buddy-auth "3.0.323"]
                 [buddy/buddy-core "1.10.413"]
                 ;; Add all your dependencies here
                 [ring/ring-core "1.9.6"]
                 [ring/ring-defaults "0.3.4"]
                 [ring/ring-devel "1.9.6"]
                 [org.xerial/sqlite-jdbc "3.36.0.3"]
                 ;; Add any other dependencies your project requires
                 ]

  ;; Define project profiles (e.g., for dev, test, uberjar)
  :profiles {:dev {:dependencies [[com.jakemccrary/lein-test-refresh "0.24.1"]
                                  [pjstadig/humane-test-output "0.11.0"]
                                  [ring/ring-devel "1.9.6"]
                                  [ring/ring-mock "0.4.0"]]
                   :resource-paths ["env/dev/resources"]
                   :jvm-opts ["-Dconf=dev-config.edn"]
                   :source-paths ["env/dev/clj"]}

             :test {:dependencies [[pjstadig/humane-test-output "0.11.0"]]
                    :resource-paths ["env/test/resources"]
                    :jvm-opts ["-Dconf=test-config.edn"]}

             :uberjar {:aot :all
                       :omit-source true
                       :source-paths ["env/prod/clj"]
                       :resource-paths ["env/prod/resources"]
                       :main calculator.core
                       :uberjar-name "calculator.jar"}}

  :main calculator.core)
