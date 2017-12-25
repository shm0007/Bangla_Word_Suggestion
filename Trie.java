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
    public  ArrayList<String>  search(String word)
    {
        queue  = new PriorityQueue<>();
        answerList = new ArrayList<String>();
        TrieNode current = root;  
        for (char ch : word.toCharArray() )
        {
            if (current.subNode(ch) == null)
                return new ArrayList<String>();
            else
                current = current.subNode(ch);
        }  
        for (TrieNode eachChild : current.childList)
        {
            suggestion = "";
            prefixWord=word;
            getChild(eachChild);
        }
    while (queue.size() > 0 && answerList.size()<10) 
    {
        answerList.add(queue.poll().val);

    }
       return answerList;
    
    }
    public void getChild(TrieNode curr)
    {
        if(curr.isEnd!=0) {
            
           suggestion +=curr.content;
           queue.add(new Pair(prefixWord+suggestion,curr.isEnd ) );
           suggestion = "";
           return;
        }
       for (TrieNode eachChild : curr.childList)
       {
//           System.out.print(curr.content);
            suggestion +=curr.content;
            getChild(eachChild);
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

        while ((str = in.readLine()) != null) {

            String val[] = str.split(" ");

           // System.out.println(val[0]+"   "+ val[1]);
            insert(val[0],Integer.parseInt(val[1]));
        }


    }
     
}    
 
