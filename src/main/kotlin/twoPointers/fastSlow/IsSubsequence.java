package twoPointers.fastSlow;

/**
 * Created by Rupesh Urmaliya on 15/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/is-subsequence/">392. Is Subsequence</a>
 */


public class IsSubsequence {

    private boolean isSubsequence(String s, String t) {
        int sPointer = 0;
        int tPointer = 0;
        while (sPointer < s.length() && tPointer < t.length()) {
            if (s.charAt(sPointer) == t.charAt(tPointer)) {
                sPointer++;
            }
            tPointer++;
        }
        return sPointer == s.length();
    }


    public static void main(String[] args) {
        IsSubsequence isSubsequence = new IsSubsequence();
        boolean isSubsequenceString = isSubsequence.isSubsequence("abc", "ahbgdc");
        System.out.println("Is subsequence string " + isSubsequenceString);
    }
}
