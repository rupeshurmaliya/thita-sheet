package com.thitasheet.tree


/**
 *    Time Complexity: O(N * H)
 *
 * * Visiting every node in the binary tree takes O(N) time.
 * * At each of the L leaves, reversing the StringBuilder and performing string comparison takes O(H) time, where H is the height of the tree.
 * * In a balanced tree, H = O(log N), yielding O(N * log N).
 * * In the worst-case skewed tree, H = O(N), yielding O(N^2). Given N <= 8500, this runs comfortably within execution time limits.
 *
 *
 *    Space Complexity: O(H)
 *
 * * The recursion call stack uses at most O(H) memory.
 * * The StringBuilder holds at most H characters at any point.
 * * In a balanced tree: O(log N) auxiliary space.
 * * In a skewed tree: O(N) auxiliary space in the worst case.
 *
 * [988. Smallest String Starting From Leaf](https://leetcode.com/problems/smallest-string-starting-from-leaf/description/)
 *
 * Extension of [BinaryTreePaths]
 */

class SmallestStringStartingFromLeaf {

    fun smallestFromLeaf(root: TreeNode?): String {
        var smallestString = ""
        if (root == null) return smallestString

        val currentPath = StringBuilder()

        fun dfs(node: TreeNode) {
            // Convert node integer value (0..25) to character ('a'..'z')
            val char = 'a' + node.`val`
            currentPath.append(char)

            // If we reach a leaf node, evaluate the leaf-to-root string
            if (node.left == null && node.right == null) {
                val leafToRootString = currentPath.reverse().toString()
                currentPath.reverse()

                // Update smallest string if it's the first one found or lexicographically smaller
                if (smallestString.isEmpty() || leafToRootString < smallestString) {
                    smallestString = leafToRootString
                }
            } else {
                // Continue exploring left and right subtrees
                node.left?.let { dfs(it) }
                node.right?.let { dfs(it) }
            }
            // Backtrack: remove the current node's character before returning to parent
            currentPath.deleteCharAt(currentPath.length - 1)
        }
        dfs(root)
        return smallestString
    }

}