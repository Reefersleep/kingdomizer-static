(ns kingdomizer-static.randomization
  (:require [cljs.pprint :refer [pprint]]
            [kingdomizer-static.sets]
            [kingdomizer-static.util]))

(defn- =supply-type [value]
  (fn [{:keys [supply-type] :as pile}]
    (= value supply-type)))

(defn- pick-n-from-pool [n pool]
  (->> pool
       shuffle
       (take n)
       set))

(defn- pick-x-piles-among-sets [{:keys [picked-piles selected-number-of-piles pile-pool] :as instructions}]
  (let [available-kingdom-piles     (->> pile-pool
                                         (clojure.set/select (=supply-type :kingdom-cards)))
        available-piles             (clojure.set/difference available-kingdom-piles picked-piles)
        number-of-piles-yet-to-pick (- selected-number-of-piles (count picked-piles))]
    (->> (pick-n-from-pool number-of-piles-yet-to-pick
                           available-piles)
         (update instructions :picked-piles clojure.set/union))))

(defn- pick-bane-card [{:keys [pile-pool selected-piles]}]
  (let [bane-card-prospect (->> pile-pool
                                (clojure.set/select (=supply-type :kingdom-cards))
                                (#(clojure.set/difference % (set selected-piles)))
                                (clojure.set/select (fn find-nonsplit-piles
                                                      [{:keys [cards] :as pile}]
                                                      (= nil cards)))
                                (clojure.set/select (fn find-piles-at-cost-2-or-3-coins
                                                      [{{:keys [coins debt potions]} :cost :as pile}]
                                                      (and (not debt) (not potions)
                                                           (or (= 2 coins) (= 3 coins)))))
                                (pick-n-from-pool 1)
                                first)]
    (or bane-card-prospect
      {:error :out-of-potential-bane-cards})))

(defn- ?pick-bane-card [{:keys [picked-piles selected-sets pile-pool] :as instructions}]
  (if (->> picked-piles
           (clojure.set/select (fn [{:keys [pile-name] :as pile}]
                                 (= "Young Witch" pile-name)))
           seq)
    (let [{:keys [error] :as bane-card} (pick-bane-card {:pile-pool      pile-pool
                                                         :selected-piles picked-piles})]
      (if error
        (assoc instructions :error error)
        (-> instructions
            (update :picked-piles conj bane-card)
            (assoc :bane-card-pile bane-card))))
    instructions))

(defn- ?pick-0-2-events-and-landmarks [{:keys [desired-number-of-events-and-landmarks pile-pool] :as instructions}]
  (let [desired-number       (or desired-number-of-events-and-landmarks (rand-int 3))
        events-and-landmarks (->> pile-pool
                                  (clojure.set/select (fn is-event-or-landmark?
                                                        [{:keys [supply-type] :as pile}]
                                                        (#{:landmarks :events} supply-type))))]
    (->> (pick-n-from-pool desired-number
                           events-and-landmarks)
         (group-by :supply-type)
         (map (fn setify-vals
                [[k v]]
                [k (set v)]))
         (merge instructions))))

(defn- find-available-piles-from-sets [{:keys [full-pile-pool selected-sets] :as instructions}]
  (->> full-pile-pool
       (clojure.set/select (fn filter-piles-by-set [{:keys [belongs-to-set] :as pile}]
                             (selected-sets belongs-to-set)))
       (assoc instructions :pile-pool)))

(defn ?pick-platinum-and-colony [{:keys [picked-piles] :as instructions}]
  (if (= "Prosperity" (-> picked-piles
                          first
                          :belongs-to-set))
    (assoc instructions :platinum true
                        :colony   true)
    instructions))

(defn ?pick-obelisk-pile [{:keys [landmarks picked-piles] :as instructions}]
  (if (->> landmarks
           (clojure.set/select (fn filter-obelisk
                                 [{:keys [landmark-name] :as pile}]
                                 (= "Obelisk" landmark-name)))
           seq)
    (if-let [obelisk-pile-prospect (->> picked-piles
                                        (clojure.set/select (fn filter-piles-into-action-type
                                                              [{:keys [types] :as pile}]
                                                              ((set types) :action)))
                                        shuffle
                                        first)]
      (assoc instructions :obelisk-pile obelisk-pile-prospect)
      instructions)
    instructions))

(defn ?ensure-at-least-three-alchemy-piles [{:keys [selected-sets picked-piles pile-pool] :as instructions}]
  (cond-> instructions
    (selected-sets "Alchemy")
    (update :picked-piles clojure.set/union (let [all-alchemy-piles       (->> pile-pool
                                                                               (clojure.set/select (fn filter-into-kingdom-cards
                                                                                                     [{:keys [supply-type] :as pile}]
                                                                                                     (#{:kingdom-cards} supply-type)))
                                                                               (clojure.set/select (fn filter-into-alchemy-piles
                                                                                                     [{:keys [belongs-to-set] :as pile}]
                                                                                                     (#{"Alchemy"} belongs-to-set))))
                                                  picked-alchemy-piles    (->> picked-piles
                                                                               (clojure.set/select (fn filter-alchemy-piles
                                                                                                     [{:keys [belongs-to-set] :as pile}]
                                                                                                     (#{"Alchemy"} belongs-to-set))))
                                                  unpicked-alchemy-piles  (clojure.set/difference all-alchemy-piles picked-alchemy-piles)
                                                  number-of-piles-to-pick (- 3 (count picked-alchemy-piles))
                                                  picks                   (pick-n-from-pool number-of-piles-to-pick unpicked-alchemy-piles)]
                                              picks))))

(defn make-kingdom
  [instructions]
  (-> instructions
      find-available-piles-from-sets
      ?ensure-at-least-three-alchemy-piles
      pick-x-piles-among-sets
      ?pick-bane-card
      ?pick-0-2-events-and-landmarks
      ?pick-platinum-and-colony
      ?pick-obelisk-pile))

(defn- find-similar-pile [{pile-pool                                                    :pile-pool
                          selected-piles                                               :selected-piles ;;only the :pile part
                          {pile         :pile
                           display-data :display-data
                           :as          renderable-pile-to-be-swapped}                 :pile-to-be-swapped}]
  (let [available-piles (clojure.set/difference pile-pool selected-piles)]
    (if (seq available-piles)
      (if (-> renderable-pile-to-be-swapped
              :display-data
              :bane-card-pile)
        (pick-bane-card {:pile-pool      pile-pool
                         :selected-piles selected-piles})
        (-> (pick-n-from-pool 1 available-piles)
            first))
      {:error :unable-to-find-similar-pile})))

(defn swap-pile
  "Returns either an {:error something} map
  or a list of renderable piles
  with the designated renderable-pile-to-be-swapped
  swapped out for a similar pile."
  [{{:keys [pile-name] :as pile} :pile
    display-data                 :display-data
    :as                          renderable-pile-to-be-swapped}
   full-pile-pool
   selected-renderable-piles
   selected-sets]
  (let [selected-piles                   (->> selected-renderable-piles
                                              (map :pile)
                                              set)
        pile-pool                        (->> full-pile-pool
                                              (clojure.set/select (fn filter-piles [{:keys [belongs-to-set] :as pile}]
                                                                    (selected-sets belongs-to-set)))
                                              (clojure.set/select (=supply-type :kingdom-cards)))
        {:keys [error] :as similar-pile} (find-similar-pile {:pile-pool          pile-pool
                                                             :selected-piles     selected-piles
                                                             :pile-to-be-swapped renderable-pile-to-be-swapped})]
    (if error
      similar-pile
      (->> selected-renderable-piles
           (clojure.set/select #(not= pile-name
                                      (-> %
                                          :pile
                                          :pile-name)))
           (#(conj % {:pile similar-pile :display-data display-data}))
           (assoc {} :result)))))
