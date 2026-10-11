package com.thitasheet.tree


/**
 * Time Complexity: O(n)
 * * Building the HashMap takes O(n).
 * * We visit every node exactly once to create it. Finding the split point in the HashMap is O(1).
 * * Total: O(n).
 *
 * Space Complexity: O(n)
 * * We store all values in the HashMap, which is O(n).
 * * The recursion stack takes O(h) (where h is the height), but the map dominates the space.
 *
 * [105. Construct Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/?envType=problem-list-v2&envId=wj7n672g)
 *
 * [Youtube solution](https://www.youtube.com/watch?v=PbPS460rbMo)
 *
 * The Golden Rule for Traversals
 * * Preorder + Inorder: Process Preorder 0 -> N, build Left then Right.
 * * Postorder + Inorder: Process Postorder N -> 0, build Right then Left.
 *
 */
class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        // Start from the last element of postorder
        var preorderIndex = 0

        // Step 1: Map values to indices for O(1) lookup
        val inorderMap = mutableMapOf<Int, Int>()
        inorder.forEachIndexed { index, value -> inorderMap[value] = index }

        fun buildSubtree(left: Int, right: Int): TreeNode? {
            // Base case: if there are no elements to construct the tree
            if (left > right) return null

            // Step 2: The first element in preorder is the root
            val rootValue = preorder[preorderIndex++]
            val root = TreeNode(rootValue)

            // Step 3: Find where this root splits the Inorder list
            val splitIndex = inorderMap[rootValue] ?: return null

            // Step 4: Recursively build the left and right subtrees
            // IMPORTANT: Must build Left before Right because of how preorderIndex moves
            root.left = buildSubtree(left, splitIndex - 1)
            root.right = buildSubtree(splitIndex, right)

            return root
        }

        return buildSubtree(0, inorder.size - 1)
    }
}