(ns kingdomizer-static.core
  (:require [reagent.core]
            [re-frame.core]
            [goog.dom]
            [kingdomizer-static.sets]
            [kingdomizer-static.events]
            [kingdomizer-static.subs]
            [kingdomizer-static.util]))

(defn add-react-keys
  "Assigns unique 'key' props to the 'components' seq elements.
  React gives a 'warning' if each element of a seq does not have a unique 'key' prop. "
  [components]
  (map (fn [component]
         (with-meta component {:key (random-uuid)}))
       components))

(defn get-value
  [node]
  (-> node
      .-target
      .-value))

(defn capitalize-words
  "Capitalize every word in a string
  Shamelessly stolen from https://clojuredocs.org/clojure.string/capitalize"
  [s]
  (->> (clojure.string/split s #"\b")
       (map clojure.string/capitalize)
       clojure.string/join))

(defn kw->label [kw]
  (-> kw
      name
      (clojure.string/replace #"-" " ")
      capitalize-words))

(defn sort-select []
  [:select
   {:on-change (fn [node]
                 (let [sort-method (->> node
                                        get-value)]
                   (re-frame.core/dispatch [:set-sort-method sort-method])))
    :style     {:cursor :pointer}}
   [:option {:value "set"}  "Set"]
   [:option {:value "name"} "Card name"]
   [:option {:value "cost"} "Cost"]])

(defn get-viewport-width []
  (let [window (goog.dom/getWindow)
        viewport-size (goog.dom/getViewportSize window)
        viewport-width (.-width viewport-size)]
    viewport-width))

(def card-banner-background-edge  "rgb(112, 129, 126)")
(def dominion-box-rim "rgb(144, 136, 117)")
(def card-textfield-background "rgb(175, 176, 170)")
(def events-banner-background "rgb(157, 171, 156)")
(def landmarks-banner-background "rgb(78, 168, 104)")

(defn nbsp []
  [:div {:dangerouslySetInnerHTML {:__html "&nbsp;"}}])

(defn render-pipe [color]
  (if (nil? color)
    nil
    (let [width 3]
      [:div {:style {:background-color color
                     :min-height       "100%"
                     :border-right     "1px solid grey"
                     :align-self       :stretch
                     :min-width        width
                     :max-width        width}}])))
(defn set-name->set-icon [set-name]
  (-> set-name
      .toLowerCase
      (clojure.string/replace #" " "_")
      (str "_set_icon.png")))

(defn render-pile [{{:keys [pile-name types cost belongs-to-set] :as pile}         :pile
                    {:keys [bane-card-pile obelisk-card-pile]    :as display-data} :display-data
                    :as                                                            renderable-pile}]
  (let [type-colors               (->> types
                                       (map (fn [t]
                                              [render-pipe (case t
                                                             :reaction :deepskyblue
                                                             :treasure :gold
                                                             :victory  :lime
                                                             :duration :darkorange
                                                             :night    :black
                                                             nil)]))
                                       (filter (comp not nil?))
                                       add-react-keys)
        left-part-of-pile-banner  (if (seq type-colors)
                                    type-colors
                                    [nbsp])
        right-part-of-pile-banner [:div {:style {:display         :flex
                                                 :width           "100%"
                                                 :padding-right   5
                                                 :justify-content :flex-end}}
                                   (when bane-card-pile    "Bane")
                                   (when (and bane-card-pile obelisk-card-pile) " ")
                                   (when obelisk-card-pile "Obelisk")
                                   [:button {:on-click #(re-frame.core/dispatch [:swap-pile renderable-pile])
                                             :disabled @(re-frame.core/subscribe [:no-sets-selected?])
                                             :style    {:cursor :pointer}}
                                    [:img {:src      "images/ic_autorenew_black_24px.svg"
                                           :title    (if @(re-frame.core/subscribe [:no-sets-selected?])
                                                       "You have to select sets before you can swap piles."
                                                       "Swap pile")
                                           :style    {:opacity (if @(re-frame.core/subscribe [:no-sets-selected?])
                                                                 0.2
                                                                 1)}}]]
                                   [:img {:src    (str "images/"
                                                       (set-name->set-icon belongs-to-set))
                                          :title  belongs-to-set
                                          :height 15
                                          :width  15
                                          :style  {:padding-left 5
                                                   :padding-top  5}}]]
        pile-name                 [:div {:style {:display        :flex
                                                 :flex-direction :column
                                                 :padding-left   10}}
                                   [:div  pile-name]
                                   (if true ;; TODO Add display switch here?
                                     (into
                                      [:div {:style {:display :flex}}]
                                      (mapv (fn [[cost-type amount]]
                                              (case cost-type
                                                :coins   [:img {:src    (str "images/"
                                                                             "coin_small_"
                                                                             amount
                                                                             ".png")
                                                                :height 15
                                                                :width  15
                                                                :style  {:padding-bottom 5
                                                                         :padding-left   5}}]
                                                :debt    [:img {:src    (str "images/"
                                                                             "debt_"
                                                                             amount
                                                                             ".png")
                                                                :height 15
                                                                :width  15
                                                                :style  {:padding-bottom 5
                                                                         :padding-left   5}}]
                                                :potions [:img {:src    (str "images/"
                                                                             "potion_small.png")
                                                                :height 15
                                                                :width  15
                                                                :style  {:padding-bottom 5
                                                                         :padding-left   5}}]
                                                [:div (str cost-type " " amount)]))
                                            cost))
                                     [:div {:style {:font-size   "0.6em"
                                                    :line-height "100%"}} belongs-to-set])]]
    [:div {:style {:background       :white
                   :font-family      "Trajan Regular Pro"
                   :background-color "white"
                   :border-bottom    (str "1px solid " card-banner-background-edge)
                   :justify-content  :flex-start
                   :display          :flex
                   :align-items      :center}}
      left-part-of-pile-banner pile-name right-part-of-pile-banner]))

(defn body-column [& components]
  (let [on-phone? (< (get-viewport-width) 400)]
    [:div {:style {:width (if on-phone?
                            "100%"
                            "50%")
                   :margin (if on-phone?
                             "0% 0%"
                             "0% 25%")
                   :border-left "1px solid black"
                   :background-color card-textfield-background
                   :border-right "1px solid black"}}
     (add-react-keys components)]))

(defn title-banner []
  [:div {:style {:width "100%"
                 :display :flex
                 :color :gold
                 :text-shadow "2px 2px 4px #000000"
                 :padding-bottom 7
                 :padding-top    7
                 :background-color dominion-box-rim
                 :justify-content :center}}
   "Kingdomizer"])

(defn control-panel [& components]
  [:div {:style {:display        :flex
                 :background-color dominion-box-rim
                 :padding-bottom   3
                 :flex-direction :column}}
   (add-react-keys components)])

(defn pile-number-selection []
  [:div {:style {:display         :flex
                 :justify-content :flex-end}}
   "Desired number of piles: "
   [:input {:type      :number
            :min       1
            :max       20
            :value     @(re-frame.core/subscribe [:selected-number-of-piles])
            :on-change (fn toggle-select-number-of-piles [node]
                         (let [number-of-piles (-> node
                                                   get-value
                                                   int)]
                           (re-frame.core/dispatch [:select-number-of-piles number-of-piles])))}]])

(defn control-panel-bottom-row []
  [:div
   [:button {:on-click #(re-frame.core/dispatch [:set-all-sets-as-deselected])
             :style    {:cursor :pointer}}
    "Clear sets selection"]
   [:button {:on-click #(re-frame.core/dispatch [:set-all-sets-as-selected])
             :style    {:cursor :pointer}}
    "Select all sets"]
   [:button {:on-click #(re-frame.core/dispatch [:randomize-set-selections])
             :style    {:cursor :pointer}}
    "Select random sets"]])

(defn sets-selection []
  [:div (->> kingdomizer-static.sets/all-dominion-sets
             sort
             (map (fn dominion-set-option [set-name]
                    [:div {:title (if @(re-frame.core/subscribe [:selected-set? set-name])
                                    (str "Deselect " set-name)
                                    (str "Select " set-name))
                           :on-click #(re-frame.core/dispatch [:toggle-set-selection set-name])
                           :style {:min-height    25
                                   :border-bottom "1px solid grey"
                                   :display       :flex
                                   :align-items   :center
                                   :user-select   :none
                                   :cursor        :pointer}}
                     [:div {:style {:min-width  20
                                    :min-height 20
                                    :display    :flex
                                    :align-items :center
                                    :justify-content :center}}
                      (if @(re-frame.core/subscribe [:selected-set? set-name])
                        [:img {:src    "images/check-mark.svg"
                               :height 15
                               :width  15}]
                        [:div {:style {:min-height 15
                                       :min-width  15}}])]
                     (kw->label set-name)]))
             add-react-keys
             doall)])

(defn promos-selection []
  [:div (->> kingdomizer-static.sets/flattened-piles
             (clojure.set/select (fn filter-by-promo-type [{:keys [belongs-to-set] :as pile}]
                                   (#{"Promos"} belongs-to-set)))
             (map (fn [{:keys [pile-name name]}]
                    (or pile-name name))) ;; Due to Summon
             sort
             (map (fn dominion-promo-option [promo-name]
                    [:div {:title (if @(re-frame.core/subscribe [:selected-promo? promo-name])
                                    (str "Deselect " promo-name)
                                    (str "Select " promo-name))
                           :on-click #(re-frame.core/dispatch [:toggle-promo-selection promo-name])
                           :style {:min-height    25
                                   :border-bottom "1px solid grey"
                                   :display       :flex
                                   :align-items   :center
                                   :user-select   :none
                                   :cursor        :pointer}}
                     [:div {:style {:min-width  20
                                    :min-height 20
                                    :display    :flex
                                    :align-items :center
                                    :justify-content :center}}
                      (if @(re-frame.core/subscribe [:selected-promo? promo-name])
                        [:img {:src    "images/check-mark.svg"
                               :height 15
                               :width  15}]
                        [:div {:style {:min-height 15
                                       :min-width  15}}])]
                     promo-name]))
             (cons [:div {:style {:min-height    25
                                  :border-bottom "1px solid grey"
                                  :display       :flex
                                  :justify-content :center
                                  :align-items   :center
                                  :user-select   :none
                                  :cursor        :pointer}}
                    [:div {:style {:min-width  20
                                   :min-height 20
                                   :display    :flex
                                   :align-items :center
                                   :justify-content :center}}
                     "Promos"]])
             add-react-keys
             doall)])

(defn error-reporting []
  [:div @(re-frame.core/subscribe [:error])])

(defn generated-kingdom []
  [:div
   [:div (when-let [randomized-piles @(re-frame.core/subscribe [:randomized-piles])]
           (when-not (empty? randomized-piles)
             (->> randomized-piles
                  (mapv render-pile)
                  add-react-keys)))]
   (when @(re-frame.core/subscribe [:platinum])
     [:div {:style {:padding-left     10
                    :padding-top      2
                    :padding-bottom   2}}
      "Platinum"])
   (when @(re-frame.core/subscribe [:colony])
     [:div {:style {:padding-left     10
                    :padding-top      2
                    :padding-bottom   2}}
      "Colony"])
   (when-let [events @(re-frame.core/subscribe [:events])]
     [:div
      (->> events
           (map (fn render-event [{:keys [pile-name ;; This is an exception due to Summon
                                          name] :as event}]
                  [:div {:style {:background-color events-banner-background
                                 :border-bottom    "1px solid black"
                                 :padding-left     10
                                 :padding-top      2
                                 :padding-bottom   2 }}
                   name]))
           add-react-keys
           doall)])
   (when-let [landmarks @(re-frame.core/subscribe [:landmarks])]
     [:div
      (->> landmarks
           (map (fn render-event [{:keys [landmark-name] :as landmark}]
                  [:div {:style {:background-color landmarks-banner-background
                                 :border-bottom    "1px solid black"
                                 :padding-left     10
                                 :padding-top      2
                                 :padding-bottom   2 }}
                   landmark-name]))
           add-react-keys)])])

(defn new-kingdom-trigger []
  [:div {:style {:display         :flex
                 :padding-top     5
                 :padding-bottom  5
                 :justify-content :center}}
   [:button {:on-click #(re-frame.core/dispatch [:generate-kingdom])
             :disabled @(re-frame.core/subscribe [:no-sets-selected?])
             :style    {:cursor :pointer}}
    "Create new Kingdom!"]])

(defn pile-sort-selection []
  (when (seq @(re-frame.core/subscribe [:randomized-piles]))
    [:div {:style {:display         :flex
                   :padding-top     2
                   :padding-bottom  2
                   :padding-right   2
                   :justify-content :flex-end}}
     "Sort sets by " [sort-select]]))

(defn home-page []
  [body-column
   [title-banner]
   [control-panel
    [control-panel-bottom-row]]
   [sets-selection]
   #_[pile-number-selection] ;; Maybe only for advanced instructions
   [promos-selection]
   [new-kingdom-trigger]
   [pile-sort-selection]
   [error-reporting]
   [generated-kingdom]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (re-frame.core/dispatch-sync [:initialize-db])
  (reagent.core/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
