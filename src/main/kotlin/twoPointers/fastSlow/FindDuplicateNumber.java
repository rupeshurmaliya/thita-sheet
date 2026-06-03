package twoPointers.fastSlow;

/**
 * Created by Rupesh Urmaliya on 15/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/find-the-duplicate-number/description/">287. Find the Duplicate number</a>
 */


public class FindDuplicateNumber {


    /**
     * Finds the duplicate number in an array of n+1 integers where each integer is
     * between 1 and n (inclusive), using Floyd's Cycle-Finding Algorithm.
     * * The array is treated as a linked list where the value at index 'i'
     * points to the index 'nums[i]'. The presence of a duplicate creates a cycle.
     * * Time Complexity: O(N)
     * Space Complexity: O(1)
     * <p>
     * <a href="https://www.youtube.com/watch?v=wjYnzkAhcNk">find the duplicate</a>
     */

    private int findDuplicate(int[] nums) {
        // Step 1: Detect the intersection point (where slow and fast meet)

        int tortoise = nums[0]; // Start at the value pointed to by index 0
        int hare = nums[nums[0]]; // Start at the value pointed to by the value at index 0 (2 steps ahead)

        // The loop continues until the two pointers meet inside the cycle.
        while (tortoise != hare) {
            // Tortoise moves one step (nums[i])
            tortoise = nums[tortoise];

            // Hare moves two steps (nums[nums[i]])
            hare = nums[nums[hare]];
        }

        // Step 2: Find the entry point of the cycle (which is the duplicate number)

        // Reset the tortoise pointer to the start of the array (index 0).
        // Note: The actual value at index 0 is used as the starting point in the list traversal,
        // but for finding the cycle entry, we must start from the true beginning.
        int ptr1 = 0;

        // The hare pointer (now called ptr2) is left at the intersection point.
        int ptr2 = hare;

        // Move both pointers one step at a time until they meet.
        // The meeting point will be the start of the cycle, which is the duplicate.
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        // The meeting point (ptr1 or ptr2) is the duplicate number.
        return ptr1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
        int num = findDuplicateNumber.findDuplicate(nums);
        System.out.println("Duplicate number is : " + num);
    }

}
