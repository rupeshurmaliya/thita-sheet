package com.thitasheet.slidingWindow;

/**
 * Created by Rupesh Urmaliya on 23/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/minimum-window-substring/description/">76. Minimum Window Substring</a>
 */


public class MinimumWindowSubstring {

    //https://www.youtube.com/watch?v=jSto0O4AJbM&t=1s

    private String minWindow(String s, String t) {
        if (t.length() == 0 || s.length() == 0 || t.length() > s.length()) {
            return "";
        }

        // 1. Create a frequency map for the target string t
        // Using an array of size 128 to cover all standard ASCII characters
        int[] required = new int[128];
        for (char c : t.toCharArray()) {
            required[c]++;
        }

        int left = 0;
        int right = 0;
        int minLen = s.length() + 1; // Stores the minimum length found
        int minStart = 0;            // Stores the starting index of the minimum window
        int requiredCount = t.length(); // Count of characters from T yet to be fulfilled

        while (right < s.length()) {
            char rightChar = s.charAt(right);

            // 2. Expand the window (move right)
            // If the current right character is one of the required characters,
            // decrement its requirement count.
            if (required[rightChar] > 0) {
                requiredCount--;
            }
            required[rightChar]--; // This acts as a 'window' counter, tracking the
            // *excess* or *remaining need* of a char.
            // A positive value in 'required' means we still need that char.

            // 3. Contract the window (move left) when a valid window is found
            while (requiredCount == 0) {
                // Current window [left, right] is a valid solution
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);

                // Check if the character at 'left' is essential for the window's validity.
                // An essential character is one whose count in 'required' goes from 0 to positive
                // after being removed from the window (i.e., we needed it, and now we don't have enough).
                required[leftChar]++;
                if (required[leftChar] > 0) {
                    requiredCount++; // The window is now invalid (needs more of leftChar)
                }

                left++; // Shrink the window
            }

            right++; // Move the right pointer to continue expansion
        }

        // 4. Return the result
        return minLen > s.length() ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        String str = "ADOBECODEBANC";
        String target = "ABC";
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        String result = minimumWindowSubstring.minWindow(str, target);
        System.out.println("Min window " + result);
    }
}
