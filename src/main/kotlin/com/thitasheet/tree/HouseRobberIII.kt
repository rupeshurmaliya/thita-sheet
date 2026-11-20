package com.thitasheet.tree


/**
 * **• Time Complexity: O(N)**
 *
 *       • Each node in the binary tree is visited exactly once in postorder DFS.
 *       • At each node, computing robCurrent and skipCurrent takes O(1) arithmetic operations.
 *       • Total time is strictly linear in terms of total nodes N.
 *
 * **• Space Complexity: O(H)**
 *
 *       • H is the height of the binary tree.
 *       • In the worst case (a completely skewed tree), H = N, requiring O(N) call stack space.
 *       • In the best/average case (a balanced binary tree), H = log N, requiring O(log N) call stack space.
 * [337. House Robber III](https://leetcode.com/problems/house-robber-iii/description/)
 */

class HouseRobberIII {

    // Clean data holder to avoid confusing magic indices (e.g. array[0] vs array[1])
    private data class RobResult(
        val rob: Int, val notRob: Int
    )

    fun rob(root: TreeNode?): Int {
        val finalResult = dfs(root)
        // Best profit from root is either robbing it or skipping it
        return maxOf(finalResult.rob, finalResult.notRob)
    }

    private fun dfs(node: TreeNode?): RobResult {
        // Base case: null house provides zero profit
        if (node == null) {
            return RobResult(rob = 0, notRob = 0)
        }

        // Bottom-up: resolve subtrees first
        val left = dfs(node.left)
        val right = dfs(node.right)

        // 1. If we rob this node, we cannot rob its children
        val robCurrent = node.`val` + left.notRob + right.notRob

        // 2. If we skip this node, children can independently be robbed or skipped
        val skipCurrent = maxOf(left.rob, left.notRob) + maxOf(right.rob, right.notRob)

        return RobResult(rob = robCurrent, notRob = skipCurrent)
    }

    fun robBruteForce(root: TreeNode?): Int {
        if (root == null) return 0

        // Choice 1: Rob the current root + grandchildren
        var robRoot = root.`val`
        if (root.left != null) {
            robRoot += robBruteForce(root.left?.left) + robBruteForce(root.left?.right)
        }
        if (root.right != null) {
            robRoot += robBruteForce(root.right?.left) + robBruteForce(root.right?.right)
        }

        // Choice 2: Do not rob the current root, rob direct children
        val skipRoot = robBruteForce(root.left) + robBruteForce(root.right)

        return maxOf(robRoot, skipRoot)
    }
}