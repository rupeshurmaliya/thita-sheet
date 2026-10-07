package com.thitasheet.tree


/**
 * Time complexity:
 *
 *
 * Space complexity:
 *
 * [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/description/)
 */

class BinaryTreeLevelOrderTraversal {

    fun levelOrder(root: TreeNode): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()

        val queue = ArrayDeque<TreeNode>()
        queue.add(root)

        while (!queue.isEmpty()) {
            val levelSize = queue.size
            //Pre size the array list to avoid repeated resizing
            val currentList = ArrayList<Int>(levelSize)

            repeat(levelSize) {
                val currentNode = queue.removeFirst()
                currentNode.left?.let { queue.add(it) }
                currentNode.right?.let { queue.add(it) }
                currentList.add(currentNode.`val`)
            }

            result.add(currentList)
        }
        return result
    }
}