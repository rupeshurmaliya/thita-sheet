package com.thitasheet.tree


/**
 *
 * Time Complexity: O(n)
 * * In a binary tree with n nodes, the algorithm performs a constant amount of work at each node (checking if it's null, doing one addition, and one maxOf comparison).
 * * Since we visit n nodes, the time is linear: O(n).
 *
 * Space Complexity: O(h)
 * * This is the one that usually trips people up. Even though we aren't creating new arrays or lists, the system recursion stack takes up memory.
 * * Best Case (Balanced Tree): O(log n)
 * If the tree is perfectly balanced (like a pyramid), the height h is log2(n)For 1,000 nodes, the stack only goes ~10 levels deep.
 * * Worst Case (Skewed Tree): O(n)
 * If the tree is basically a linked list (each node only has one child), the height h is equal to n. For 1,000 nodes, the stack goes 1,000 levels deep.
 *
 * [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?envType=problem-list-v2&envId=wj7n672g)
 *
 *
 *
 *
 *
 *          1 (Root)
 *          /
 *         2
 *        / \
 *       4   5
 *      /     \
 *     6       7
 *
 *
 *
 * 1. Max Depth (Height)
 * We count the longest path from the Root (1) to a leaf.
 *
 * Path: 1 -> 2 -> 4 -> 6 (Length: 4)
 *
 * Result: 4
 *
 *
 * 2. Diameter
 * We look for the longest path anywhere. Notice the path from 6 to 7:
 *
 * Path: 6 -> 4 -> 2 -> 5 -> 7
 *
 * Count the edges: 1, 2, 3, 4. (Nodes: 5)
 *
 * Result: 4 edges (or 5 nodes)
 *
 * In this case, the Diameter stayed entirely within the left subtree! It didn't even use the Root (Node 1).
 *
 * **Hint: Pattern 15: Tree DFS - Recursive Postorder Traversal**
 */

class MaximumDepthOfBinaryTree {

    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val left = maxDepth(root.left)
        val right = maxDepth(root.right)

        return maxOf(left, right) + 1
    }
}