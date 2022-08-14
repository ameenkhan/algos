package com.akpg._2_linked_lists;

import com.akpg._2_linked_lists.common.SinglyLinkedList;
import com.akpg._2_linked_lists.common.SinglyLinkedList.Node;

import java.util.Stack;

/**
 * Implement a function to check if a linked list is a palindrome
 */
public class _2_6_Palindrome {
    Node ptr;

    public static void main(String[] args) {

        _2_6_Palindrome testClass = new _2_6_Palindrome();
        System.out.println(testClass.isPalindrome(SinglyLinkedList.createSampleLinkedList(new int[] {0, 1, 2, 1, 0})));
        System.out.println(testClass.isPalindrome(SinglyLinkedList.createSampleLinkedList(new int[] {0, 1, 0, 2})));
        System.out.println(testClass.isPalindrome(SinglyLinkedList.createSampleLinkedList(new int[] {0})));
        System.out.println(testClass.isPalindrome(SinglyLinkedList.createSampleLinkedList(new int[] {1, 1})));

        System.out.println("\nO(1) space impl");
        System.out.println(testClass.isPalindromeNoStack(SinglyLinkedList.createSampleLinkedList(new int[] {0, 1, 2, 1,
                0})));
        System.out.println(testClass.isPalindromeNoStack(SinglyLinkedList.createSampleLinkedList(new int[] {0, 1, 0,
                2})));
        System.out.println(testClass.isPalindromeNoStack(SinglyLinkedList.createSampleLinkedList(new int[] {0})));
        System.out.println(testClass.isPalindromeNoStack(SinglyLinkedList.createSampleLinkedList(new int[] {1, 1})));
    }

    /**
     * Basic solution is to iterate and push everything to a stack. And iterate again and compare that each popped
     * element is the same
     *
     * Runtime O(n)
     * Space O(n)
     */
    public boolean isPalindrome(SinglyLinkedList list) {
        System.out.println(list.printLinkedList());
        Node ptr = list.head;
        Stack<Integer> stack = new Stack<>();

        while (ptr != null) {
            stack.push(ptr.data);
            ptr = ptr.next;
        }

        ptr = list.head;
        while (!stack.isEmpty()) {
            if (ptr.data != stack.pop()) {
                return false;
            }
            ptr = ptr.next;
        }

        return true;
    }

    /**
     * Can do this recursively, recurse to the end of the list. Start returning the node from at the bottom of the
     * call stack, and before each return check if the head ptr = the current node value.
     *
     * Similar to using a stack, but we are just recursively traversing
     *
     *
     */
    private boolean isPalindromeNoStack(SinglyLinkedList list) {
        ptr = list.head;
        return isPalindromeRecursive(list.head);
    }

    boolean isPalindromeRecursive(Node node) {
        isPalindromeRecursive(node.next);
    }
}
