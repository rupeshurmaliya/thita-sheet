package twoPointers.sortedArraysTargetSum;

/**
 * Created by Rupesh Urmaliya on 09/11/25
 * <p>
 * Que: <a href="https://leetcode.com/problems/container-with-most-water/description/">Container with most water</a>
 */


public class ContainerWithMostWater {

    private int maxArea(int[] height) {
        int startIndex = 0;
        int endIndex = height.length - 1;
        int maxArea = 0;
        while (startIndex < endIndex) {
            int range = Math.min(height[startIndex], height[endIndex]);
            int width = endIndex - startIndex;
            maxArea = Math.max(range * width, maxArea);
            if (height[startIndex] < height[endIndex]) startIndex++;
            else endIndex--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        int maxArea = containerWithMostWater.maxArea(height);
        System.out.println("Container with the most water " + maxArea);
    }
}
