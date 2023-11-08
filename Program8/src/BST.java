// Diego Salas
//CS 2050

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class BinarySearchTree
{
    Node root;

    BinarySearchTree()
    {
        root = null;
    }

    void insert(String data)
    {
        root = insertRec(root, data.toLowerCase());
    }

    Node insertRec(Node root, String data) // Recursive method to insert a node in the BST
    {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        int compareResult = data.compareTo(root.data);
        if (compareResult < 0)
            root.left = insertRec(root.left, data);
        else if (compareResult > 0)
            root.right = insertRec(root.right, data);

        return root;
    }

    int countNodes(Node root)     // Method that well counts nodes in the BST

    {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    int height(Node root) // calculates height of BST
    {
        if (root == null)
            return 0;
        else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            if (leftHeight > rightHeight)
                return (leftHeight + 1);
            else
                return (rightHeight + 1);
        }
    }

    long getMaxPossibleNodes(int height)
    {
        return (long) (Math.pow(2, height) - 1);
    }

    void postOrder(Node root, BufferedWriter writer) throws IOException
    {
        if (root != null) {
            postOrder(root.left, writer);
            postOrder(root.right, writer);
            writer.write(root.data + "\n");
        }
    }

    void analyzeTree(String filePath) // the method that analyzes the tree
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            int nodeCount = countNodes(root);
            writer.write("Number of nodes: " + nodeCount + "\n");
            int treeHeight = height(root) - 1;
            writer.write("Height of the tree: " + treeHeight + "\n");
            long maxPossibleNodes = getMaxPossibleNodes(treeHeight);
            writer.write("Max possible nodes at height: " + maxPossibleNodes + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
