package twoPointers;

/**
 * Created by Rupesh Urmaliya on 16/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/">19. Remove Nth Node From End of List,</a>
 */


public class RemoveNthNodeFromEndOfList {


    /**
     * <a href="https://www.youtube.com/watch?v=3kMKYQ2wNIU">Good explanation</a>
     *
     * @param head - ListNode
     * @param n    - position to be deleted
     * @return return the new ListNode
     */
    private ListNode removeNthFromEndOfList(ListNode head, int n) {
        // Step 1: Create a dummy node to handle edge cases,
        // especially when the node to be removed is the head itself.
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Initialize two pointers: fast and slow
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Step 2: Move the fast pointer n+1 steps ahead.
        // The "+1" is critical because we want the slow pointer to stop
        // *before* the node to be deleted, so it can correctly skip it.
        for (int i = 0; i <= n; i++) {
            // Check for edge case where n is larger than the list length,
            // though problem constraints usually prevent this.
            if (fast == null) {
                return head;
            }
            fast = fast.next;
        }

        // Step 3: Move both pointers until the fast pointer reaches the end (null).
        // Since fast started n+1 steps ahead, when fast is null, slow is at L-n.
        // slow is now pointing to the (L-n)th node, which is the node *before* the target node.
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Step 4: Delete the target node.
        // The target node is slow.next. We skip it by pointing slow.next
        // to the node after the target node (slow.next.next).
        slow.next = slow.next.next;

        // Step 5: Return the new head of the list (skipping the dummy node).
        return dummy.next;
    }

}
