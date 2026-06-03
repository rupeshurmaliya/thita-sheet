/**
 * Created by Rupesh Urmaliya on 20/11/25
 * <p>
 * Que: <a href="Enter link">Enter title</a>
 */


public class LRUCache {

    // --- 1. Custom DLL and HashMap Implementation (O(1) time complexity) ---

    public static class CustomDLLLRUCache {

        // Inner Node class for the Doubly Linked List
        class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        // Maps the key to its corresponding Node in the DLL for O(1) lookup.
        private final java.util.HashMap<Integer, Node> cache;

        // Sentinel nodes for the Doubly Linked List
        private final Node head; // Head.next is MRU (Most Recently Used)
        private final Node tail; // Tail.prev is LRU (Least Recently Used)

        /**
         * Initializes the LRU Cache with a given capacity.
         */
        public CustomDLLLRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new java.util.HashMap<>();

            // Initialize the sentinel nodes (dummy nodes)
            this.head = new Node(0, 0); // Key and value don't matter for sentinels
            this.tail = new Node(0, 0);

            // Link head and tail initially
            head.next = tail;
            tail.prev = head;
        }

        /**
         * Gets the value from the cache. If found, moves the node to the head (MRU).
         * Time Complexity: O(1)
         */
        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }

            Node node = cache.get(key);

            // Move the accessed node to the front (MRU)
            removeNode(node);
            addNode(node);

            return node.value;
        }

        /**
         * Puts a key-value pair into the cache.
         * Time Complexity: O(1)
         */
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                // Case 1: Key exists. Update value and move to MRU.
                Node node = cache.get(key);
                node.value = value;
                removeNode(node);
                addNode(node);
            } else {
                // Case 2: Key does not exist.

                // Check capacity
                if (cache.size() == capacity) {
                    // Evict the LRU node (node just before the tail sentinel)
                    Node lruNode = tail.prev;
                    removeNode(lruNode);
                    cache.remove(lruNode.key);
                }

                // Create new node and add it to MRU (head)
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addNode(newNode);
            }
        }

        // --- Doubly Linked List Helper Methods ---

        /**
         * Inserts a node right after the head sentinel (making it MRU).
         */
        private void addNode(Node node) {
            // Node: |head| <-> |newNode| <-> |originalMRU| <-> ...

            // 1. Link newNode to what was the MRU (head.next)
            node.next = head.next;
            node.prev = head;

            // 2. Link head.next (original MRU) back to newNode
            head.next.prev = node;

            // 3. Link head to newNode
            head.next = node;
        }

        /**
         * Removes a given node from the doubly linked list.
         */
        private void removeNode(Node node) {
            // Disconnect the node by linking its neighbors together

            // Get the node before and after the one to be removed
            Node prevNode = node.prev;
            Node nextNode = node.next;

            // Link the previous node to the next node
            prevNode.next = nextNode;

            // Link the next node back to the previous node
            nextNode.prev = prevNode;

            // Optionally, clear the removed node's pointers (garbage collection aid)
            node.prev = null;
            node.next = null;
        }
    }


    // --- 2. LinkedHashMap Implementation (Simpler, O(1) time complexity) ---

    /**
     * This implementation extends LinkedHashMap and overrides its removeEldestEntry
     * method to implement the LRU eviction policy automatically.
     * <p>
     * LinkedHashMap is naturally suited for LRU because it maintains insertion
     * order OR access order (set by its constructor). When access order is true,
     * a get() or put() operation moves the accessed/inserted entry to the end
     * of the list (MRU). The entry at the start of the list is always the LRU.
     */
    public static class LinkedHashMapLRUCache extends java.util.LinkedHashMap<Integer, Integer> {
        private final int capacity;

        /**
         * Initializes the LRU Cache with a given capacity.
         */
        public LinkedHashMapLRUCache(int capacity) {
            // true: Enables access-order mode. The order of iteration is the order in which entries were last accessed.
            // 0.75f: Default load factor.
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        /**
         * The standard way to implement LRU with LinkedHashMap.
         * This method is called after a new entry is inserted.
         * It returns true if the eldest (LRU) entry should be removed.
         */
        @Override
        protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
            // Remove the oldest entry if the current size exceeds the capacity
            return size() > capacity;
        }

        // The get(key) and put(key, value) methods are inherited and work automatically
        // because the constructor was called with accessOrder=true.

        // We override get to match the CustomDLLLRUCache's signature for returning -1
        // (LinkedHashMap's get returns null if the key is not found).
        public int get(int key) {
            Integer value = super.get(key);
            return value != null ? value : -1;
        }
    }

    // --- Example Usage (Demonstration) ---

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("1. Testing Custom DLL LRU Cache (Capacity 2)");
        System.out.println("=========================================");

        CustomDLLLRUCache customCache = new CustomDLLLRUCache(2);

        customCache.put(1, 10); // MRU: 1
        customCache.put(2, 20); // MRU: 2, LRU: 1
        System.out.println("Get(1): " + customCache.get(1)); // Output: 10. MRU: 1, LRU: 2
        customCache.put(3, 30); // Evicts 2. MRU: 3, LRU: 1
        System.out.println("Get(2): " + customCache.get(2)); // Output: -1
        System.out.println("Get(3): " + customCache.get(3)); // Output: 30. MRU: 3, LRU: 1

        System.out.println("\n=========================================");
        System.out.println("2. Testing LinkedHashMap LRU Cache (Capacity 2)");
        System.out.println("=========================================");

        LinkedHashMapLRUCache linkedCache = new LinkedHashMapLRUCache(2);

        linkedCache.put(1, 10); // MRU: 1
        linkedCache.put(2, 20); // MRU: 2, LRU: 1
        System.out.println("Get(1): " + linkedCache.get(1)); // Output: 10. MRU: 1, LRU: 2
        linkedCache.put(3, 30); // Evicts 2. MRU: 3, LRU: 1
        System.out.println("Get(2): " + linkedCache.get(2)); // Output: -1
        System.out.println("Get(3): " + linkedCache.get(3)); // Output: 30. MRU: 3, LRU: 1
    }
}
