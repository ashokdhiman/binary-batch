package Tree;

import java.util.*;

public class BinaryTree {

    // Node class to represent the binary tree
    static class Node {
        int val;
        Node left, right;
        Node(int x) {
            val = x;
            left = right = null;
        }
    }

    // Q1: Spiral level order traversal
    public List<List<Integer>> spiralLevelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current != null) {
                    level.add(current.val);
                    if (current.left != null) queue.offer(current.left);
                    if (current.right != null) queue.offer(current.right);
                }
            }

            if (!leftToRight) {
                Collections.reverse(level);
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    // Q2: Check if the tree is a complete binary tree
    public boolean isCompleteBinaryTree(Node root) {
        if (root == null) return true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        boolean flag = false;  // To check if we found a non-full node

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current == null) {
                flag = true;
            } else {
                if (flag) return false;  // If we already found a non-full node, no more nodes should be present
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        return true;
    }

    // Q3: Reverse level order traversal
    public List<List<Integer>> reverseLevelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                level.add(current.val);
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            result.add(level);
        }

        Collections.reverse(result);  // Reverse the level order
        return result;
    }

    // Q4: Left view of the binary tree
    public List<Integer> leftView(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (i == 0) {
                    result.add(current.val);  // Add the first node at each level
                }
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
        }
        return result;
    }

    // Q5: Convert the binary tree into its mirror and print pre-order
    public Node mirror(Node root) {
        if (root == null) return null;

        // Swap left and right subtrees
        Node left = mirror(root.left);
        Node right = mirror(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    // Pre-order traversal of the tree
    public void preOrder(Node root) {
        if (root == null) return;

        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // Driver method to test the functionality
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(8);
        root.left.left.left = new Node(7);

        // Q1: Spiral Level Order
        System.out.println("Spiral Level Order: " + tree.spiralLevelOrder(root));

        // Q2: Check if Complete Binary Tree
        System.out.println("Is Complete Binary Tree: " + tree.isCompleteBinaryTree(root));

        // Q3: Reverse Level Order
        System.out.println("Reverse Level Order: " + tree.reverseLevelOrder(root));

        // Q4: Left View
        System.out.println("Left View: " + tree.leftView(root));

        // Q5: Mirror Tree and Pre-order traversal
        Node mirroredRoot = tree.mirror(root);
        System.out.print("Pre-order traversal of mirrored tree: ");
        tree.preOrder(mirroredRoot);
    }
}
