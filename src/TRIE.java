import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Node
{
    char leadChar;
    boolean isWord = false;
    Node rightSibling;
    Node firstChild;

    public Node(char leadChar)
    {
        this.leadChar = leadChar;
        this.firstChild = null;
        this.rightSibling = null;
    }

}

public class TRIE {

    public static int count;
    public static Node root;

    public TRIE(){
        root = new Node((char)0);
    }
    public static void main(String[] args) throws FileNotFoundException {

        TRIE wordTrie = new TRIE();

        File wordFile = new File("WORD.LST");
        Scanner in = new Scanner(wordFile);

        while (in.hasNextLine()){
            String word = in.nextLine();

            if(word.length() == 5){
                wordTrie.insert(wordTrie.root, word);
            }
        }

        File inputFile = new File("inputFile.txt");
        Scanner cin = new Scanner(inputFile);
        String queryParams = cin.nextLine();
        String [] paramArray = queryParams.split(" ");
        String prefix = paramArray[0];
        String forbidden = paramArray[1];
        wordTrie.query(wordTrie.root, prefix, forbidden);

        System.out.println(count);

    }

    public void query(Node root, String qPrefix, String forbidden)
    {

        Node q = search(root, qPrefix);
        preorderMatch(q, qPrefix, forbidden);
    }

    public Node search(Node root, String qPrefix)
    {
        if(qPrefix.length() == 0){
            return root;
        }

        Node q = findChild(root, qPrefix.charAt(0));

        if(q == null){
            return null;
        }
        return search(q, qPrefix.substring(1,qPrefix.length()));
    }

    public void preorderMatch(Node root, String prefix, String forbidden)
    {

        if(root == null){
            return;
        }

        if(root.isWord){
            count++;
        }
        prefix = prefix + root.leadChar;
        Node q = root.firstChild;


        while(q != null)
        {
            if(forbidden.indexOf(q.leadChar) == -1){
                preorderMatch(q, prefix, forbidden);
            }
            q = q.rightSibling;
        }
    }


    public void insert(Node root, String s)
    {
        if(s.length() == 0)
        {
            root.isWord = true;
            return;
        }
        char c = s.charAt(0);
        String ss = s.substring(1,s.length());

        Node q = findChild(root, c);

        if(q == null)
        {
            Node r = new Node(c);
            r.rightSibling = root.firstChild;
            root.firstChild = r;
            insert(r, ss);
        }
        else
            insert(q, ss);

    }

    public Node findChild(Node root, char c)
    {
        if(root.firstChild == null){
            return null;
        }
        Node q = root.firstChild;

        while(q!= null && q.leadChar != c){
            q = q.rightSibling;
        }
        return q;
    }

}


