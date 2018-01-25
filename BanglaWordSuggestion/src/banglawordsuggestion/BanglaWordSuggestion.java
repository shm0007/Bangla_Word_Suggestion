/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package banglawordsuggestion;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ehsan
 */
public class BanglaWordSuggestion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {            
        Trie t = new Trie();
        String input = "বা";
        ArrayList<Pair> answer=   t.search(input);
        System.out.println("Suggestion : Count" +answer.size());
        for (Pair object: answer) {
            System.out.println("Suggestion : "+object.val + " " + object.dist);
        }
    }
    
}
