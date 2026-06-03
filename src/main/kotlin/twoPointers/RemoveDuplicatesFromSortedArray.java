package twoPointers;

/**
 * Created by Rupesh Urmaliya on 16/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/">26. Remove Duplicates from Sorted Array,</a>
 */


public class RemoveDuplicatesFromSortedArray {

    /**
     * <a href="https://www.youtube.com/watch?v=DEJAZBq0FDA">Solution</a>
     * <p>
     * Note: Can't use extra space
     * <p>
     * Time complexity: O(n)
     * <p>
     * Space complexity: O(1)
     *
     * @param nums
     * @return
     */
    private int removeDuplicates(int[] nums) {
        int first = 0;
        int second = 1;
        while (second <= nums.length - 1) {
            if (nums[first] != nums[second]) {
                first++;
                nums[first] = nums[second];
            }
            second++;
        }
        return first + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        RemoveDuplicatesFromSortedArray removeDuplicatesFromSortedArray = new RemoveDuplicatesFromSortedArray();
        int uniqueElements = removeDuplicatesFromSortedArray.removeDuplicates(nums);
        System.out.println("Unique elements: " + uniqueElements);
    }
}
