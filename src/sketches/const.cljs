(ns sketches.perlin-const)

(def body (.-body js/document))
(def w (.-clientWidth body))
(def h (.-clientHeight body))


(def palette
  {:name       "purple haze"
   :background [10 10 10]
   :colors     [[32 0 40]
                [82 15 125]
                [99 53 126]
                [102 10 150]
                [132 26 200]
                [165 32 250]
                [196 106 251]]})
