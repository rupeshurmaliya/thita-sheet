package com.thitasheet.slidingWindow;

/**
 * Created by Rupesh Urmaliya on 08/12/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/longest-repeating-character-replacement/description/">424. Longest Repeating Character Replacement</a>
 */


public class LongestRepeatingCharacterReplacement {

    private int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] freq = new int[26];
        int maxLen = 0;
        int maxFreq = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            freq[rightChar - 'A']++;
            maxFreq = Math.max(maxFreq, freq[rightChar - 'A']);

            int windowLength = right - left + 1;
            if (windowLength - maxFreq > k) {
                char leftChar = s.charAt(left);
                freq[leftChar - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement longestRepeatingCharacterReplacement = new LongestRepeatingCharacterReplacement();
        int result = longestRepeatingCharacterReplacement.characterReplacement("ABAB", 2);
        System.out.println("Longest repeating character replacement: " + result);
    }
}
