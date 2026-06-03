package twoPointers.fastSlow;

import twoPointers.ListNode;

/**
 * Created by Rupesh Urmaliya on 11/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/linked-list-cycle/description/">141. Linked List Cycle</a>
 */


public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        boolean hasCycle = false;
        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return hasCycle;
    }

    public static void main(String[] args) {

    }
}
