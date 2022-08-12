package com.akpg._2_linked_lists;

import com.akpg._2_linked_lists.common.SinglyLinkedList;

import java.util.HashSet;

import static com.akpg._2_linked_lists.common.SinglyLinkedList.createSampleLinkedList;

public class _2_1_Duplicate_Nodes {
    /**
     * Given an unsorted linked list, remove duplicates
     *
     * Follow up, do it in place
     */
    public static void main(String[] args) {
        _2_1_Duplicate_Nodes testClass = new _2_1_Duplicate_Nodes();

        SinglyLinkedList singlyLinkedList = createSampleLinkedList(new int[] {1, 2, 3, 2, 3, 4});
        System.out.println(singlyLinkedList.printLinkedList());
        testClass.removeDuplicates(singlyLinkedList);
        System.out.println(singlyLinkedList.printLinkedList());

        System.out.println("\n");

        SinglyLinkedList newList = createSampleLinkedList(new int[] {1, 2, 3, 2, 3, 4});
        System.out.println(newList.printLinkedList());
        testClass.removeDuplicatesConstantMemory(newList);
        System.out.println(newList.printLinkedList());
    }

    /**
     * Use a Set to store previously seen nodes, and if we encounter a duplicate remove it
     *
     * Runtime O(n) visit all nodes
     * Space O(n) set
     */
    void removeDuplicates(SinglyLinkedList list) {
        HashSet<Integer> set = new HashSet<>();
        SinglyLinkedList.Node ptr = list.head;
        SinglyLinkedList.Node prev = null;

        while (ptr != null) {
            if (set.contains(ptr.data)) {
                prev.next = ptr.next;
            } else {
                set.add(ptr.data);
                prev = ptr;
            }
            ptr = ptr.next;
        }
    }


    /**
     * instead of set, we can use a runner ptr to look ahead and remove duplicates of the current node
     *
     * Runtime O(N^2)
     * Space O(1)
     *
     * @param list
     */
    void removeDuplicatesConstantMemory(SinglyLinkedList list) {
        SinglyLinkedList.Node curr = list.head;
        SinglyLinkedList.Node runner;

        while (curr != null) {
            runner = curr;

            while (runner.next != null) {
                if (runner.next.data == curr.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            curr = curr.next;
        }
    }
}
