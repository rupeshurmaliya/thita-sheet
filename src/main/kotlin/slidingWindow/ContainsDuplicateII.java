package slidingWindow;

import java.util.HashSet;

/**
 * Created by Rupesh Urmaliya on 23/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/contains-duplicate-ii/submissions/1837859800/">219. Contains Duplicate II</a>
 */


public class ContainsDuplicateII {

    private boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // If the set already contains the number, we've found a duplicate
            // within the k-distance window.
            if (!set.add(nums[i])) {
                return true;
            }
            // Maintain the window size. If the set size exceeds k,
            // remove the oldest element, which is at index i - k.
            if (set.size() > k) {
                set.remove(i - k);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        ContainsDuplicateII containsDuplicateII = new ContainsDuplicateII();
        boolean containsDuplicate = containsDuplicateII.containsNearbyDuplicate(nums, 3);
        System.out.println(containsDuplicate);
    }
}
