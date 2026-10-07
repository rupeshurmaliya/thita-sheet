package com.thitasheet.tree


/**
 *
 *  Time Complexity: O(N), where N is the total number of nodes in the binary tree. We visit each node in the tree exactly once.
 *
 *  Space Complexity: O(H), where H is the height of the tree. This is the maximum depth of the recursive call stack.
 * * In a balanced tree, the space complexity is O(log N).
 * * In the worst case (a skewed tree), the space complexity is O(N).
 * * This is optimal because it avoids storing extra queue elements or intermediate level arrays.
 *
 *
 * [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/description/)
 */
class BinaryTreeRightSideView {

    fun rightSideView(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val result = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (!queue.isEmpty()) {
            val levelSize = queue.size
            repeat(levelSize) { index ->
                val node = queue.removeFirst()
                // The last element of the current level loop is the rightmost node
                if (index == levelSize - 1) {
                    result.add(node.`val`)
                }
                // Add left child then right child to queue
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
        }
        return result
    }

    /**
     *  Comparison of DFS vs BFS:
     *
     * * DFS (Recursive): Preferred for its simplicity and superior space complexity O(H) in balanced trees, as it only stores recursive stack frames.
     * * BFS (Iterative): Useful if the tree is extremely deep (preventing stack overflow), but has a higher average space complexity O(W) because it must keep entire levels in the
     *   queue.
     *
     */

    fun rightSideViewDFS(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        fun dfs(node: TreeNode?, level: Int) {
            if (node == null) return

            // If this is the first node we encounter at this depth, it is the rightmost node
            if (result.size == level) {
                result.add(node.`val`)
            }

            // Always traverse the right child first so rightmost nodes are visited first
            dfs(node.right, level + 1)
            dfs(node.left, level + 1)
        }

        dfs(root, 0)
        return result
    }
}