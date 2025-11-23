package slidingWindow;

/**
 * Created by Rupesh Urmaliya on 23/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/reverse-vowels-of-a-string/description/">345. Reverse Vowels of a String</a>
 */


public class ReverseVowelsOfAString {

    private String reverseVowels(String s) {
        int start = 0, end = s.length() - 1;
        char[] chars = s.toCharArray();
        while (start < end) {
            if (isVowel(chars[start]) && isVowel(chars[end])) {
                swap(chars, start, end);
                start++;
                end--;
            } else if (isVowel(chars[start]) && !isVowel(chars[end])) {
                end--;
            } else if (!isVowel(chars[start]) && isVowel(chars[end])) {
                start++;
            } else {
                start++;
                end--;
            }
        }
        return new String(chars);
    }

    private boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U');
    }

    private void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    public static void main(String[] args) {
        String s = "IceCreAm";
        ReverseVowelsOfAString reverseVowelsOfAString = new ReverseVowelsOfAString();
        String result = reverseVowelsOfAString.reverseVowels(s);
        System.out.println(result);
        System.out.println(reverseVowelsOfAString.reverseVowels("leetcode"));
    }
}
