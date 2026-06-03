package twoPointers;

/**
 * Created by Rupesh Urmaliya on 16/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/remove-element/description/">27. Remove Element</a>
 */


public class RemoveElement {

    /**
     * <a href="https://www.youtube.com/watch?v=5kuhq82Xdqk">Good explanation </a>
     */
    private int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count] = nums[i];
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        RemoveElement removeElement = new RemoveElement();
        int count = removeElement.removeElement(nums, 3);
        System.out.println("Count: " + count);
    }
}
