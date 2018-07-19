(ns ^:figwheel-no-load kingdomizer-static.dev
  (:require
    [kingdomizer-static.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
