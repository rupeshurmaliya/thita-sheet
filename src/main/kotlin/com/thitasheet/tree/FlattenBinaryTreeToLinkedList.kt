package com.thitasheet.tree


/**
 *
 * Time Complexity: O(n)
 * * The algorithm performs a constant amount of work (O(1)) at each node: assigning two pointers (root.right = prev and root.left = null) and updating the global prev pointer.
 * * Since every node is visited exactly once, the total time grows linearly with the number of nodes n.
 *
 * Space Complexity: O(h)
 * * Even though we change the tree pointers in-place and don't allocate new structures (like arrays or lists), the system recursion stack takes up memory:
 * * Worst-Case (Skewed Tree): O(n)
 * * If the tree is a straight line (essentially a linked list already), the height h equals n. The recursion stack will go n levels deep before it starts returning, consuming O(n) memory.
 * * Best-Case (Balanced Tree): O(log n)
 * * If the tree is completely balanced, the height h is bounded by log2(n), meaning the call stack stays very shallow.
 *
 *
 * [114. Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/?envType=problem-list-v2&envId=wj7n672g)
 *
 */

class FlattenBinaryTreeToLinkedList {

    // This global pointer keeps track of the node we processed
    // IMMEDIATELY before the current one (the "previous" node in reverse order).
    private var prev: TreeNode? = null

    fun flatten(root: TreeNode?) {
        if (root == null) return

        /*
         * STEP 1: Traverse the RIGHT subtree first.
         * We go all the way to the right-most bottom node of the entire tree.
         * This node will become the very end (tail) of our final flattened list.
         */
        flatten(root.right)

        /*
         * STEP 2: Traverse the LEFT subtree.
         * Because we are moving backwards, after finishing a right branch,
         * we jump to its corresponding left sibling branch.
         */
        flatten(root.left)

        /*
         * STEP 3: Process the current Root (The Magic Step).
         *
         * At this moment, 'prev' is pointing to the node that should come
         * DIRECTLY AFTER 'root' in a standard pre-order traversal.
         *
         * We wire up the current node like a linked list node:
         * 1. Point its right child to 'prev'.
         * 2. Clear its left child to null (as required by the problem).
         */
        root.right = prev
        root.left = null

        /*
         * STEP 4: Move the 'prev' pointer to the current node.
         * This signals to the next recursive call (which will be this node's parent)
         * that 'this' current node is now the head of the processed sub-list.
         */
        prev = root
    }

    /**
     *  Dry Run
     *
     *   Let's trace the execution step-by-step with a sample binary tree:
     *
     *           1
     *          / \
     *         2   5
     *        / \   \
     *       3   4   6
     *
     *   Standard pre-order traversal:  1 → 2 → 3 → 4 → 5 → 6 .
     *   Our reverse order traversal (Right → Left → Root) will visit nodes in this exact sequence:  6 → 5 → 4 → 3 → 2 → 1 .
     *
     *   • Initial state:  prev = null .
     *
     *   1.  flatten(1)
     *       • Calls  flatten(1.right)  →  flatten(5)
     *   2.  flatten(5)
     *       • Calls  flatten(5.right)  →  flatten(6)
     *   3.  flatten(6)
     *       • Calls  flatten(6.right)  →  flatten(null)  → returns.
     *       • Calls  flatten(6.left)  →  flatten(null)  → returns.
     *       • Process Node 6:
     *           •  6.right = prev  (since  prev  is  null ,  6.right = null ).
     *           •  6.left = null .
     *           • Update  prev = 6 .
     *           • Returns back to  flatten(5) .
     *
     *   4. Back in  flatten(5)
     *       • Calls  flatten(5.left)  →  flatten(null)  → returns.
     *       • Process Node 5:
     *           •  5.right = prev  (since  prev  is Node 6,  5.right = 6 ).
     *           •  5.left = null .
     *           • Update  prev = 5 .
     *           • Current state:  5 → 6 .
     *           • Returns back to  flatten(1) .
     *
     *   5. Back in  flatten(1)
     *       • Calls  flatten(1.left)  →  flatten(2)
     *   6.  flatten(2)
     *       • Calls  flatten(2.right)  →  flatten(4)
     *   7.  flatten(4)
     *       • Children of 4 are null, recursion returns.
     *       • Process Node 4:
     *           •  4.right = prev  (since  prev  is Node 5,  4.right = 5 ).
     *           •  4.left = null .
     *           • Update  prev = 4 .
     *           • Current state:  4 → 5 → 6 .
     *           • Returns back to  flatten(2) .
     *
     *   8. Back in  flatten(2)
     *       • Calls  flatten(2.left)  →  flatten(3)
     *   9.  flatten(3)
     *       • Children of 3 are null, recursion returns.
     *       • Process Node 3:
     *           •  3.right = prev  (since  prev  is Node 4,  3.right = 4 ).
     *           •  3.left = null .
     *           • Update  prev = 3 .
     *           • Current state:  3 → 4 → 5 → 6 .
     *           • Returns back to  flatten(2) .
     *
     *   10. Back in  flatten(2)
     *       • Process Node 2:
     *           •  2.right = prev  (since  prev  is Node 3,  2.right = 3 ).
     *           •  2.left = null .
     *           • Update  prev = 2 .
     *           • Current state:  2 → 3 → 4 → 5 → 6 .
     *           • Returns back to  flatten(1) .
     *
     *   11. Back in  flatten(1)
     *       • Process Node 1:
     *           •  1.right = prev  (since  prev  is Node 2,  1.right = 2 ).
     *           •  1.left = null .
     *           • Update  prev = 1 .
     *           • Final state:  1 → 2 → 3 → 4 → 5 → 6 .
     *           • Execution ends.
     *
     */

    fun flattenIterative(root: TreeNode?): Unit {
        var current = root
        while (current != null) {
            // If current node has a left subtree, it needs to be spliced into the right
            if (current.left != null) {
                // Find the rightmost node in the left subtree
                var rightMost = current.left!!
                while (rightMost.right != null) {
                    rightMost = rightMost.right!!
                }

                // Connect the rightmost node of left subtree to current's right child
                rightMost.right = current.right

                // Move the left subtree to current's right child
                current.right = current.left
                current.left = null
            }

            // Move to the next node on the right spine
            current = current.right
        }
    }
}