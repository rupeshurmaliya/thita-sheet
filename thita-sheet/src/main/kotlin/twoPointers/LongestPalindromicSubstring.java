package twoPointers;

/**
 * Created by Rupesh Urmaliya on 22/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/longest-palindromic-substring/description/">5. Longest Palindromic Substring</a>
 */


public class LongestPalindromicSubstring {

    private String longestPalindrome(String s) {
        String result = s.substring(0, 1);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            String palindrome = intermediateString(i, i, s);
            if (palindrome != null && palindrome.length() > result.length()) {
                result = palindrome;
            }
            palindrome = intermediateString(i, i + 1, s);
            if (palindrome != null && palindrome.length() > result.length()) {
                result = palindrome;
            }
        }
        return result;
    }


    private String intermediateString(int startIndex, int endIndex, String s) {
        if (startIndex > endIndex) {
            return null;
        }
        while (startIndex >= 0 && endIndex < s.length() && s.charAt(startIndex) == s.charAt(endIndex)) {
            --startIndex;
            ++endIndex;
        }
        return s.substring(startIndex + 1, endIndex);
    }


    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String palindromeString = longestPalindromicSubstring.longestPalindrome("babad");
        System.out.println(palindromeString);
    }


}
