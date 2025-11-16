package twoPointers.sortedArraysTargetSum;

import java.util.Arrays;

/**
 * Created by Rupesh Urmaliya on 10/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/boats-to-save-people/description/">881. Boats to Save People</a>
 */


public class BoatsToSavePeople {

    //Time Complexity: O(N log N) due to sorting.
    //Space Complexity: O(1) (excluding input array and sorting space).
    private int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;
        int boats = 0;
        while (start <= end) {
            boats++;
            if (people[end] + people[start] <= limit) {
                start++;
            }
            end--;
        }
        return boats;
    }

    public static void main(String[] args) {
        int[] people = {3, 2, 2, 1};
        BoatsToSavePeople boatsToSavePeople = new BoatsToSavePeople();
        int boats = boatsToSavePeople.numRescueBoats(people, 3);
        System.out.println("Number of boats required: " + boats);
    }
}
