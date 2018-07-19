(ns kingdomizer-static.util
  (:require [cljs.pprint]))

(defn pprinturn
  ([x]
   (cljs.pprint/pprint x)
   x)
  ([message x]
   (prn message)
   (cljs.pprint/pprint x)
   x))

(defn single [coll]
  {:pre [(= 1 (count coll))]}
  (first coll))
