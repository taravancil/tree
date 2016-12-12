(defproject tree "0.1.0-SNAPSHOT"
  :description "A recursive directory listing program"
  :url "https://github.com/taravancil/tree"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot tree.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
