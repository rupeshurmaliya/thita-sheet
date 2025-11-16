package twoPointers;

/**
 * Created by Rupesh Urmaliya on 16/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/">2095. Delete the Middle Node of a Linked List</a>
 */


public class DeleteMiddleNodeOfLinkedList {

    //https://www.youtube.com/watch?v=iMveCdYt5zY
    private ListNode deleteMiddle(ListNode head) {
        if (head.next == null) return null;

        if (head.next.next == null) {
            head.next = null;
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        prev.next = fast;
        slow.next = null;
        return head;
    }

    public ListNode deleteMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        // --- Pass 1: Calculate the length of the list (L) ---
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // --- Pass 2: Traverse to the Predecessor of the Middle Node ---
        ListNode dummy = new ListNode(0, head);
        ListNode predecessor = dummy;

        // We need (length / 2) steps starting from the dummy node (index -1)
        // to reach the node *before* the middle node.
        int steps = length / 2;

        for (int i = 0; i < steps; i++) {
            predecessor = predecessor.next;
        }

        // After the loop, 'predecessor' is pointing to the node right before the middle node.

        // --- Deletion ---
        // The node to delete is predecessor.next.
        predecessor.next = predecessor.next.next;

        return dummy.next;
    }
}
