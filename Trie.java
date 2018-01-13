import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

class TrieNode 
{
    char content; 
    int isEnd; 
    int count;  

    LinkedList<TrieNode> childList; 
    public TrieNode(char c)
    {
        childList = new LinkedList<TrieNode>();
        isEnd = 0;
        content = c;
        count = 0;
        
    }  
    public TrieNode subNode(char c)
    {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.content == c)
                    return eachChild;
        return null;
    }
}
  class Pair implements Comparable<Pair> {
            String val;
            int dist;

            Pair(String val, int dist) {
                this.val = val;
                this.dist = dist;
            }

            @Override
            public int compareTo(Pair o) {
                if (o.dist > dist)
                    return 1;
                if (o.dist < dist)
                    return -1;
                return 0;
            }
        }
 class Trie 
{
    private TrieNode root;
    private String suggestion;
    private  ArrayList<String>answerList;
    private PriorityQueue<Pair> queue;
    private String prefixWord;
    public Trie() throws IOException
    {
        root = new TrieNode(' '); 
        suggestion = "";
        init();
    }
     /* Function to insert word */
    public void insert(String word, int freq)
    {
        TrieNode current = root; 
        for (char ch : word.toCharArray() )
        {
            TrieNode child = current.subNode(ch);
            if (child != null)
                current = child;
            else 
            {
                 current.childList.add(new TrieNode(ch));
                 current = current.subNode(ch);
            }
            current.count++;
        }
        current.isEnd = freq;
    }
    public  ArrayList<Pair>  search(String word)
    {
        queue  = new PriorityQueue<>();
        answerList = new ArrayList<String>();
        ArrayList<Pair> answerPair = new ArrayList<Pair>();
        TrieNode current = root;  
        for (char ch : word.toCharArray() )
        {
            if (current.subNode(ch) == null)
                return new ArrayList<Pair>();
//remove comment                return new ArrayList<String>();

            else
                current = current.subNode(ch);
        }  
        for (TrieNode eachChild : current.childList)
        {
            suggestion = "";
            prefixWord=word;
            getChild(eachChild,prefixWord);
        }
    while (queue.size() > 0 && answerPair.size()<10) // change to answerList.size()
    {
//remove comment        answerList.add(queue.poll().val);
        answerPair.add(queue.poll());

    }
       return answerPair;
    
    }
    public void getChild(TrieNode curr, String currentSuggestion)
    {
        if(curr.isEnd!=0) {
            
           currentSuggestion +=curr.content;
           queue.add(new Pair(currentSuggestion,curr.isEnd ) );
          // return;
        }
     currentSuggestion +=curr.content;
       for (TrieNode eachChild : curr.childList)
       {
            getChild(eachChild,currentSuggestion);
       }
       return;
    }
    public void init() throws IOException
    {

        File fileDir = new File("WordsEnglish.txt");
        BufferedReader in = new BufferedReader(
           new InputStreamReader(
                      new FileInputStream(fileDir), "UTF8"));
        String str;
        int tt=0;
        while ((str = in.readLine()) != null) {
            String val[] = str.split(" ");
            insert(val[0],Integer.parseInt(val[1]));
        }
    }
     
}    
 
