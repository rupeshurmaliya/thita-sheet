package com.thitasheet.tree


/**
 *
 * Time Complexity: O(N)
 * * We visit every node in the tree exactly once.
 *
 * Space Complexity: O(H)
 * * This is the space used by the recursion stack. H is the height of the tree.
 * * In the worst case (skewed tree), O(N).
 * * In the best case (balanced tree), O(log N).
 *
 * [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/description/?envType=problem-list-v2&envId=wj7n672g)
 *
 * **Hint: Pattern 15: Tree DFS - Recursive Postorder Traversal**
 */

class BinaryTreePostorderTraversal {

    fun postorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()

        fun postorder(node: TreeNode?) {
            // Base case: If the node is null, there's nothing to do
            if (node == null) return

            // 2. Traverse Left subtree
            postorder(node.left)
            // 2. Traverse Right subtree
            postorder(node.right)
            // 1. Visit Root: Add the current node's value to the list first
            result.add(node.`val`)
        }

        postorder(root)
        return result
    }

    /**
     * Instead of nesting an inner while loop inside an outer while loop, this version treats the loop as a clean 3-state transition system that advances by one atomic step per
     * iteration:
     *
     * ```text
     *                     ┌──────────────────────┐
     *                     │   curr != null ?     │
     *                     └──────────┬───────────┘
     *                          YES   │   NO
     *                  ┌─────────────┘   └─────────────┐
     *                  ▼                               ▼
     *          [State 1: Dive Left]          [Inspect stack.last()]
     *          - Push curr to stack                    │
     *          - curr = curr.left                      ▼
     *                                        ┌──────────────────┐
     *                                        │ Unvisited right? │
     *                                        └────┬─────────┬───┘
     *                                         YES │         │ NO
     *                            ┌────────────────┘         └────────────────┐
     *                            ▼                                           ▼
     *                  [State 2: Pivot Right]                      [State 3: Process & Pop]
     *                  - curr = peekNode.right                     - result.add(val)
     *                                                              - lastVisited = pop()
     *                                                              - curr remains null!
     * ```
     */

    fun postorderTraversalIterative(root: TreeNode?): List<Int> {
        val stack = ArrayDeque<TreeNode>()
        var current = root
        var lastVisited: TreeNode? = null
        val result = mutableListOf<Int>()

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                // 1. Dive Left
                stack.addLast(current) // Using standard ArrayDeque push equivalent
                current = current.left
            } else {
                val peekNode = stack.last() // Using standard ArrayDeque peek equivalent

                // 2. If right child exists and we haven't come back from it yet...
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    // ...move to the right child
                    current = peekNode.right
                } else {
                    // 3. Otherwise, we are done with subtrees. Visit the Node.
                    result.add(peekNode.`val`)
                    lastVisited = stack.removeLast() // Using standard ArrayDeque pop equivalent
                }
            }
        }
        return result
    }

    fun postorderTraversalIterativeNestedWhile(root: TreeNode?): List<Int> {
        val result = ArrayList<Int>()
        val stack = ArrayDeque<TreeNode>()
        var current = root
        var lastVisited: TreeNode? = null

        while (current != null || !stack.isEmpty()) {
            // Step 1: Reach the leftmost node of the current subtree
            while (current != null) {
                stack.addLast(current)
                current = current.left
            }
            // Step 2: Inspect the top node without removing it
            val peekNode = stack.last()
            // Step 3: Check if we need to explore the right subtree first
            if (peekNode.right != null && lastVisited != peekNode.right) {
                current = peekNode.right
            } else {
                // Step 4: Both subtrees are finished; process this node
                result.add(peekNode.`val`)
                lastVisited = stack.removeLast()
            }
        }
        return result
    }
}