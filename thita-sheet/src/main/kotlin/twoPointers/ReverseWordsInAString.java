package twoPointers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rupesh Urmaliya on 23/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/reverse-words-in-a-string/description/">151. Reverse Words in a String</a>
 */


public class ReverseWordsInAString {

    private String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i].trim();
            stringBuilder.append(word);
            if (i > 0) stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    private String reverseWordsAlternative(String s) {
        String trimmed = s.trim();
        List<String> wordList = Arrays.asList(trimmed.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public static void main(String[] args) {
        ReverseWordsInAString reverseWordsInAString = new ReverseWordsInAString();
        String result = reverseWordsInAString.reverseWords("the sky is blue");
        System.out.println(result);
        System.out.println(reverseWordsInAString.reverseWords("  hello world  "));
        System.out.println(reverseWordsInAString.reverseWords("a good   example"));
    }
}
