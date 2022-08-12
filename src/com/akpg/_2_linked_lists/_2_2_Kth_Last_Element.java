package com.akpg._2_linked_lists;

import com.akpg._2_linked_lists.common.SinglyLinkedList;
import com.akpg._2_linked_lists.common.SinglyLinkedList.Node;

import static com.akpg._2_linked_lists.common.SinglyLinkedList.createSampleLinkedList;


public class _2_2_Kth_Last_Element {
    class Index {
        public int val = 0;
    }

    public static void main(String[] args) {
        _2_2_Kth_Last_Element testClass = new _2_2_Kth_Last_Element();

        SinglyLinkedList list = createSampleLinkedList(new int[] {1, 2, 3, 4, 5});
        System.out.println(testClass.getKthLastElement(list, 0));
        System.out.println(testClass.getKthLastElement(list, 1));
        System.out.println(testClass.getKthLastElement(list, 4));

        System.out.println("\nimproved approach\n");
        System.out.println(testClass.getKthLastElementImprovementIterative(list, 0));
        System.out.println(testClass.getKthLastElementImprovementIterative(list, 1));
        System.out.println(testClass.getKthLastElementImprovementIterative(list, 4));

        System.out.println("\nrecursive approach\n");
        System.out.println(testClass.recursiveKthToLast(list, 0));
        System.out.println(testClass.recursiveKthToLast(list, 1));
        System.out.println(testClass.recursiveKthToLast(list, 4));
    }

    /**
     * Have to get to the end of the list to find the length n. Then need to "track back" k elements.
     * Since it's singly linked list, no prev pointers, so we need two pointer approach against the list - one to
     * find the length, and the other pointer to iterate to and return the (n - k)th element
     *
     * Runtime = O(n + (n-k)) = O(n)
     * Space O(1)
     */
    private int getKthLastElement(SinglyLinkedList list, int k) {
        System.out.println("input list: " + list.printLinkedList() + " | k: " + k);
        // validation - empty list, k < length, -k
        Node runner = list.head;
        Node ptr = list.head;
        int len = 0;

        while (runner.next != null) {
            runner = runner.next;
            len++;
        }
        for (int i = 0; i < (len - k); i++) {
            ptr = ptr.next;
        }
        return ptr.data;
    }

    /**
     * Move p1 k points ahead and keep p2 at index 0. Then, move both pointers together and once p1 hits the end of
     * the list, you know the difference between p1 and p2 is (n - k), so p2 at this point is pointing at the kth
     * element
     */
    private int getKthLastElementImprovementIterative(SinglyLinkedList list, int k) {
        System.out.println("input list: " + list.printLinkedList() + " | k: " + k);

        if (k == 0) {
            return list.head.data;
        }

        Node p1 = list.head;
        Node p2 = list.head;

        for (int i = 0; i < k + 1; i++) {
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2.data;
    }

    private int recursiveKthToLast(SinglyLinkedList list, int k) {
        System.out.println("input list: " + list.printLinkedList() + " | k: " + k);
        Index idx = new Index();
        Node kthToLast = recursiveKthToLast(list.head, k, idx);
        return kthToLast.data;
    }

    /**
     *  Traverse to the end of the list, and then start backtracking while incrementing a counter. Once this counter
     *  reaches k, we return that node
     *
     *  Space O(n) call stack
     *  Runtime O(n) go through all nodes
     */
    private Node recursiveKthToLast(Node head, int k, Index idx) {
        // reached end of call stack
        if (head == null) {
            return null;
        }

        // build call stack till the end
        Node kthToLast = recursiveKthToLast(head.next, k, idx);

        // increment idx.val for each call stack returned node, if it's == k, then we found what we were looking for
        idx.val = idx.val + 1;
        if (idx.val == k + 1) {
            return head;
        }

        // keep going down the call stack
        return kthToLast;
    }
}
