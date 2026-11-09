package com.thitasheet.tree


/**
 *      Time Complexity: O(H + k)
 * * H is the height of the tree. We must first traverse down to the leftmost leaf, which takes O(H) time.
 * * Then we process k elements, which takes O(k) time.
 * * In a balanced tree, H = log(N), so Time is O(log N + k).
 * * In the worst case (skewed tree), H = N, so Time is O(N).
 *
 *
 *      Space Complexity: O(H)
 * * The stack stores at most H elements at any given time (one path from root to leaf). In a balanced tree, this is O(log N). The worst case is O(N).
 *
 * [230. Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/?envType=problem-list-v2&envId=wj7n672g)
 */

class KthSmallestElementInaBST {

    fun kthSmallest(root: TreeNode?, k: Int): Int {
        var count = 0
        var current = root
        val stack = ArrayDeque<TreeNode>()

        // Loop as long as there are nodes to traverse or elements in the stack
        while (current != null || !stack.isEmpty()) {
            // Traverse to the leftmost node of the current subtree
            while (current != null) {
                stack.addLast(current)
                current = current.left
            }
            // Process the top node (the smallest unvisited node
            val node = stack.removeLast()
            count++
            // If we've processed k nodes, return the value
            if (count == k) {
                return node.`val`
            }
            // Move to the right subtree of the processed node
            current = node.right
        }
        // Fallback for cases where k is out of bounds (per constraints, this won't happen)
        return -1
    }
}