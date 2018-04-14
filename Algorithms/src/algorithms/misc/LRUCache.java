package algorithms.misc;

/**
 * Created by abhishektiwari on 14/04/18.
 * LRU Cache implementation that supports get/set methods
 * The implementation uses Map and DLL to keep track of least and most
 * frequently used elements.
 * In case of distributed LRU cache we could use concurrent hashmap where
 * there is no exclusive lock on the whole map in case of write ops.
 *
 * Refs:
 * https://www.geeksforgeeks.org/lru-cache-implementation/
 */
import java.util.*;

class LinkedListNode {
    int key;
    int value;
    LinkedListNode prev, next;

    public LinkedListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LinkedList {

    private final int capacity;
    private LinkedListNode head;
    private LinkedListNode tail;
    private int size;
    private final Map<Integer, LinkedListNode> kNodeMap;

    public LinkedList(int capacity) {
        size = 0;
        this.capacity = capacity;
        head = tail = null;
        kNodeMap = new HashMap<Integer, LinkedListNode>();
    }

    private LinkedListNode insert(int key, int value) {
        // insertion from head
        if (head == null) {
            head = new LinkedListNode(key, value);
            tail = head;
        } else {
            LinkedListNode newHead = new LinkedListNode(key, value);
            newHead.next = head;
            newHead.prev = null;
            head.prev = newHead;
            head = newHead;
        }
        if (size + 1 > capacity) {
            if(kNodeMap.containsKey(tail.key)) {
                kNodeMap.remove(tail.key);
            }
            remove(tail);
        }
        size++;
        return head;
    }

    private LinkedListNode insert(LinkedListNode node) {
        node.next = head;
        head.prev = node;
        head = node;
        size++;
        return head;
    }

    private LinkedListNode remove(LinkedListNode node) {
        if (node == head) {
            head = head.next;
            if (head != null)
                head.prev = null;
            node.next = null;
            return node;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
            if (node == tail)
                tail = node.prev;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        node.prev = null;
        node.next = null;
        size--;
        return node;
    }

    private LinkedListNode moveToHead(LinkedListNode node) {
        if (node == head)
            return node;
        remove(node);
        return insert(node);
    }

    private LinkedListNode update(LinkedListNode node, int v) {
        node.value = v;
        return moveToHead(node);
    }

    public int get(Integer k) {
        return kNodeMap.containsKey(k) ? kNodeMap
                .get(moveToHead(kNodeMap.get(k)).key).value : -1;
    }

    public void set(Integer k, Integer v) {
        if (!kNodeMap.containsKey(k)) {
            kNodeMap.put(k, insert(k, v));
        } else {
            update(kNodeMap.get(k), v);
        }
    }
}

public class LRUCache {

    LinkedList ll;

    public LRUCache(int capacity) {
        ll = new LinkedList(capacity);
    }

    public int get(int key) {
        return ll.get(key);
    }

    public void set(int key, int value) {
        ll.set(key, value);
    }
    public static void main(String args[]) {
        LRUCache cache = new LRUCache(2);
        cache.set(2,1);
        cache.set(1,1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.set(2,3);
        cache.set(4,1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(4));
        cache.set(3,9);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        cache.set(5,19);
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
    }
}

