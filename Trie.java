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
    boolean isEnd; 
    int count;  
    LinkedList<TrieNode> childList; 
    public TrieNode(char c)
    {
        childList = new LinkedList<TrieNode>();
        isEnd = false;
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
 class Trie 
{
    private TrieNode root;
    private String suggestion;
    private  ArrayList<String>answerList;
    private String prefixWord;
    public Trie() throws IOException
    {
        root = new TrieNode(' '); 
        suggestion = "";
        init();
    }
     /* Function to insert word */
    public void insert(String word)
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
        current.isEnd = true;
    }
    public ArrayList<String>  search(String word)
    {
        answerList = new  ArrayList<String>();
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
       return answerList;
    }
    public void getChild(TrieNode curr)
    {
        if(curr.isEnd==true) {
            
           suggestion +=curr.content;
           answerList.add(prefixWord+suggestion);
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

        File fileDir = new File("words.txt");
        BufferedReader in = new BufferedReader(
           new InputStreamReader(
                      new FileInputStream(fileDir), "UTF8"));
        String str;

        while ((str = in.readLine()) != null) {

           // System.out.print(str);
            insert(str);
        }


    }
     
}    
 
