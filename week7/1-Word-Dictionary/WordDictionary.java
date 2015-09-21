import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class WordDictionary {
    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
        MyScanner scanner = new MyScanner();
        Trie trie = new Trie();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String command = scanner.nextString();
            String word = scanner.nextString();
            if (command.equals("insert")) {
                trie.insert(word);
            }
            else {
                writer.println(trie.contains(word));
            }
        }
        writer.close();
    }
}

class Node {
    public char symbol;
    public boolean isEnd = false;
    public HashMap<Character, Node> children = new HashMap<>();

    public Node() {}

    public Node(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object obj) {
        return Character.compare(symbol, ((Node)obj).symbol) == 0;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(symbol);
    }
}

class Trie {
    Node root = new Node();

    public Trie() {}

    public void insert(String word) {
        Node current = root;
        int index = 0;
        int max = word.length();
        while (index < max && current != null && current.children.containsKey(word.charAt(index))) {
            current = current.children.get(word.charAt(index));
            index++;
        }

        while (index < max) {
            Node newNode = new Node(word.charAt(index));
            if (index == max - 1) {
                newNode.isEnd = true;
            }
            current.children.put(word.charAt(index), newNode);
            current = newNode;
            index++;
        }
    }

    public boolean contains(String word) {
        Node current = root;
        int index = 0;
        int max = word.length();
        while (index < max && current != null && current.children.containsKey(word.charAt(index))) {
            current = current.children.get(word.charAt(index));
            index++;
        }

        index = Math.min(index, max - 1);

        if (Character.compare(current.symbol, word.charAt(index)) == 0 && current.isEnd) {
            return true;
        }
        return false;
    }
}


class MyScanner {
    StringTokenizer tokenizer = null;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }

        return tokenizer.nextToken();
    }

    String nextString() throws IOException {
        return nextToken();
    }
}