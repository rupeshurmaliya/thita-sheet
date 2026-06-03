package twoPointers;

/**
 * Created by Rupesh Urmaliya on 23/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/reverse-string-ii/description/">541. Reverse String II</a>
 */


public class ReverseStringII {

    private String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        int n = a.length;
        for (int i = 0; i < n; i += 2 * k) {
            // 'start' is the beginning of the segment to be reversed
            int start = i;
            // 'end' is the end of the segment. It should be the minimum of:
            // 1. The end of the first k characters in the current 2k block (i + k - 1)
            // 2. The end of the string (n - 1)
            int end = Math.min(start + k - 1, n - 1);

            // Reverse the segment a[start...end]
            reverse(a, start, end);
        }
        return new String(a);
    }

    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        ReverseStringII reverseStringII = new ReverseStringII();
        String result = reverseStringII.reverseStr("abcdefg", 2);
        System.out.println(result);
    }
}
