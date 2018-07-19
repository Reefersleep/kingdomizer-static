(ns kingdomizer-static.randomization-test
  (:require [cljs.test :refer-macros [is are deftest testing use-fixtures]]
            [kingdomizer-static.randomization]
            [kingdomizer-static.util]
            [kingdomizer-static.sets]))

(deftest pick-valid-obelisk-pile
  (let [ten-action-piles (->> kingdomizer-static.sets/flattened-piles
                              (clojure.set/select (fn filter-desired-piles
                                                    [{:keys [pile-name] :as pile}]
                                                    (#{"Cellar"
                                                       "Chapel"
                                                       "Moat"
                                                       "Harbinger"
                                                       "Merchant"
                                                       "Vassal"
                                                       "Village"
                                                       "Workshop"
                                                       "Bureaucrat"
                                                       "Enchantress"}
                                                     pile-name))))
        landmarks        (->> kingdomizer-static.sets/flattened-piles
                              (clojure.set/select (fn filter-desired-piles
                                                    [{:keys [landmark-name] :as pile}]
                                                    (#{"Obelisk"} landmark-name))))
        instructions     {:picked-piles ten-action-piles
                          :landmarks    landmarks}
        result           (->> instructions
                              kingdomizer-static.randomization/?pick-obelisk-pile
                              :obelisk-pile)]
    (is (int? result))))

(deftest can-not-pick-valid-obelisk-pile-due-to-no-action-piles-available
  (let [ten-non-action-piles (->> kingdomizer-static.sets/flattened-piles
                                  (clojure.set/select (fn filter-desired-piles
                                                        [{:keys [pile-name] :as pile}]
                                                        (#{"Coins Of The Realm"
                                                           "Bank"
                                                           "Treasure Trove"
                                                           "Philosopher's Stone"
                                                           "Royal Seal"
                                                           "Hoard"
                                                           "Loan"
                                                           "Horn Of Plenty"
                                                           "Ill-gotten Gains"
                                                           "Talisman"}
                                                         pile-name))))
        landmarks            (->> kingdomizer-static.sets/flattened-piles
                                  (clojure.set/select (fn filter-desired-piles
                                                        [{:keys [landmark-name] :as pile}]
                                                        (#{"Obelisk"} landmark-name))))
        instructions         {:picked-piles ten-non-action-piles
                              :landmarks    landmarks}
        result               (->> instructions
                                  kingdomizer-static.randomization/?pick-obelisk-pile
                                  :obelisk-pile)]
    (is (nil? result))))

(deftest can-pick-valid-obelisk-pile-with-only-one-action-card-pile-available
  (let [nine-treasure-piles-and-one-action-pile
        (->> kingdomizer-static.sets/flattened-piles
             (clojure.set/select (fn filter-desired-piles
                                   [{:keys [pile-name] :as pile}]
                                   (#{"Village"
                                      "Bank"
                                      "Treasure Trove"
                                      "Philosopher's Stone"
                                      "Royal Seal"
                                      "Hoard"
                                      "Loan"
                                      "Horn Of Plenty"
                                      "Ill-gotten Gains"
                                      "Talisman"}
                                    pile-name))))
        landmarks    (->> kingdomizer-static.sets/flattened-piles
                          (clojure.set/select (fn filter-desired-piles
                                                [{:keys [landmark-name] :as pile}]
                                                (#{"Obelisk"} landmark-name))))
        instructions {:picked-piles nine-treasure-piles-and-one-action-pile
                      :landmarks    landmarks}
        result       (->> instructions
                          kingdomizer-static.randomization/?pick-obelisk-pile
                          :obelisk-pile)]
    (is (map? result))))

(deftest add-3-alchemy-piles-when-none-are-picked
  (let [result (kingdomizer-static.randomization/?ensure-at-least-three-alchemy-piles {:selected-sets #{"Alchemy"
                                                                                             "Dominion Second Edition"
                                                                                             "Intrigue Second Edition"
                                                                                             "Prosperity"
                                                                                             "Seaside"
                                                                                             "Cornucopia"
                                                                                             "Hinterlands"
                                                                                             "Dark Ages"
                                                                                             "Guilds"
                                                                                             "Adventures"
                                                                                             "Empires"
                                                                                             "Nocturne"}
                                                                            :pile-pool kingdomizer-static.sets/flattened-piles
                                                                            :picked-piles nil})
        number-of-alchemy-piles (->> result
                                     :picked-piles
                                     (clojure.set/select (fn filter-alchemy-piles
                                                           [{:keys [belongs-to-set] :as pile}]
                                                           (#{"Alchemy"} belongs-to-set)))
                                     count)]
    (is (= 3 number-of-alchemy-piles))))

(deftest dont-add-alchemy-piles-when-alchemy-is-not-picked
  (let [result (kingdomizer-static.randomization/?ensure-at-least-three-alchemy-piles {:selected-sets #{"Prosperity"}
                                                                            :pile-pool kingdomizer-static.sets/flattened-piles
                                                                            :picked-piles nil})
        number-of-alchemy-piles (->> result
                                     :picked-piles
                                     (clojure.set/select (fn filter-alchemy-piles
                                                           [{:keys [belongs-to-set] :as pile}]
                                                           (#{"Alchemy"} belongs-to-set)))
                                     count)]
    (is (= 0 number-of-alchemy-piles))))

(deftest dont-add-alchemy-piles-when-3-alchemy-piles-are-already-picked
  (let [pre-picked-piles (->> kingdomizer-static.sets/flattened-piles
                              (clojure.set/select (fn [{:keys [belongs-to-set]}]
                                                    (#{"Alchemy"} belongs-to-set)))
                              (take 3)
                              set)
        result (kingdomizer-static.randomization/?ensure-at-least-three-alchemy-piles {:selected-sets #{"Alchemy"}
                                                                            :pile-pool kingdomizer-static.sets/flattened-piles
                                                                            :picked-piles pre-picked-piles})
        alchemy-piles (->> result
                           :picked-piles
                           (clojure.set/select (fn filter-alchemy-piles
                                                 [{:keys [belongs-to-set] :as pile}]
                                                 (#{"Alchemy"} belongs-to-set))))]
    (is (= pre-picked-piles alchemy-piles))))

(deftest add-2-Alchemy-piles-when-1-is-pre-picked
  (let [pre-picked-piles (->> kingdomizer-static.sets/flattened-piles
                              (clojure.set/select (fn [{:keys [belongs-to-set]}]
                                                    (#{"Alchemy"} belongs-to-set)))
                              (take 1)
                              set)
        result (kingdomizer-static.randomization/?ensure-at-least-three-alchemy-piles {:selected-sets #{"Alchemy"}
                                                                            :pile-pool kingdomizer-static.sets/flattened-piles
                                                                            :picked-piles pre-picked-piles})
        alchemy-piles (->> result
                           :picked-piles
                           (clojure.set/select (fn filter-alchemy-piles
                                                 [{:keys [belongs-to-set] :as pile}]
                                                 (#{"Alchemy"} belongs-to-set))))
        number-of-alchemy-piles (count alchemy-piles)]
    (is (clojure.set/subset? pre-picked-piles alchemy-piles))
    (is (= 3 number-of-alchemy-piles))))
