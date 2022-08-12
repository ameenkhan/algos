package com.akpg._2_linked_lists;

import com.akpg._2_linked_lists.common.SinglyLinkedList;
import com.akpg._2_linked_lists.common.SinglyLinkedList.Node;

import static com.akpg._2_linked_lists.common.SinglyLinkedList.createSampleLinkedList;

/**
 * delete a node in the middle (i.e., any node but the first and last node, not necessarily the exact middle) of a
 * single linked list, given only acess to that node.
 *
 * EXAMPLE:
 * Input: the node 3 from the linked list   [1, 2, 3, 4, 5, 6]
 * Result: void, but linked list looks like [1, 2, 4, 5, 6]
 */
public class _2_3_Delete_Middle_Node {
    public static void main(String[] args) {
        _2_3_Delete_Middle_Node testClass = new _2_3_Delete_Middle_Node();

        SinglyLinkedList list = createSampleLinkedList(new int[] {1, 2, 3, 4, 5, 6});
        System.out.println(list.printLinkedList());
        Node input3 = list.getNode(3);
        testClass.deleteNode(input3);
        System.out.println(list.printLinkedList());
    }

    /**
     * Make this current node the same as is its next node
     * Then remove the "next node" which is a representative of the input to this method by removing ptr
     */
    private boolean deleteNode(Node input) {
        if (input == null || input.next == null) {
            return false;
        }

        input.data = input.next.data;
        input.next = input.next.next;
        return true;
    }


}
