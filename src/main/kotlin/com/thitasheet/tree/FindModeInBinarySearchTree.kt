package com.thitasheet.tree


/**
 *  **Time Complexity: O(N)**
 *   - Every node in the BST is visited exactly once during the in-order traversal.
 *   - Updating the streak and list operations take `O(1)` amortized time per node.
 *   - Converting the list to `IntArray` takes `O(M)` time where `M <= N` is the number of modes.
 *   - Overall time: `O(N)`.
 *
 *  **Space Complexity: O(H)**
 *   - Auxiliary recursion stack memory is bounded by tree height `H`.
 *   - Balanced BST: `O(log N)` call stack space.
 *   - Skewed BST: `O(N)` call stack space.
 *   - *Note:* The problem's follow-up explicitly notes that the implicit recursion call stack does not count as extra space, making this an `O(1)` auxiliary space solution outside the output array.
 *
 * [501. Find Mode in Binary Search Tree](https://leetcode.com/problems/find-mode-in-binary-search-tree/description/)
 */

class FindModeInBinarySearchTree {


    /**
     * ```text
     * 1. In-Order Flow:
     *       • Recursively visit node.left.
     *       • Process the current node.val.
     *       • Recursively visit node.right.
     *
     * 2. Processing Each Node Value:
     *       • Update Streak:
     *           • If node.val == currentVal, increment currentStreak++.
     *           • Otherwise, we are starting a new number: set currentVal = node.val and reset currentStreak = 1.
     *       • Evaluate Against maxStreak:
     *           • If currentStreak > maxStreak:
     *               • A new highest frequency is established!
     *               • Update maxStreak = currentStreak.
     *               • Clear the modes list (all previous candidates had a lower frequency).
     *               • Add node.val to modes.
     *           • Else if currentStreak == maxStreak:
     *               • This value ties the current highest frequency.
     *               • Add node.val to modes.
     *           • Else (currentStreak < maxStreak):
     *               • Do nothing; this value hasn't reached the maximum frequency yet.
     *
     * 3. Why No Duplicates in modes?
     *       • Because identical elements are visited consecutively, for any number X, its streak strictly increments 1, 2, ..., K.
     *       • It only hits maxStreak once (or clears and adds once if it beats maxStreak).
     *       • Once the traversal moves to a larger number, X will never appear again.
     * ```
     */
    fun findModes(root: TreeNode?): IntArray {
        // State variables for tracking streaks during in-order traversal
        val modes = mutableListOf<Int>()
        var currentVal: Int? = null
        var maxStreak = 0
        var currentStreak = 0

        // 1. Traverse left subtree
        fun inorder(node: TreeNode?) {
            if (node == null) return
            inorder(node.left)

            // 2. Process current node
            val nodeVal = node.`val`
            if (currentVal != null && currentVal == nodeVal) {
                currentStreak++
            } else {
                currentVal = nodeVal
                currentStreak = 1
            }
            // Check if current streak beats or matches the maximum frequency
            if (currentStreak > maxStreak) {
                maxStreak = currentStreak
                modes.clear()
                modes.add(nodeVal)
            } else if (currentStreak == maxStreak) {
                modes.add(nodeVal)
            }
            // 3. Traverse right subtree
            inorder(node.right)
        }

        inorder(root)
        return modes.toIntArray()
    }

    /**
     * Time complexity: O(N)
     *
     * Space Complexity: O(N+H) extra space (for the map)
     *
     * Solved though iterative inorder traversal
     */
    fun findModeNBruteForce(root: TreeNode?): IntArray {
        val map = mutableMapOf<Int, Int>()
        val stack = ArrayDeque<TreeNode>()
        var current = root

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                stack.addLast(current)
                current = current.left
            }

            val node = stack.removeLast()

            //map[node.`val`] = map[node.`val`]?.plus(1) ?: 1

            map[node.`val`] = map.getOrDefault(node.`val`, 0) + 1

            current = node.right
        }
        val maxCount = map.values.maxOrNull() ?: 0

        val modes = map.filter { it.value == maxCount }.keys.toIntArray()
        return modes
    }
}