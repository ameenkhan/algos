package com.akpg._2_linked_lists.common;

public class SinglyLinkedList {
    public class Node {
        public int data;
        public Node next;

        public Node(){};

        public Node(int d) {
            this.data = d;
        }

        public void displayNodeData() {
            System.out.println("{ " + data + " } ");
        }
    }

    public Node head;

    public void insertFirst(int data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = head;
        head = newNode;
    }

    public Node deleteFirst() {
        Node temp = head;
        head = head.next;
        return temp;
    }

    // assume no duplicates in list (don't judge me this is a basic impl)
    public Node getNode(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return current;
            }
            current = current.next;
        }
        return current;
    }

    public void insertLast(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next; // we'll loop until current.next is null
        }
        Node newNode = new Node();
        newNode.data = data;
        current.next = newNode;
    }

    public String printLinkedList() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            if (current.next == null) {
                stringBuilder.append(current.data);
            } else {
                stringBuilder.append(current.data + ", ");
            }
            current = current.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Return a singly linked list's head, list looks like - [1, 2, 3, 2, 3, 4]
     */
    public static SinglyLinkedList createSampleLinkedList(int[] vals) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        for (int v : vals) {
            singlyLinkedList.insertLast(v);
        }

        return singlyLinkedList;
    }
}
