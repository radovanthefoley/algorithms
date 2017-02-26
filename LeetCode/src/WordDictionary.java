import java.util.HashMap;

public class WordDictionary {

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode actualNode = root;
        for (int i = 0; i < word.length(); i++) {
            actualNode = actualNode.addChild(word.charAt(i));
        }
        actualNode.setIsEndOfWord(true);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        SettableBoolean found = new SettableBoolean();
        searchChild(root, word, 0, word.length(), found);
        return found.getBool();
    }

    private void searchChild(TrieNode actualNode, String word, int i,
            int length, SettableBoolean found) {
        if (actualNode == null)
            return;
        if (i < length) {
            char z = word.charAt(i);
            if (z == '.') {
                HashMap<Character, TrieNode> nodes = actualNode
                        .returnAllChildren();
                for (Character ch : nodes.keySet())
                        searchChild(nodes.get(ch), word, i + 1, length, found);
            } else
                searchChild(actualNode.returnChild(z), word, i + 1, length,
                        found);
        }
        if (i < length)
            return;
        if (actualNode.isEndOfWord())
            found.setBool(true);
    }

    private class TrieNode {
        // Initialize your data structure here.
        private HashMap<Character, TrieNode> children = new HashMap<>();
        private boolean isEndOfWord;

        public TrieNode() {
            this.isEndOfWord = false;
        }

        public TrieNode addChild(Character ch) {
            if (hasChild(ch))
                return returnChild(ch);
            TrieNode child = new TrieNode();
            children.put(ch, child);
            return child;
        }

        public boolean hasChild(Character ch) {
            return children.containsKey(ch);
        }

        public TrieNode returnChild(Character ch) {
            return children.get(ch);
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public void setIsEndOfWord(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
        }

        public HashMap<Character, TrieNode> returnAllChildren() {
            return children;
        }
    }

    private class SettableBoolean {
        boolean bool = false;

        public void setBool(boolean bool) {
            this.bool = bool;
        }

        public boolean getBool() {
            return bool;
        }
    }

    public static void main(String... args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("word");
        System.out.println(wordDictionary.search(".o..."));
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
