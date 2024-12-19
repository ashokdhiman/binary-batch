package Linkedlist;

public class LinkedListProblems {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        // Example for Q1
        Node head1 = new Node(14);
        head1.next = new Node(21);
        head1.next.next = new Node(11);
        head1.next.next.next = new Node(30);
        head1.next.next.next.next = new Node(10);
        System.out.println(isPresent(head1, 14) ? "Yes" : "No");

        // Example for Q2
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(4);
        head2.next.next.next = new Node(5);
        insertAfter(head2.next, 3);
        printList(head2);

        // Example for Q3
        Node head3 = new Node(1);
        head3.next = new Node(1);
        head3.next.next = new Node(2);
        head3.next.next.next = new Node(3);
        head3.next.next.next.next = new Node(3);
        head3 = removeDuplicates(head3);
        printList(head3);

        // Example for Q4
        Node head4 = new Node(1);
        head4.next = new Node(2);
        head4.next.next = new Node(2);
        head4.next.next.next = new Node(1);
        System.out.println(isPalindrome(head4) ? "true" : "false");

        // Example for Q5
        Node list1 = new Node(5);
        list1.next = new Node(6);
        list1.next.next = new Node(3);
        Node list2 = new Node(8);
        list2.next = new Node(4);
        list2.next.next = new Node(2);
        Node sumList = addTwoNumbers(list1, list2);
        printList(sumList);
    }

    // Q1: Check if X is present in the linked list
    public static boolean isPresent(Node head, int x) {
        while (head != null) {
            if (head.data == x) return true;
            head = head.next;
        }
        return false;
    }

    // Q2: Insert a node at a given position
    public static void insertAfter(Node prevNode, int newData) {
        if (prevNode == null) return;
        Node newNode = new Node(newData);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
    }

    // Q3: Remove duplicates from sorted linked list
    public static Node removeDuplicates(Node head) {
        Node current = head;
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    // Q4: Check if linked list is a palindrome
    public static boolean isPalindrome(Node head) {
        Node slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            Node temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }
        if (fast != null) slow = slow.next;
        while (slow != null) {
            if (slow.data != prev.data) return false;
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }

    // Q5: Add two numbers represented by linked lists
    public static Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = (l1 != null ? l1.data : 0) + (l2 != null ? l2.data : 0) + carry;
            carry = sum / 10;
            current.next = new Node(sum % 10);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }

    // Helper function to print the linked list
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}

