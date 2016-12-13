(ns tree.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def current-path (clojure.java.io/file "."))

(def cli-options
  [[nil "--help"]
   [nil "--version"]
   ["-a" "--all" "Print all files"]
   ["-d" "--directories" "Print directories only"]
   ["-f" "--full-paths" "Print full paths"]
   ["-l" "--links" "Follow symlinks if they refer to directories"]
   ["-P PATTERN" "--pattern" "List files that match PATTERN"]
   [nil "--noreport" "Do not show report of files and directories listed"]
   ["-p" "--permissions" "Print file and directory permissions"]
   ["-s" "--size" "Print size in bytes"]
   ["-h" "--human-readable" "Print size in a human-readable format"]
   ["-u" "--username" "Print username or alternatively UID of files"]
   ["-g" "--group" "Print group name"]
   ["-D" "--last-modified" "Print last modified date of file"]
   ["-r" "--reverse" "Sort output by reverse alphabetical order"]
   ["-t" "--time" "Sort by last modified date"]
   ["-n" "--no-colorize" "Turn colorization off"]
   ["-C" "--colorize" "Turn colorization on"]
   ["-L DEPTH" "--levels" "Set max display depth of the listing tree"
    :default 65536
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
   [nil "--filelimit LIMIT" "Only traverse directories with file count under LIMIT"
    :default 65536
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]])

(defn get-file [path] (clojure.java.io/file path))

(defn directory-items
  "A sequence of the items in the directory dir"
  [dir]
  (file-seq dir))

(defn traverse
  [dir level]
  (doseq [f (rest (directory-items dir))]
    (println (apply str (repeat level "  "))
             "|--"
             (.getName f))
    (if (.isDirectory f) (traverse f (+ level 1)))))

(defn -main [& args]
  (let [{:keys [options arguments summary]} (parse-opts args cli-options)
        initial-level 0]
    (cond
      (get options :help) (println "Usage:\n summary")
      (get options :help) (println "Usage:\n summary")
      (seq args) (traverse (get-file (nth args 0)) initial-level)
      :else (traverse current-path initial-level))))
