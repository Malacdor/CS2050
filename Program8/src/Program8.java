// Diego Salas
//CS 2050


import java.io.*;
class Node
{
    String data;
    Node left, right;

    public Node(String item)
    {
        data = item;
        left = right = null;
    }
}

public class Program8 {
    public static void main(String[] args)
    {
        BinarySearchTree tree = new BinarySearchTree();

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("dracula.txt"));
            String line;
            while ((line = br.readLine()) != null)
            {
                // Split each line into words and insert them into the BST

                String[] words = line.split("[^a-zA-Z]+");
                for (String word : words)
                {
                    if (!word.equals(""))
                    {
                        tree.insert(word.toLowerCase());
                    }
                }
            }
            br.close();
        } catch (IOException e)
        {

            e.printStackTrace();
        }

        tree.analyzeTree("analysis.txt"); // this does the actual analysis
    }
}