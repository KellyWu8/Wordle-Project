package wordle;

import java.sql.*;


public class Wordle {
    private String targetWord = null;

    public Wordle() {
        
        
        targetWord = new WordList("words.txt").getTargetWord();
        new StartGUI(targetWord);
    }

    public static void main(String[] args) {
        new Wordle();
    }
}