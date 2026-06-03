package twoPointers.fastSlow;

/**
 * Created by Rupesh Urmaliya on 11/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/happy-number/description/">202. Happy Number</a>
 */


public class HappyNumber {

    private boolean isHappy(int n) {
        // Floyd's Cycle-Finding Algorithm (Tortoise and Hare)
        // This is the most efficient method, requiring O(1) extra space.

        // 'slow' moves one step at a time
        int slow = n;

        // 'fast' moves two steps at a time
        int fast = getNext(n);

        // Continue until the pointers meet (cycle detected) OR
        // the slow pointer reaches 1 (happy number).
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);         // Slow moves 1 step
            fast = getNext(getNext(fast)); // Fast moves 2 steps
        }

        // If the loop terminated because 'fast' reached 1, the number is happy.
        return fast == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int digit = n % 10;
            n = n / 10;
            totalSum += digit * digit;
        }
        return totalSum;
    }


    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
        boolean isHappyNumber = happyNumber.isHappy(2);
        System.out.println("Is 2 happy number :" + isHappyNumber);
    }
}
