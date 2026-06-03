package slidingWindow;

import java.util.HashSet;

/**
 * Created by Rupesh Urmaliya on 23/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/description/">3. Longest Substring Without Repeating Characters</a>
 */


public class LongestSubstringWithoutRepeatingCharacters {

    private int lengthOfLongestSubstring(String s) {
        int startIndex = 0, endIndex = 0, maxLength = 0;
        HashSet<Character> set = new HashSet<>();
        while (endIndex < s.length()) {
            if (!set.contains(s.charAt(endIndex))) {
                set.add(s.charAt(endIndex));
                maxLength = Math.max(maxLength, set.size());
                endIndex++;
            } else {
                set.remove(s.charAt(startIndex));
                startIndex++;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        int count = longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcabcbb");
        System.out.println(count);
    }

}
