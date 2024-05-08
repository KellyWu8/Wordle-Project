package wordle;

import java.util.*;

public class Wordle {
    public Wordle() {
        new LoginGUI();
    }
    
    public Wordle(String username) { // If user clicks Play Again, don't need to login again
        WordList wl = new WordList("words.txt"); 
        String targetWord = wl.getTargetWord();
        ArrayList<String> words = wl.getWords();
        new StartGUI(targetWord, username, words);
    }

    public static void main(String[] args) {
        new Wordle();
    }
}