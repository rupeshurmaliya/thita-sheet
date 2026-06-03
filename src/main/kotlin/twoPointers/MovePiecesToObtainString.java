package twoPointers;

/**
 * Created by Rupesh Urmaliya on 22/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/move-pieces-to-obtain-a-string/description/">2337. Move Pieces to Obtain a String</a>
 */


public class MovePiecesToObtainString {

    /**
     * Time Complexity: O(N), where $N$ is the length of the string. Both pointers, i and j,
     * traverse their respective strings at most once.
     * <p>
     * Space Complexity: O(1), as only a few constant-size variables are used.
     * <p>
     *
     * @param start
     * @param target
     * @return
     */
    private boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0;
        int j = 0;
        while (i < n || j < n) {
            while (i < n && start.charAt(i) == '_') {
                i++;
            }
            while (j < n && start.charAt(j) == '_') {
                j++;
            }

            if (i == n || j == n) {
                return i == n && j == n;
            }

            if (start.charAt(i) != target.charAt(j)) {
                return false;
            }
            char piece = start.charAt(i);
            if (piece == 'L') {
                if (i < j) return false;

            } else {
                if (i > j) return false;
            }
            i++;
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        MovePiecesToObtainString movePiecesToObtainString = new MovePiecesToObtainString();
        boolean canChange = movePiecesToObtainString.canChange("_L__R__R_", "L______RR");
        System.out.println("Can change: " + canChange);
    }
}
