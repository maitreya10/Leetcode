class WordDictionary {
    private Node root;
    public WordDictionary() {
        root = new Node('\0'); // dummy node.
    }
    
    public void addWord(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new Node(ch);
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isWord = true;
    }
    /**
     * TC: O(n^2)
     */
    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    private boolean searchHelper(String word, Node curr, int index) {
        for (int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == '.') { // Brute force by backtracking.
                for (Node tmp : curr.children) {
                    if (tmp != null && searchHelper(word, tmp, i + 1)) {
                        return true;
                    }
                }
                return false;
            }
            if (curr.children[ch - 'a'] == null) {
                return false;
            }
            curr = curr.children[ch - 'a'];
        }
        return curr.isWord; // true only if current pointer is at "end of Word"
    }

    private class Node {
        char value;
        boolean isWord;
        Node[] children;

        public Node(char value) {
            this.value = value;
            isWord = false;
            children = new Node[26];
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
