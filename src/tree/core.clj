(ns tree.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def cli-options
  [["--help"]
   ["--version"]
   ["-a" "Print all files"]
   ["-d" "Print directories only"]
   ["-f" "Print full paths"]
   ["-l" "Follow symlinks if they refer to directories"]
   ;; TODO validate the pattern? -tbv
   ["-P PATTERN" "List files that match PATTERN"]
   ["-I PATTERN" "Do not list files that match PATTERN"]
   ["--noreport" "Do not show report of files and directories listed"]
   ["-p" "Print file and directory permissions"]
   ["-s" "Print size in bytes"]
   ["-h" "Print size in a human-readable format"]
   ["-u" "Print username or alternatively UID of files"]
   ["-g" "Print group name"]
   ["-D" "Print last modified date of file"]
   ["-r" "Sort output by reverse alphabetical order"]
   ["-t" "Sort by last modified date"]
   ["-n" "Turn colorization off"]
   ["-C" "Turn colorization on"]
   ["-L DEPTH" "Set max display depth of the listing tree"
    :default 65536
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
   ["--filelimit LIMIT" "Only traverse directories with file count under LIMIT"
    :default 65536
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
