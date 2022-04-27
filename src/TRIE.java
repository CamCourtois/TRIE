import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TRIE {

    Node p;
    public static void main(String[] args) throws FileNotFoundException {

        TRIE wordTrie = new TRIE();

        String instructionLine;
        String[] trieInstructions;

        File inputFile = new File("inputFile.txt");
        Scanner in = new Scanner(inputFile);
        String numLines = in.nextLine();
        int numInstructions = Integer.parseInt(numLines);

        for (int i = 0; i < numInstructions-2; i++){
            instructionLine = in.nextLine();
            trieInstructions = instructionLine.split(" ");

            if(trieInstructions[0].equalsIgnoreCase("IN")){
                String word = trieInstructions[1];
                wordTrie.p = wordTrie.insert(wordTrie.p, word);
            }
            else if(trieInstructions[0].equalsIgnoreCase("QU")){

            }
            else
                return;

        }




    }

    public Node insert(Node p, String s)
    {
        if(s == null){
            p.isWord = true;
            return p;
        }
        char c = s.charAt(0);
        String ss = s.substring(1,s.length());
        Node q = findChild(p, c);

        if(q == null){
            Node r = new Node(c);
            r.rightSibling = p.firstChild;
            p.firstChild = r;
            insert(r, ss);
        }
        else
            insert(q, ss);

        return p;
    }

    public Node findChild(Node p, char c){
        Node q = p.firstChild;

        while(q.leadChar != c){
            q = q.rightSibling;
        }
        return q;
    }

}

class Node
{
    char leadChar;
    boolean isWord = false;
    Node rightSibling;
    Node firstChild;

    public Node(char leadChar){
        this.leadChar = leadChar;
    }

}
