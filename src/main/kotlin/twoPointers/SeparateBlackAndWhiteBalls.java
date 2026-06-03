package twoPointers;

/**
 * Created by Rupesh Urmaliya on 22/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/separate-black-and-white-balls/description/">2938. Separate Black and White Balls</a>
 */


public class SeparateBlackAndWhiteBalls {

    //https://www.youtube.com/watch?v=E6AKLOdt9jc
    private long minimumSteps(String s) {
        long swaps = 0;
        int blackCount = 0; // Tracks the count of '0's encountered so far

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                // If we encounter a '0', we count it.
                // This '0' needs to be moved to the left partition.
                swaps += blackCount;

            } else { // s.charAt(i) == '1'
                // If we encounter a '1', it is to the right of 'blackCount' zeros
                // that have already been counted (i.e., that belong on the left).

                // Each of those 'blackCount' zeros must eventually pass this '1'.
                // Therefore, 'blackCount' swaps are needed involving this '1'
                // and the '0's that are conceptually (or actually) to its left.
                blackCount++;
            }
        }

        return swaps;
    }

    public static void main(String[] args) {
        SeparateBlackAndWhiteBalls separateBlackAndWhiteBalls = new SeparateBlackAndWhiteBalls();
        long minSteps = separateBlackAndWhiteBalls.minimumSteps("101");
        System.out.println("Minimum steps: " + minSteps);
    }
}
