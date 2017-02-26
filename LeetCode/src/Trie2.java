// implementation using arrays

public class Trie2 {

    private TrieNode root;

    public Trie2() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode actualNode = root;
        for (int i = 0; i < word.length(); i++) {
            actualNode = actualNode.addChild(word.charAt(i));
        }
        actualNode.setIsEndOfWord(true);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return search(word, false);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return search(prefix, true);
    }

    private boolean search(String string, boolean isPrefix) {
        TrieNode actualNode = root;
        for (int i = 0; i < string.length(); i++) {
            actualNode = actualNode.returnChild(string.charAt(i));
            if (actualNode == null)
                return false;
        }
        return isPrefix ? true : actualNode.isEndOfWord();
    }

    private class TrieNode {
        // Initialize your data structure here.
        private TrieNode[] children = new TrieNode[26];
        private boolean isEndOfWord;

        public TrieNode() {
            this.isEndOfWord = false;
        }

        public TrieNode addChild(char ch) {
            if (hasChild(ch))
                return returnChild(ch);
            TrieNode child = new TrieNode();
            children[ch - 'a'] = child;
            return child;
        }

        public boolean hasChild(char ch) {
            return children[ch - 'a'] != null;
        }

        public TrieNode returnChild(char ch) {
            return children[ch - 'a'];
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public void setIsEndOfWord(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
        }
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
