package wordle;

import java.io.*;
import java.util.*;

public class WordList {
    private String targetWord = null;
    private ArrayList<String> words = null;
    
    WordList(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scan = new Scanner(file);
            words = new ArrayList<>();
            
            // Read each line from the file and add it to the list
            while (scan.hasNextLine()) {
                words.add(scan.nextLine());
            }
            
            // Generate a random index
            Random r = new Random();
            int idx = r.nextInt(words.size());
            
            // Get the word from the list
            targetWord = words.get(idx);
            System.out.println("Target word: " + targetWord);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: File not found!");
        }
    }
    
    String getTargetWord() {
        return targetWord;
    }
    
    ArrayList<String> getWords() {
        return words;
    }
}