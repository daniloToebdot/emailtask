# emailtask

The goal of this project is to showcase a simple code to verify similarity between different pieces of text and classify them as Spam in case they are deemed too similar to one another.

# How to Run

1. Find the file `task-1.0-jar-with-dependencies.jar` from this project and place it somewhere you want to run it
2. Create a folder containing text files you'd like to verify the contents for spam
3. Run `java -jar  task-1.0-jar-with-dependencies.jar foldername`
4. The code should output one line per file and an indication if it was detected as spam or not:
```
spam one.txt isSpam: yes
spam two.txt isSpam: yes
Subject one.txt isSpam: no
Subject two.txt isSpam: no
```

