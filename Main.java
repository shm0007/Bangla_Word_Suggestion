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
            String input = "al";
            ArrayList<String> answer=   t.search(input);
            for (String object: answer) {
                System.out.println("Suggestion : "+object);
            }
        } catch (IOException ex) {
            Logger.getLogger(Trie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}