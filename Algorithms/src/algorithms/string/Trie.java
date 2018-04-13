package algorithms.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 04/13/2018
 * @author tiwariabhishek
 *
 * Insert/delete/search into trie data structure
 *
 * Reference
 * https://en.wikipedia.org/wiki/Trie
 */
public class Trie {

    private final TrieNode root;

    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (root == null || word == null || word.length() == 0) return;
        insert(word.toCharArray());
    }

    private void insert(char[] word) {
        TrieNode current = root;
        for (int i = 0; i < word.length; i++) {
            if (!current.children.containsKey(word[i])) {
                TrieNode node = new TrieNode();
                current.children.put(word[i], node);
            }
            current = current.children.get(word[i]);
        }
        current.endOfWord = true;
    }

    public boolean search(String word) {
        if (root == null || word == null || word.length() == 0) return false;
        return search(word.toCharArray());
    }

    private boolean search(char[] word) {
        TrieNode current = root;
        for (int i = 0; i < word.length; i++) {
            if (!current.children.containsKey(word[i])) {
                return false;
            }
            current = current.children.get(word[i]);
        }
        return current.endOfWord;
    }

    public void delete(String word) {
        if (root == null || word == null || word.length() == 0) return;
        delete(word.toCharArray(), 0, root);
    }

    private boolean delete(char[] word, int index, TrieNode current) {
        if (index == word.length) {
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            return current.children.size() == 0;
        }
        if (!current.children.containsKey(word[index])) {
            return false;
        }
        current = current.children.get(word[index]);
        boolean shouldDeleteThisNode = delete(word, index + 1, current);
        if (shouldDeleteThisNode) {
            current.children.remove(word[index]);
            return current.children.size() == 0;
        }
        return false;
    }

    public static void main(String args[]) {
        Trie trie = new Trie();
        trie.insert("the");
        trie.insert("their");
        trie.insert("then");
        trie.insert("there");
        trie.insert("that");
        System.out.println(trie.search("the"));
        System.out.println(trie.search("their"));
        System.out.println(trie.search("then"));
        trie.delete("the");
        System.out.println(trie.search("the"));
        System.out.println(trie.search("their"));
        System.out.println(trie.search("then"));
        System.out.println(trie.search("that"));
    }
}