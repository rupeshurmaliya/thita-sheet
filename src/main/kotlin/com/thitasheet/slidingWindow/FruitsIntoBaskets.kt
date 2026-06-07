package com.thitasheet.slidingWindow


/**
 * Time complexity:  O(N)  where  N  is the length of  fruits . Both  left  and  right  pointers traverse the array from left to right exactly once. Each element is added to the
 *   map once and removed at most once.
 *
 *
 * Space complexity:  O(1) . The map size is capped at  3  because as soon as it reaches  3 , the inner loop immediately shrinks the window to reduce the size back to  2 .
 *
 * [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/description/?envType=problem-list-v2&envId=dogesdee)
 *
 * [YouTube Solution](https://www.youtube.com/watch?v=Oi09pnLLy78)
 */
class FruitsIntoBaskets {

    fun totalFruit(fruits: IntArray): Int {
        var left = 0

        // Maps fruit type to its frequency in the current window
        val fruitsMap = mutableMapOf<Int, Int>()
        var maxFruits = 0

        for (right in fruits.indices) {
            val fruit = fruits[right]
            fruitsMap[fruit] = fruitsMap.getOrDefault(fruit, 0) + 1

            // Shrink the window from the left if we have more than 2 distinct fruit types
            while (fruitsMap.size > 2) {
                val leftFruit = fruits[left]
                fruitsMap[leftFruit] = fruitsMap[leftFruit]!! - 1

                // If frequency becomes 0, remove the fruit from our baskets
                if (fruitsMap[leftFruit] == 0) {
                    fruitsMap.remove(leftFruit)
                }
                left++
            }
            // Record the maximum window length
            maxFruits = maxOf(maxFruits, right - left + 1)
        }
        return maxFruits
    }
}