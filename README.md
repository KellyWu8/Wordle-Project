# Wordle Project

A Java implementation of the popular word-guessing game, Wordle. The game includes user authentication, a graphical user interface, and a tracking system for user statistics.

## Topics Covered
- **Graphics**: Dynamic color-coded letter boxes to indicate correctness of guesses.
    - `Gray`: Letter not in the word
    - `Yellow`: Letter in the wrong position
    - `Green`: Letter in the correct position
- **File I/O**: Reading a list of possible words from a file and selecting a random target word.
- **JDBC**: User authentication and statistics tracking using a database.
  - `USER Table`
    - Check if the username exists
    - Check if the username and password match
    - Store new user information
  - `STATS Table`
    - After completing the game, store:
      - Completion time
      - Username
      - Score
        
## Dependencies
- Java SE Development Kit
- MariaDB Server
- JDBC Driver for MariaDB

## How to Run
Compile the Java files and run the main class:
```sh
javac -d bin src/wordle/*.java && java -cp bin:lib/* wordle.Wordle
```

## Demo Screenshots
!(login-signup)
!(wordle1)
!(wordle2)
!(wordle3)
!(wordle4)
