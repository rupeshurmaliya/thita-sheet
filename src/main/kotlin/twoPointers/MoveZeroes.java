package twoPointers;

/**
 * Created by Rupesh Urmaliya on 18/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/move-zeroes/">283. Move Zeroes</a>
 */


public class MoveZeroes {


    /**
     * Moves all 0s to the end of the array while maintaining the relative order
     * of the non-zero elements.
     * <p>
     * The operation is performed in-place.
     * <p>
     * Time Complexity: O(N) - Single pass over the array.
     * <p>
     * Space Complexity: O(1) - In-place modification.
     *
     * @param nums The array to modify.
     */
    private void moveZeroes(int[] nums) {
        int insertZero = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[insertZero] = num;
                insertZero++;
            }
        }
        while (insertZero < nums.length) {
            nums[insertZero] = 0;
            insertZero++;
        }
    }


    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.moveZeroes(nums);
    }
}
