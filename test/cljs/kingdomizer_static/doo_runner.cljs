(ns kingdomizer-static.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [kingdomizer-static.core-test]
            [kingdomizer-static.randomization-test]))

(doo-tests 'kingdomizer-static.core-test
           'kingdomizer-static.randomization-test)
