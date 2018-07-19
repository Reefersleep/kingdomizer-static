(defproject kingdomizer-static "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript  "1.10.339" :scope "provided"]
                 [reagent "0.8.1"]
                 [reagent-utils "0.2.1"]
                 [com.degel/re-frame-storage-fx "0.1.0"]
                 [re-frame "0.10.2"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.16"]]

  :min-lein-version "2.5.0"
  :clean-targets ^{:protect false}
  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :source-paths ["src/"]
  :resource-paths ["public" "target/cljsbuild"]

  :cljsbuild {:builds {:app
                       {:source-paths ["src" "env/dev/cljs"]
                        :compiler
                        {:main "kingdomizer-static.dev"
                         :output-to "public/js/app.js"
                         :output-dir "public/js/out"
                         :asset-path   "js/out"
                         :source-map true
                         :optimizations :none
                         :pretty-print  true}
                        :figwheel
                        {:on-jsload "kingdomizer-static.core/mount-root"
                         :open-urls ["http://localhost:3449/index.html"]}}
                       :release
                       {:source-paths ["src" "env/prod/cljs"]
                        :compiler
                        {:output-to "public/js/app.js"
                         :output-dir "public/js/release"
                         :asset-path   "js/out"
                         :optimizations :advanced
                         :pretty-print false}}
                       :nodetest
                       {:source-paths ["src" "test/cljs/kingdomizer_static"]
                        :compiler {:output-to  "target/cljsbuild/test/js/testable.js"
                                   :output-dir "target/cljsbuild/test/js/"
                                   :main kingdomizer-static.doo-runner
                                   :optimizations :none
                                   :target :nodejs}}}}

  :figwheel {:http-server-root "."
             :nrepl-port 7002
             :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
             :css-dirs ["public/css"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.4"]
                                  [figwheel-sidecar "0.5.11"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [pjstadig/humane-test-output "0.8.2"]]

;;                   :jvm-opts ["--add-modules" "java.xml.bind"]

                   :plugins [[lein-figwheel "0.5.11"]
                             [lein-doo "0.1.8"]]}})
