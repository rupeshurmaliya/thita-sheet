package com.thitasheet.tree


/**
 *
 * Time Complexity: O(n)
 * * Why? In the worst-case scenario (where the trees are identical or only differ at the very last leaf), the function must visit every single node at least once.
 * * n represents the number of nodes in the smaller of the two trees. As soon as we hit a mismatch or a null, the function returns.
 * * Operations: At each node, we perform a constant amount of work—just a few null checks and one value comparison.
 *
 * Space Complexity: O(h)
 * * Why? This is determined by the Recursion Stack. Every time the function calls itself (isSameTree), the computer "saves" the current state in memory until that call returns.
 * * h represents the height of the tree.
 * * Best Case (Balanced Tree): The complexity is O(log n) because the tree is short and bushy.
 * * Worst Case (Skewed Tree): If the tree is just one long line of nodes (like a Linked List), the complexity is O(n) because the stack will be as deep as the number of nodes.
 *
 *
 * [100. Same Tree](https://leetcode.com/problems/same-tree/?envType=problem-list-v2&envId=wj7n672g)
 *
 * Same as Tree identical logic except catch is below line:
 *
 * isSymmetric(node1.left, node2.right) && isTreeIdentical(node1.right, node2.left);
 *
 */
class SameTree {

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return when {
            // Both nodes are null: structural match at this branch
            p == null && q == null -> true

            // Only one node is null: structural mismatch
            p == null || q == null -> false

            // Values of the current nodes differ: value mismatch
            p.`val` != q.`val` -> false

            // Recursively verify left and right subtrees
            else -> isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }
    }
}