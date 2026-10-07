package com.thitasheet.tree


/**
 * Time Complexity: O(n)
 * * n is the number of nodes.
 * * We visit every node once.
 * * Using addFirst() on a LinkedList is O(1), so building each level is very efficient.
 *
 * Space Complexity: O(w)
 * * w is the maximum width of the tree.
 * * The queue stores at most one level of nodes at a time.
 * * In a balanced tree, the widest part is about n/2 nodes.
 *
 *
 * [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/?envType=problem-list-v2&envId=wj7n672g)
 */
class BinaryTreeZigzagLevelOrderTraversal {

    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result

        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        var leftToRight = true

        while (!queue.isEmpty()) {
            val levelSize = queue.size
            // We use a ArrayDeque for the current level because it's
            // efficient to add items to both the front and the back
            val currentList = ArrayDeque<Int>(levelSize)

            repeat(levelSize) {
                val node = queue.removeFirst()

                if (leftToRight) {
                    currentList.addLast(node.`val`)
                } else {
                    // Reversed order: add to the front
                    // Alternatively, we can use an ArrayList and, based on the leftToRight condition,
                    // reverse it right before flipping the direction to avoid using addFirst().
                    currentList.addFirst(node.`val`)
                }

                node.left?.let(queue::add)
                node.right?.let(queue::add)
            }
            result.add(currentList)
            // Flip the direction for the next level
            leftToRight = !leftToRight
        }

        return result
    }

}