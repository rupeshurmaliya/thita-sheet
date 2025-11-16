package twoPointers;

/**
 * Created by Rupesh Urmaliya on 16/11/25
 * <p>
 * Que: <a href="Enter link">Enter title</a>
 */


public class MiddleOfLinkedList {

    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
