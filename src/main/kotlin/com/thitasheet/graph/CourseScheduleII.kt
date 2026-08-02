package com.thitasheet.graph


/**
 *    Time Complexity:  O(V + E)  where  V  is the number of courses (numCourses) and  E  is the number of prerequisite edges ( prerequisites.size ).
 * * Building the graph takes  O(E)  time.
 * * In the BFS phase, we process each node exactly once ( O(V) ) and visit each directed edge exactly once ( O(E) ).
 * * Hence, the overall time complexity is linear relative to the size of the graph.
 *
 *
 *      Space Complexity:  O(V + E)
 * * Storing the adjacency list requires  O(V + E)  space.
 * * Storing the  inDegrees  array takes  O(V)  space.
 * * The  queue  can store at most  V  elements, taking  O(V)  space.
 * * Thus, the total auxiliary space complexity is  O(V + E) .
 *
 *
 * [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/description/)
 *
 * [YouTube Solution](https://www.youtube.com/watch?v=Oa4Srx9mDqs)
 */
class CourseScheduleII {

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        // Step 1: Represent the graph using an adjacency list and track in-degrees
        val adjList = Array(numCourses) { mutableListOf<Int>() }
        val inDegrees = IntArray(numCourses)

        for (prereq in prerequisites) {
            val course = prereq[0]
            val prerequisite = prereq[1]
            adjList[prerequisite].add(course)
            inDegrees[course]++
        }

        // Step 2: Initialize queue with courses having 0 prerequisites
        val queue = ArrayDeque<Int>()
        for (course in 0 until numCourses) {
            if (inDegrees[course] == 0) {
                queue.addLast(course)
            }
        }

        // CHANGE 1: Create an array to store our topological order
        val topoOrder = IntArray(numCourses)
        var index = 0

        // Step 3: Process the queue
        while (queue.isNotEmpty()) {
            val currentCourse = queue.removeFirst()

            // CHANGE 2: Add the completed course to our topological path
            topoOrder[index++] = currentCourse

            for (neighbor in adjList[currentCourse]) {
                inDegrees[neighbor]--
                if (inDegrees[neighbor] == 0) {
                    queue.addLast(neighbor)
                }
            }
        }

        // CHANGE 3: If we processed all courses, return the order; otherwise return empty array
        return if (index == numCourses) topoOrder else intArrayOf()
    }
}