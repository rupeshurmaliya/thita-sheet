package twoPointers;

/**
 * Created by Rupesh Urmaliya on 22/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/palindromic-substrings/description/">647. Palindromic Substrings</a>
 */


public class PalindromicSubstrings {
    int count = 0;

    private int countSubstring(String s) {
        for (int i = 0; i < s.length(); i++) {
            expandAndCount(i, i, s);
            expandAndCount(i, i + 1, s);
        }
        return count;
    }

    private void expandAndCount(int startIndex, int endIndex, String s) {
        while (startIndex >= 0 && endIndex < s.length() && s.charAt(startIndex) == s.charAt(endIndex)) {
            count++;
            startIndex--;
            endIndex++;
        }
    }

    public static void main(String[] args) {
        PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
        //System.out.println(palindromicSubstrings.countSubstring("aaa"));
        System.out.println(palindromicSubstrings.countSubstring("abc"));
    }
}
