(ns kingdomizer-static.sets
  (:require [reagent.core  :as r]
            [reagent.ratom :as ra]))

(def all-dominion-sets (into #{}
                             ["Dominion Second Edition"
                              "Intrigue Second Edition"
                              "Alchemy"
                              "Prosperity"
                              "Seaside"
                              "Cornucopia"
                              "Hinterlands"
                              "Dark Ages"
                              "Guilds"
                              "Adventures"
                              "Empires"
                              "Nocturne"]))

(def flattened-piles
  #{{:pile-name      "Prizes",
     :cards
     [{:name "Bag Of Gold", :cost {:coins 0}, :types [:action :prize]}
      {:name "Diadem", :cost {:coins 0}, :types [:treasure :prize]}
      {:name  "Followers",
       :cost  {:coins 0},
       :types [:action :attack :prize]}
      {:name "Princess", :cost {:coins 0}, :types [:action :prize]}
      {:name "Trusty Steed", :cost {:coins 0}, :types [:action :prize]}],
     :belongs-to-set "Cornucopia",
     :supply-type    :non-supply}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Hamlet",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Fortune Teller",
     :cost           {:coins 3},
     :types          [:action :attack]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Menagerie",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Farming Village",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Horse Traders",
     :cost           {:coins 4},
     :types          [:action :reaction]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Remake",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Tournament",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Young Witch",
     :cost           {:coins 4},
     :types          [:action :attack]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Harvest",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Horn Of Plenty",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Hunting Party",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Jester",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Cornucopia",
     :supply-type    :kingdom-cards,
     :pile-name      "Fairgrounds",
     :cost           {:coins 6},
     :types          [:victory]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Courtyard",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Lurker",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Pawn",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Masquerade",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Shantytown",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Steward",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Swindler",
     :cost           {:coins 3},
     :types          [:action :attack]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Wishing Well",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Baron",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Bridge",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Conspirator",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Diplomat",
     :cost           {:coins 4},
     :types          [:action :reaction]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Ironworks",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Mill",
     :cost           {:coins 4},
     :types          [:action :victory]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Mining Village",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Secret Passage",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Courtier",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Duke",
     :cost           {:coins 5},
     :types          [:Victory]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Minion",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Patrol",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Replace",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Torturer",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Trading Post",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Upgrade",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Harem",
     :cost           {:coins 6},
     :types          [:treasure :victory]}
    {:belongs-to-set "Intrigue Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Nobles",
     :cost           {:coins 6},
     :types          [:action :victory]}
    {:name           "Alms",
     :cost           {:coins 0},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Borrow",
     :cost           {:coins 0},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Quest",
     :cost           {:coins 0},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Save",
     :cost           {:coins 1},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Scouting Party",
     :cost           {:coins 2},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Travelling Fair",
     :cost           {:coins 2},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Bonfire",
     :cost           {:coins 3},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Expedition",
     :cost           {:coins 3},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Ferry",
     :cost           {:coins 3},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Plan",
     :cost           {:coins 3},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Mission",
     :cost           {:coins 4},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Pilgrimage",
     :cost           {:coins 4},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Ball",
     :cost           {:coins 5},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Raid",
     :cost           {:coins 5},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Seaway",
     :cost           {:coins 5},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Trade",
     :cost           {:coins 5},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Lost Arts",
     :cost           {:coins 6},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Training",
     :cost           {:coins 6},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Inheritance",
     :cost           {:coins 7},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:name           "Pathfinding",
     :cost           {:coins 8},
     :belongs-to-set "Adventures",
     :supply-type    :events}
    {:cards
     [{:name  "Treasure Hunter",
       :cost  {:coins 3},
       :types [:action :traveller]}],
     :belongs-to-set "Adventures",
     :supply-type    :non-supply}
    {:cards
     [{:name  "Warrior",
       :cost  {:coins 4},
       :types [:action :attack :traveller]}],
     :belongs-to-set "Adventures",
     :supply-type    :non-supply}
    {:cards
     [{:name "Hero", :cost {:coins 5}, :types [:action :traveller]}],
     :belongs-to-set "Adventures",
     :supply-type    :non-supply}
    {:cards
     [{:name "Champion", :cost {:coins 6}, :types [:action :duration]}],
     :belongs-to-set "Adventures",
     :supply-type    :non-supply}
    {:cards
     [{:name  "Soldier",
       :cost  {:coins 3},
       :types [:action :attack :traveller]}],
     :belongs-to-set "Adventures",
     :supply-type    :non-supply}
    {:cards
     [{:name "Fugitive", :cost {:coins 4}, :types [:action :traveller]}],
     :belongs-to-set "Adventures",
     :supply-type    :non-supply}
    {:cards
     [{:name "Disciple", :cost {:coins 5}, :types [:action :traveller]}],
     :belongs-to-set "Adventures",
     :supply-type    :non-supply}
    {:cards
     [{:name "Teacher", :cost {:coins 6}, :types [:action :reserve]}],
     :belongs-to-set "Adventures",
     :supply-type    :non-supply}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Coins Of The Realm",
     :cost           {:coins 2},
     :types          [:treasure :reserve]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Page",
     :cost           {:coins 2},
     :types          [:action :traveller]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Peasant",
     :cost           {:coins 2},
     :types          [:action :traveller]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Ratcatcher",
     :cost           {:coins 2},
     :types          [:action :reserve]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Raze",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Amulet",
     :cost           {:coins 3},
     :types          [:action :duration]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Caravan Guard",
     :cost           {:coins 3},
     :types          [:action :duration :reaction]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Dungeon",
     :cost           {:coins 3},
     :types          [:action :duration]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Gear",
     :cost           {:coins 3},
     :types          [:action :duration]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Guide",
     :cost           {:coins 3},
     :types          [:action :reserve]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Duplicate",
     :cost           {:coins 4},
     :types          [:action :reserve]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Magpie",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Messenger",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Miser",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Port",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Ranger",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Transmogrify",
     :cost           {:coins 4},
     :types          [:action :reserve]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Artificer",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Bridge Troll",
     :cost           {:coins 5},
     :types          [:action :attack :duration]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Distant Lands",
     :cost           {:coins 5},
     :types          [:action :reserve :victory]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Giant",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Haunted Woods",
     :cost           {:coins 5},
     :types          [:action :attack :duration]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Lost City",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Relic",
     :cost           {:coins 5},
     :types          [:treasure :attack]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Royal Carriage",
     :cost           {:coins 5},
     :types          [:action :reserve]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Storyteller",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Swamp Hag",
     :cost           {:coins 5},
     :types          [:action :attack :duration]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Treasure Trove",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Wine Merchant",
     :cost           {:coins 5},
     :types          [:action :reserve]}
    {:belongs-to-set "Adventures",
     :supply-type    :kingdom-cards,
     :pile-name      "Hireling",
     :cost           {:coins 6},
     :types          [:action :duration]}
    {:cards          [{:name "Platinum", :cost {:coins 9}, :types [:treasure]}],
     :belongs-to-set "Prosperity",
     :supply-type    :basic-cards}
    {:cards          [{:name "Colony", :cost {:coins 11}, :types [:victory]}],
     :belongs-to-set "Prosperity",
     :supply-type    :basic-cards}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Loan",
     :cost           {:coins 1},
     :types          [:treasure]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Trade Route",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Watchtower",
     :cost           {:coins 3},
     :types          [:action :reaction]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Bishop",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Monument",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Quarry",
     :cost           {:coins 4},
     :types          [:treasure]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Talisman",
     :cost           {:coins 4},
     :types          [:treasure]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Worker's Village",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "City",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Contraband",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Counting House",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Mint",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Mountebank",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Rabble",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Royal Seal",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Vault",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Venture",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Goons",
     :cost           {:coins 6},
     :types          [:action :attack]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Grand Market",
     :cost           {:coins 6},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Hoard",
     :cost           {:coins 6},
     :types          [:treasure]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Bank",
     :cost           {:coins 7},
     :types          [:treasure]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Expand",
     :cost           {:coins 7},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Forge",
     :cost           {:coins 7},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "King's Court",
     :cost           {:coins 7},
     :types          [:action]}
    {:belongs-to-set "Prosperity",
     :supply-type    :kingdom-cards,
     :pile-name      "Peddler",
     :cost           {:coins 8},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Embargo",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Haven",
     :cost           {:coins 2},
     :types          [:action :duration]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Lighthouse",
     :cost           {:coins 2},
     :types          [:action :duration]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Native Village",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Pearl Diver",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Ambassador",
     :cost           {:coins 3},
     :types          [:action :attack]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Fishing Village",
     :cost           {:coins 3},
     :types          [:action :duration]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Lookout",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Smugglers",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Warehouse",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Caravan",
     :cost           {:coins 4},
     :types          [:action :duration]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Cutpurse",
     :cost           {:coins 4},
     :types          [:action :attack]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Island",
     :cost           {:coins 4},
     :types          [:action :victory]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Navigator",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Pirate Ship",
     :cost           {:coins 4},
     :types          [:action :attack]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Salvager",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Sea Hag",
     :cost           {:coins 4},
     :types          [:action :attack]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Treasure Map",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Bazaar",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Explorer",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Ghost Ship",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Merchant Ship",
     :cost           {:coins 5},
     :types          [:action :duration]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Outpost",
     :cost           {:coins 5},
     :types          [:action :duration]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Tactician",
     :cost           {:coins 5},
     :types          [:action :duration]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Treasury",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Seaside",
     :supply-type    :kingdom-cards,
     :pile-name      "Wharf",
     :cost           {:coins 5},
     :types          [:action :duration]}
    {:cards          [{:name "Potion", :cost {:coins 4}, :types [:treasure]}],
     :belongs-to-set "Alchemy",
     :supply-type    :basic-cards}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Transmute",
     :cost           {:potions 1},
     :types          [:action]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Vineyard",
     :cost           {:potions 1},
     :types          [:victory]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Herbalist",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Apothecary",
     :cost           {:coins 2, :potions 1},
     :types          [:action]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Scrying Pool",
     :cost           {:coins 2, :potions 1},
     :types          [:action :attack]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "University",
     :cost           {:coins 2, :potions 1},
     :types          [:action]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Alchemist",
     :cost           {:coins 3, :potions 1},
     :types          [:action]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Familiar",
     :cost           {:coins 3, :potions 1},
     :types          [:action :attack]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Philosopher's Stone",
     :cost           {:coins 3, :potions 1},
     :types          [:treasure]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Golem",
     :cost           {:coins 4, :potions 1},
     :types          [:action]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Apprentice",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Alchemy",
     :supply-type    :kingdom-cards,
     :pile-name      "Possession",
     :cost           {:coins 6, :potions 1},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Crossroads",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Duchess",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Fool's Gold",
     :cost           {:coins 2},
     :types          [:treasure :reaction]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Develop",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Oasis",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Oracle",
     :cost           {:coins 3},
     :types          [:action :attack]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Scheme",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Tunnel",
     :cost           {:coins 3},
     :types          [:victory :reaction]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Jack Of All Trades",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Noble Brigand",
     :cost           {:coins 4},
     :types          [:action :attack]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Nomad Camp",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Silk Road",
     :cost           {:coins 4},
     :types          [:victory]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Spice Merchant",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Trader",
     :cost           {:coins 4},
     :types          [:action :reaction]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Cache",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Cartographer",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Embassy",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Haggler",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Highway",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Ill-gotten Gains",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Inn",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Mandarin",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Margrave",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Stables",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Border Village",
     :cost           {:coins 6},
     :types          [:action]}
    {:belongs-to-set "Hinterlands",
     :supply-type    :kingdom-cards,
     :pile-name      "Farmland",
     :cost           {:coins 6},
     :types          [:victory]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Candlestick Maker",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Stonemason",
     :cost           {:coins 2, :overpay true},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Doctor",
     :cost           {:coins 3, :overpay true},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Masterpiece",
     :cost           {:coins 3, :overpay true},
     :types          [:treasure]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Advisor",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Plaza",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Taxman",
     :cost           {:coins 4},
     :types          [:action :attack]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Herald",
     :cost           {:coins 4, :overpay true},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Baker",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Butcher",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Journeyman",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Merchant Guild",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Guilds",
     :supply-type    :kingdom-cards,
     :pile-name      "Soothsayer",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Cellar",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Chapel",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Moat",
     :cost           {:coins 2},
     :types          [:action :reaction]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Harbinger",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Merchant",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Vassal",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Village",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Workshop",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Bureaucrat",
     :cost           {:coins 4},
     :types          [:action :attack]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Gardens",
     :cost           {:coins 4},
     :types          [:victory]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Militia",
     :cost           {:coins 4},
     :types          [:action :attack]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Moneylender",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Poacher",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Remodel",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Smithy",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Throne Room",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Bandit",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Council Room",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Festival",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Laboratory",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Library",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Market",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Mine",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Sentry",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Witch",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dominion Second Edition",
     :supply-type    :kingdom-cards,
     :pile-name      "Artisan",
     :cost           {:coins 6},
     :types          [:action]}
    {:cards          [{:name "Madman", :cost {:coins 0}, :types [:action]}],
     :belongs-to-set "Dark Ages",
     :supply-type    :non-supply}
    {:cards
     [{:name "Mercenary", :cost {:coins 0}, :types [:action :attack]}],
     :belongs-to-set "Dark Ages",
     :supply-type    :non-supply}
    {:cards          [{:name "Spoils", :cost {:coins 0}, :types [:treasure]}],
     :belongs-to-set "Dark Ages",
     :supply-type    :non-supply}
    {:cards
     [{:name "Hovel", :cost {:coins 1}, :types [:reaction :shelter]}],
     :belongs-to-set "Dark Ages",
     :supply-type    :non-supply}
    {:cards
     [{:name "Necropolis", :cost {:coins 1}, :types [:action :shelter]}],
     :belongs-to-set "Dark Ages",
     :supply-type    :non-supply}
    {:cards
     [{:name  "Overgrown Estate",
       :cost  {:coins 1},
       :types [:victory :shelter]}],
     :belongs-to-set "Dark Ages",
     :supply-type    :non-supply}
    {:pile-name      "Ruins",
     :cards
     [{:name "Abandoned Mine", :cost {:coins 0}, :types [:action :ruins]}
      {:name "Ruined Library", :cost {:coins 0}, :types [:action :ruins]}
      {:name "Ruined Market", :cost {:coins 0}, :types [:action :ruins]}
      {:name "Ruined Village", :cost {:coins 0}, :types [:action :ruins]}
      {:name "Survivors", :cost {:coins 0}, :types [:action :ruins]}],
     :belongs-to-set "Dark Ages",
     :supply-type    :basic-cards}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Poor House",
     :cost           {:coins 1},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Beggar",
     :cost           {:coins 2},
     :types          [:action :reaction]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Squire",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Vagrant",
     :cost           {:coins 2},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Forager",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Hermit",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Market Square",
     :cost           {:coins 3},
     :types          [:action :reaction]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Sage",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Storeroom",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Urchin",
     :cost           {:coins 3},
     :types          [:action :attack]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Armory",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Death Cart",
     :cost           {:coins 4},
     :types          [:action :looter]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Feodum",
     :cost           {:coins 4},
     :types          [:victory]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Fortress",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Ironmonger",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Marauder",
     :cost           {:coins 4},
     :types          [:action :attack :looter]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Procession",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Rats",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Scavenger",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Wandering Minstrel",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Band Of Misfits",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Bandit Camp",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Catacombs",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Count",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Counterfeit",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Cultist",
     :cost           {:coins 5},
     :types          [:action :attack :looter]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Graverobber",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Junk Dealer",
     :cost           {:coins 5},
     :types          [:action]}
    {:pile-name      "Knights",
     :cards
     [{:name  "Dame Anna",
       :cost  {:coins 5},
       :types [:action :attack :knight]}
      {:name  "Dame Josephine",
       :cost  {:coins 5},
       :types [:action :attack :knight :victory]}
      {:name  "Dame Molly",
       :cost  {:coins 5},
       :types [:action :attack :knight]}
      {:name  "Dame Natalie",
       :cost  {:coins 5},
       :types [:action :attack :knight]}
      {:name  "Dame Sylvia",
       :cost  {:coins 5},
       :types [:action :attack :knight]}
      {:name  "Sir Bailey",
       :cost  {:coins 5},
       :types [:action :attack :knight]}
      {:name  "Sir Destry",
       :cost  {:coins 5},
       :types [:action :attack :knight]}
      {:name  "Sir Martin",
       :cost  {:coins 5},
       :types [:action :attack :knight]}
      {:name  "Sir Michael",
       :cost  {:coins 5},
       :types [:action :attack :knight]}
      {:name  "Sir Vander",
       :cost  {:coins 5},
       :types [:action :attack :knight]}],
     :belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :cost           {:coins 5},
     :types          [:action :attack :knight]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Mystic",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Pillage",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Rebuild",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Rogue",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Altar",
     :cost           {:coins 6},
     :types          [:action]}
    {:belongs-to-set "Dark Ages",
     :supply-type    :kingdom-cards,
     :pile-name      "Hunting Grounds",
     :cost           {:coins 6},
     :types          [:action]}
    {:landmark-name  "Aqueduct",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Arena",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Bandit Fort",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Basilica",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Baths",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Battlefield",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Colonnade",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Defiled Shrine",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Fountain",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Keep",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Labyrinth",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Mountain Pass",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Museum",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Obelisk",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Orchard",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Palace",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Tomb",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Tower",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Triumphal Arch",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Wall",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:landmark-name  "Wolf Den",
     :belongs-to-set "Empires",
     :supply-type    :landmarks}
    {:name           "Triumph",
     :cost           {:debt 5},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Annex",
     :cost           {:debt 8},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Donate",
     :cost           {:debt 8},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Advance",
     :cost           {:coins 0},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Delve",
     :cost           {:coins 2},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Tax",
     :cost           {:coins 2},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Banquet",
     :cost           {:coins 3},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Ritual",
     :cost           {:coins 4},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Salt The Earth",
     :cost           {:coins 4},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Wedding",
     :cost           {:coins 4, :debt 3},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Windfall",
     :cost           {:coins 5},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Conquest",
     :cost           {:coins 6},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:name           "Dominate",
     :cost           {:coins 14},
     :belongs-to-set "Empires",
     :supply-type    :events}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Engineer",
     :cost           {:debt 4},
     :types          [:action]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "City Quarter",
     :cost           {:debt 8},
     :types          [:action]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Overlord",
     :cost           {:debt 8},
     :types          [:action]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Royal Blacksmith",
     :cost           {:debt 8},
     :types          [:action]}
    {:pile-name      "Encampment/Plunder",
     :cards
     [{:name "Encampment", :cost {:coins 2}, :types [:action]}
      {:name "Plunder", :cost {:coins 5}, :types [:treasure]}],
     :belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :cost           {:coins 2},
     :types          [:action]}
    {:pile-name      "Patrician/Emporium",
     :cards
     [{:name "Patrician", :cost {:coins 2}, :types [:action]}
      {:name "Emporium", :cost {:coins 5}, :types [:action]}],
     :belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :cost           {:coins 2},
     :types          [:action]}
    {:pile-name      "Settlers/Bustling Village",
     :cards
     [{:name "Settlers", :cost {:coins 2}, :types [:action]}
      {:name "Bustling Village", :cost {:coins 5}, :types [:action]}],
     :belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :cost           {:coins 2},
     :types          [:action]}
    {:pile-name      "Castles",
     :cards
     [{:name  "Humble Castle",
       :cost  {:coins 3},
       :types [:treasure :victory :castle]}
      {:name  "Crumbling Castle",
       :cost  {:coins 4},
       :types [:victory :castle]}
      {:name  "Small Castle",
       :cost  {:coins 5},
       :types [:action :victory :castle]}
      {:name  "Haunted Castle",
       :cost  {:coins 6},
       :types [:victory :castle]}
      {:name  "Opulent Castle",
       :cost  {:coins 7},
       :types [:action :victory :castle]}
      {:name  "Sprawling Castle",
       :cost  {:coins 8},
       :types [:victory :castle]}
      {:name "Grand Castle", :cost {:coins 9}, :types [:victory :castle]}
      {:name  "King's Castle",
       :cost  {:coins 10},
       :types [:victory :castle]}],
     :belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :cost           {:coins 3},
     :types          [:treasure :victory :castle]}
    {:pile-name      "Catapult/Rocks",
     :cards
     [{:name "Catapult", :cost {:coins 3}, :types [:action :attack]}
      {:name "Rocks", :cost {:coins 4}, :types [:treasure]}],
     :belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :cost           {:coins 3},
     :types          [:action :attack]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Chariot Race",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Enchantress",
     :cost           {:coins 3},
     :types          [:action :attack :duration]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Farmer's Market",
     :cost           {:coins 3},
     :types          [:action :gathering]}
    {:pile-name      "Gladiator/Plunder",
     :cards
     [{:name "Gladiator", :cost {:coins 3}, :types [:action]}
      {:name "Fortune", :cost {:coins 8, :debt 8}, :types [:treasure]}],
     :belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Sacrifice",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Temple",
     :cost           {:coins 4},
     :types          [:action :gathering]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Villa",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Archive",
     :cost           {:coins 5},
     :types          [:action :duration]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Capital",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Charm",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Crown",
     :cost           {:coins 5},
     :types          [:action :treasure]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Forum",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Groundskeeper",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Legionary",
     :cost           {:coins 5},
     :types          [:action :attack]}
    {:belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :pile-name      "Wild Hunt",
     :cost           {:coins 5},
     :types          [:action :gathering]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Druid",
     :cost           {:coins 2},
     :types          [:action :fate]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Faithful Hound",
     :cost           {:coins 2},
     :types          [:action :reaction]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Guardian",
     :cost           {:coins 2},
     :types          [:night :duration]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Monastery",
     :cost           {:coins 2},
     :types          [:night]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Pixie",
     :heirloom       "Goat",
     :cost           {:coins 2},
     :types          [:action :fate]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Tracker",
     :heirloom       "Pouch",
     :cost           {:coins 2},
     :types          [:action :fate]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Changeling",
     :cost           {:coins 3},
     :types          [:night]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Fool",
     :heirloom       "Lucky Coin",
     :cost           {:coins 3},
     :types          [:action :fate]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Ghost Town",
     :cost           {:coins 3},
     :types          [:night :duration]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Leprechaun",
     :cost           {:coins 3},
     :types          [:action :doom]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Night Watchman",
     :cost           {:coins 3},
     :types          [:night]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Secret Cave",
     :heirloom       "Magic Lamp"
     :cost           {:coins 3},
     :types          [:action :duration]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Bard",
     :cost           {:coins 4},
     :types          [:action :fate]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Blessed Village",
     :cost           {:coins 4},
     :types          [:action :fate]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Cemetery",
     :heirloom       "Haunted Mirror"
     :cost           {:coins 4},
     :types          [:victory]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Conclave",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Devil's Workshop",
     :cost           {:coins 4},
     :types          [:night]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Exorcist",
     :cost           {:coins 4},
     :types          [:night]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Necromancer",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Shepherd",
     :heirloom       "Pasture"
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Skulk",
     :cost           {:coins 4},
     :types          [:action :attack :doom]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Cobbler",
     :cost           {:coins 4},
     :types          [:night :duration]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Crypt",
     :cost           {:coins 5},
     :types          [:night :duration]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Cursed Village",
     :cost           {:coins 5},
     :types          [:action :doom]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Den Of Sin",
     :cost           {:coins 5},
     :types          [:night :duration]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Idol",
     :cost           {:coins 5},
     :types          [:treasure :attack :fate]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Pooka",
     :heirloom       "Cursed Gold"
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Sacred Grove",
     :cost           {:coins 5},
     :types          [:action :fate]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Tormentor",
     :cost           {:coins 5},
     :types          [:action :attack :doom]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Tragic Hero",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Vampire",
     :cost           {:coins 5},
     :types          [:night :attack :doom]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Werewolf",
     :cost           {:coins 5},
     :types          [:action :night :attack :doom]}
    {:belongs-to-set "Nocturne",
     :supply-type    :kingdom-cards,
     :pile-name      "Raider",
     :cost           {:coins 6},
     :types          [:night :duration :attack]}
    {:belongs-to-set "Promos",
     :supply-type    :kingdom-cards,
     :pile-name      "Black Market",
     :cost           {:coins 3},
     :types          [:action]}
    {:belongs-to-set "Promos",
     :supply-type    :kingdom-cards,
     :pile-name      "Dismantle",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Promos",
     :supply-type    :kingdom-cards,
     :pile-name      "Envoy",
     :cost           {:coins 4},
     :types          [:action]}
    {:pile-name      "Sauna/Avanto",
     :cards
     [{:name "Sauna", :cost {:coins 4}, :types [:action]}
      {:name "Avanto", :cost {:coins 5}, :types [:action]}],
     :belongs-to-set "Empires",
     :supply-type    :kingdom-cards,
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Promos",
     :supply-type    :kingdom-cards,
     :pile-name      "Walled Village",
     :cost           {:coins 4},
     :types          [:action]}
    {:belongs-to-set "Promos",
     :supply-type    :kingdom-cards,
     :pile-name      "Governor",
     :cost           {:coins 5},
     :types          [:action]}
    {:belongs-to-set "Promos",
     :supply-type    :kingdom-cards,
     :pile-name      "Stash",
     :cost           {:coins 5},
     :types          [:treasure]}
    {:belongs-to-set "Promos",
     :supply-type    :kingdom-cards,
     :pile-name      "Prince",
     :cost           {:coins 8},
     :types          [:action]}
    {:belongs-to-set "Promos",
     :supply-type    :events
     :name           "Summon",
     :cost           {:coins 5},}})
