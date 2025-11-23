package twoPointers;

/**
 * Created by Rupesh Urmaliya on 22/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/backspace-string-compare/">844. Backspace String Compare</a>
 */


public class BackspaceStringCompare {

    /**
     * Time Complexity: O(M + N). We iterate through both strings once.
     * <p>
     * Space Complexity: O(1). We only use a few integer variables for pointers and skip counts.
     *
     * @param s
     * @param t
     * @return
     */
    private boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1; // Pointer for S
        int j = t.length() - 1; // Pointer for T

        int skipS = 0; // Backspace count for S
        int skipT = 0; // Backspace count for T

        while (i >= 0 || j >= 0) {
            // Find the index of the next valid character for S
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break; // Found the next valid char
                }
            }

            // Find the index of the next valid character for T
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break; // Found the next valid char
                }
            }

            // Comparison:
            // 1. Both pointers point to a valid character
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            }
            // 2. One string ran out of characters while the other didn't (unequal length)
            else if (i >= 0 || j >= 0) {
                return false;
            }

            // Move to the next pair of characters
            i--;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {

        BackspaceStringCompare backspaceStringCompare = new BackspaceStringCompare();
        boolean isEqual = backspaceStringCompare.backspaceCompare("ab#c", "ad#c");
        System.out.println("Is Equal " + isEqual);

    }
}
