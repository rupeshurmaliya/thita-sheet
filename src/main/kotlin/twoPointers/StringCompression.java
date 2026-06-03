package twoPointers;

/**
 * Created by Rupesh Urmaliya on 18/11/25
 * <p>
 * Que: <a href="Enter link">443. String Compression</a>
 */


public class StringCompression {

    private int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int write = 0;
        int read = 0;
        int n = chars.length;
        while (read < n) {
            char currentChar = chars[read];
            int count = 0;

            while (read < n && chars[read] == currentChar) {
                read++;
                count++;
            }
            chars[write++] = currentChar;
            if (count > 1) {
                String countStr = String.valueOf(count); //This is required, when count is in double digits. e.q. 12, 13, 17
                for (int i = 0; i < countStr.length(); i++) { //"a","b","b","b","b","b","b","b","b","b","b","b","b" Useful for this case.
                    chars[write++] = countStr.charAt(i);
                }
            }
        }
        return write;
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        StringCompression stringCompression = new StringCompression();
        int arrayLength = stringCompression.compress(chars);
        System.out.println("Array length:" + arrayLength);
    }
}
