package com.akpg._2_linked_lists;

import com.akpg._2_linked_lists.common.SinglyLinkedList;
import com.akpg._2_linked_lists.common.SinglyLinkedList.Node;

/**
 * Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes
 * greater than or equal to x. If x is contained within the list, the values of x only need to be after the elements
 * less than x, the partition element x can appear anywhere in the "right" partition, it does not need to appear
 * between the left and right partitions.
 *
 * Example:
 *
 * Input - 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5]
 * Output -  3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 */
public class _2_4_Partition {
    public static void main(String[] args) {
        _2_4_Partition testClass = new _2_4_Partition();
        SinglyLinkedList list = SinglyLinkedList.createSampleLinkedList(new int[] {3, 5, 8, 5, 10, 2, 1});
        testClass.partition(list.head, 5);
    }

    /**
     * Create two lists, one for nodes less than x (left) and one for nodes larger than or equal to x (right)
     * Then append the right list to the left and return the left list's head
     *
     * Runtime O(n)
     */
    private Node partition(Node node, int x) {
        System.out.println("input: " + node.printListFromNode() + " | partition value: " + x);

        Node left = new Node();
        Node leftPtr = left;
        Node right = new Node();
        Node rightPtr = right;

        while (node != null) {
            // append to both lists
            if (node.data < x) {
                leftPtr.next = node;
                leftPtr = node;
            } else {
                rightPtr.next = node;
                rightPtr = node;
            }
            node = node.next;
        }

        leftPtr.next = right.next;
        rightPtr.next = null;

        System.out.println(left.next.printListFromNode());

        return left.next;
    }
}
