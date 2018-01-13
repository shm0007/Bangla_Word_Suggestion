import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
    public static void main(String[] args)
    {            
        try {
            Trie t = new Trie();
            String input = "a";
            ArrayList<Pair> answer=   t.search(input);
            for (Pair object: answer) {
                System.out.println("Suggestion : "+object.val + " " + object.dist);
            }
        } catch (IOException ex) {
            Logger.getLogger(Trie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /* for getting just output suggestion string
    public static void main(String[] args)
    {            
        try {
            Trie t = new Trie();
            String input = "a";
            ArrayList<String> answer=   t.search(input);

            for (String object: answer) {
                System.out.println("Suggestion : "+object);
            }
        } catch (IOException ex) {
            Logger.getLogger(Trie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */

}