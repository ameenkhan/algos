package com.akpg._2_linked_lists;

import com.akpg._2_linked_lists.common.SinglyLinkedList;
import com.akpg._2_linked_lists.common.SinglyLinkedList.Node;

import java.util.Stack;

/**
 * You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored
 * in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numers
 * and returns the sum as a linked list.
 */
public class _2_5_Sum_Lists {
    public static void main(String[] args) {
        _2_5_Sum_Lists testClass = new _2_5_Sum_Lists();
        SinglyLinkedList list1 = SinglyLinkedList.createSampleLinkedList(new int[] {3, 4, 4, 9});
        SinglyLinkedList list2 = SinglyLinkedList.createSampleLinkedList(new int[] {7, 6, 5});
        Node sum = testClass.sumListsReversed(list1.head, list2.head);
        System.out.println(sum.printListFromNode());

        System.out.println("\nStack impl with forward linked");
        list1 = SinglyLinkedList.createSampleLinkedList(new int[] {9, 4, 4, 3});
        list2 = SinglyLinkedList.createSampleLinkedList(new int[] {5, 6, 7});
        sum = testClass.sumListsForward(list1.head, list2.head);
        System.out.println(sum.printListFromNode());

        System.out.println("\nno extra mem impl with forward linked");
        list1 = SinglyLinkedList.createSampleLinkedList(new int[] {9, 4, 4, 3});
        list2 = SinglyLinkedList.createSampleLinkedList(new int[] {5, 6, 7});
        sum = testClass.sumListsForwardNoStack(list1.head, list2.head);
        System.out.println(sum.printListFromNode());
    }

    /**
     * Find the lengths of each list. Then add each respective unit without any carries. For example
     *   9443
     * +  567
     * = 9,9,10,10 <-- head
     *
     * Now use the carry and normalize the list similar to the reversed approach
     *
     * 1 0 0 1 0
     */
    private Node sumListsForwardNoStack(Node node1, Node node2) {
        System.out.println("list 1: " + node1.printListFromNode() + " | list 2: " + node2.printListFromNode());
        Node head = new Node();

        int lenList1 = 0;
        int lenList2 = 0;

        Node ptr1 = node1;
        Node ptr2 = node2;

        while (ptr1 != null) {
            lenList1++;
            ptr1 = ptr1.next;
        }
        while (ptr2 != null) {
            lenList2++;
            ptr2 = ptr2.next;
        }

        // reset pointers
        ptr1 = node1;
        ptr2 = node2;

        while (lenList1 > 0 && lenList2 > 0) {
            int sum = 0;
            // list 1 has more digits, then add only from list 1 and leave ptr2 alone
            // = sign used here to add list1 val in first if block and list2 val from 2nd if block
            if (lenList1 >= lenList2) {
                sum += ptr1.data;
                ptr1 = ptr1.next;
                lenList1--;
            }
            if (lenList1 < lenList2) {
                sum += ptr2.data;
                ptr2 = ptr2.next;
                lenList2--;
            }
            // Append to the head
            //          node1 <- null
            // node1 <- node2 <- null
            Node newNode = new Node(sum);
            newNode.next = head.next;
            head.next = newNode;
        }
        System.out.println(head.next.printListFromNode());

        ptr1 = head;
        head = null;
        int carry = 0;
        int sum = 0;

        // now we need to take care of the carry and reverse the input
        // for example 10, 10, 9, 9 -> 0, 1, 0, 0, 1 -> 1, 0, 0, 1, 0
        while (ptr1 != null) {
            sum = ptr1.data + carry;
            carry = sum / 10;

            Node newNode = new Node(sum % 10);
            newNode.next = head;
            head = newNode;

            ptr1 = ptr1.next;
        }

        if (carry != 0) {
            Node newNode = new Node(carry);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }

    /**
     * Two pointers for each list, add the number. Keep track of the carry. if either pointer is null then just
     * append the other pointers number. But the catch here is if there is a carry like 99999 + 9. So the sum is
     * complete when both pointers are at the end of their lists and the carry is 0
     */
    private Node sumListsReversed(Node node1, Node node2) {
        System.out.println("list 1: " + node1.printListFromNode() + " | list 2: " + node2.printListFromNode());

        Node dummyNode = new Node();
        Node sumPtr = dummyNode;
        Node ptr1 = node1;
        Node ptr2 = node2;
        int a;
        int b;
        int sum;
        int carry = 0;

        while (ptr1 != null || ptr2 != null || carry != 0) {
            a = (ptr1 != null) ? ptr1.data : 0;
            b = (ptr2 != null) ? ptr2.data : 0;

            sum = a + b + carry;
            carry = sum / 10;

            sumPtr.next = new Node(sum % 10);
            sumPtr = sumPtr.next;

            if (ptr1 != null) {
                ptr1 = ptr1.next;
            }
            if (ptr2 != null) {
                ptr2 = ptr2.next;
            }
        }

        return dummyNode.next;
    }

    /**
     * Do the same as the reversed approach but isntead of lists use stacks. LIFO means that the top of the stack
     * will be the 1s unit nad each pop will be the next. One stack is empty means that numbers have diff digits.
     *
     * Runtime O(N+M)
     * Space O(N + M)
     */
    private Node sumListsForward(Node node1, Node node2) {
        System.out.println("list 1: " + node1.printListFromNode() + " | list 2: " + node2.printListFromNode());
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        Node ptr1 = node1;
        Node ptr2 = node2;

        // construct stacks
        while (ptr1 != null) {
            s1.push(ptr1.data);
            ptr1 = ptr1.next;
        }
        while (ptr2 != null) {
            s2.push(ptr2.data);
            ptr2 = ptr2.next;
        }

        int carry = 0;
        Node dummy = null;

        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int a = s1.isEmpty() ? 0 : s1.pop();
            int b = s2.isEmpty() ? 0 : s2.pop();

            int sum = a + b + carry;
            carry = sum / 10;

            Node newNode = new Node(sum % 10);
            newNode.next = dummy;
            dummy = dummy = newNode;
        }

        return dummy;
    }
}
