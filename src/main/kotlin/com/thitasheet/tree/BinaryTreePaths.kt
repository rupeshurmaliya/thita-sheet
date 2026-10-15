package com.thitasheet.tree


/**
 *  `Time Complexity: O(N * H)`
 *  * Traversing every node takes O(N) time.
 *  * For each leaf reached (there are at most N / 2 leaves in a balanced tree), constructing the path string takes O(H) time, where H is the height of the tree.
 *  * In a balanced tree: O(N * log N).
 *  * In a skewed tree: O(N) (only 1 leaf of depth N).
 *  * Total Time Complexity: O(N * H).
 *
 *
 *   `Space Complexity: O(H) auxiliary space`
 *  * The currentPath buffer stores at most H integers.
 *  * The recursion call stack uses O(H) memory frames.
 *  * H = O(log N) for balanced trees, and H = O(N) in the worst case (skewed tree).
 *  * (Excluding output list storage).
 *
 *
 * [257. Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/)
 */
class BinaryTreePaths {

    fun binaryTreePaths(root: TreeNode?): List<String> {
        val result = mutableListOf<String>()
        if (root == null) return result

        val currentPath = mutableListOf<Int>()

        fun findPaths(node: TreeNode) {
            // Choose: add current node's value to the active path
            currentPath.add(node.`val`)

            // Leaf condition: both left and right children are null
            if (node.left == null && node.right == null) {
                // Convert current path buffer into the required format: "1->2->5"
                result.add(currentPath.joinToString("->"))
            } else {
                // Explore child subtrees
                node.left?.let { left -> findPaths(left) }
                node.right?.let { right -> findPaths(right) }
            }
            // Unchoose (Backtrack): remove current node before returning to parent
            currentPath.removeAt(currentPath.size - 1)
        }

        findPaths(root)
        return result
    }

    fun binaryTreePathsBFS(root: TreeNode?): List<String> {
        val result = mutableListOf<String>()
        if (root == null) return result

        // Queue storing node and its path string up to that node
        val nodeQueue = ArrayDeque<TreeNode>()
        val pathQueue = ArrayDeque<String>()

        nodeQueue.addLast(root)
        pathQueue.addLast("${root.`val`}")

        while (nodeQueue.isNotEmpty()) {
            val currentNode = nodeQueue.removeFirst()
            val currentPath = pathQueue.removeFirst()

            // If current node is a leaf, add path to result
            if (currentNode.left == null && currentNode.right == null) {
                result.add(currentPath)
            }

            // Enqueue left child with updated path
            if (currentNode.left != null) {
                nodeQueue.addLast(currentNode.left!!)
                pathQueue.addLast("$currentPath->${currentNode.left!!.`val`}")
            }

            // Enqueue right child with updated path
            if (currentNode.right != null) {
                nodeQueue.addLast(currentNode.right!!)
                pathQueue.addLast("$currentPath->${currentNode.right!!.`val`}")
            }
        }

        return result
    }
}
}