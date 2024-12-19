package Tree;
import java.util.Stack;

public class BST {

    static class Node {
        int val;
        Node left, right;

        Node(int x) {
            val = x;
            left = right = null;
        }
    }

    // Q1: Iterative program to search for an element in BST
    public Node search(Node root, int key) {
        Node current = root;

        while (current != null) {
            if (key == current.val) {
                return current;
            }
            if (key < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;  // Return null if key is not found
    }

    // Q2: Find the k'th largest node in BST
    public Node kthLargest(Node root, int k) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        int count = 0;

        // Perform reverse inorder traversal (right, root, left)
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.right;
            }
            current = stack.pop();
            count++;
            if (count == k) {
                return current;
            }
            current = current.left;
        }
        return null;  // If k is larger than the number of nodes in the tree
    }

    // Q3: Find a pair with a given sum in the BST
    public boolean findPairWithSum(Node root, int sum) {
        if (root == null) return false;

        Stack<Node> stack1 = new Stack<>();  // For inorder traversal (left to right)
        Stack<Node> stack2 = new Stack<>();  // For reverse inorder traversal (right to left)

        Node current1 = root;
        Node current2 = root;

        // Initialize the stacks with the leftmost and rightmost nodes
        while (current1 != null) {
            stack1.push(current1);
            current1 = current1.left;
        }
        while (current2 != null) {
            stack2.push(current2);
            current2 = current2.right;
        }

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            Node node1 = stack1.peek();
            Node node2 = stack2.peek();

            int currentSum = node1.val + node2.val;
            if (currentSum == sum) {
                System.out.println("Pair: (" + node1.val + ", " + node2.val + ")");
                return true;
            } else if (currentSum < sum) {
                current1 = node1.right;
                stack1.pop();
                while (current1 != null) {
                    stack1.push(current1);
                    current1 = current1.left;
                }
            } else {
                current2 = node2.left;
                stack2.pop();
                while (current2 != null) {
                    stack2.push(current2);
                    current2 = current2.right;
                }
            }
        }
        return false;  // No pair found
    }

    // Q4: Find the inorder predecessor of a given key
    public Node inorderPredecessor(Node root, int key) {
        Node predecessor = null;
        Node current = root;

        // Search for the node
        while (current != null) {
            if (key == current.val) {
                // If the node has a left child, find the rightmost node in the left subtree
                if (current.left != null) {
                    predecessor = current.left;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                }
                break;
            } else if (key < current.val) {
                current = current.left;
            } else {
                predecessor = current;  // Potential predecessor
                current = current.right;
            }
        }
        return predecessor;
    }

    // Q5: Find the lowest common ancestor (LCA) of two nodes
    public Node lowestCommonAncestor(Node root, int x, int y) {
        if (root == null) return null;

        if (root.val > x && root.val > y) {
            return lowestCommonAncestor(root.left, x, y);
        } else if (root.val < x && root.val < y) {
            return lowestCommonAncestor(root.right, x, y);
        }
        return root;  // If x and y lie on opposite sides or one of them is equal to root
    }

    // Helper method to insert nodes in the BST
    public Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.val) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        return root;
    }

    // Driver method to test the functionality
    public static void main(String[] args) {
        BST tree = new BST();

        // Construct the sample BST
        Node root = null;
        int[] nodes = {15, 10, 20, 8, 12, 16, 25};
        for (int node : nodes) {
            root = tree.insert(root, node);
        }

        // Q1: Search for 25
        Node searchResult = tree.search(root, 25);
        if (searchResult != null) {
            System.out.println("Found: " + searchResult.val);
        } else {
            System.out.println("Not Found.");
        }

        // Q2: Find the 2nd largest node
        Node kthLargestNode = tree.kthLargest(root, 2);
        if (kthLargestNode != null) {
            System.out.println("2nd Largest Node: " + kthLargestNode.val);
        } else {
            System.out.println("Node not found.");
        }

        // Q3: Find a pair with sum 14
        boolean foundPair = tree.findPairWithSum(root, 14);
        if (!foundPair) {
            System.out.println("No pair found with sum 14.");
        }

        // Q4: Find the inorder predecessor of a given key
        int[] keys = {15, 10, 20, 8, 12, 16, 25};
        for (int key : keys) {
            Node predecessor = tree.inorderPredecessor(root, key);
            if (predecessor != null) {
                System.out.println("Predecessor of " + key + " is " + predecessor.val);
            } else {
                System.out.println("Predecessor doesn't exist for node " + key);
            }
        }

        // Q5: Find the lowest common ancestor (LCA) of nodes 6 and 12
        Node lca = tree.lowestCommonAncestor(root, 6, 12);
        if (lca != null) {
            System.out.println("LCA of 6 and 12: " + lca.val);
        } else {
            System.out.println("LCA not found.");
        }
    }
}

