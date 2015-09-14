import java.io.*;
import java.util.*;

/**
 * Created by Deyan on 12.7.2015 ..
 */
class Contact {
    public Contact() {}

    public Contact(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String name;
    public int number;

    public Contact left;
    public Contact right;
    public Contact parent;
}

public class PhoneBook {
    public PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));

    private Contact root;

    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        PhoneBook ph = new PhoneBook();
        int commandsCount = scanner.nextInt();
        for (int i = 0; i < commandsCount; i++) {
            String command = scanner.nextString();
            if (command.equals("insert")) {
                ph.insert(scanner.nextInt(), scanner.nextString());
            }
            else if (command.equals("lookup")) {
                ph.writer.println(ph.lookup(scanner.nextString()));
            }
            else if (command.equals("remove")) {
                ph.remove(scanner.nextString());
            }
            else if (command.equals("list")) {
                ph.list();
            }
        }
        ph.writer.flush();

    }
    public void insert(int number, String name) {
        if (root == null) {
            root = new Contact(name, number);
        }

        Contact current = root;
        Contact last = current;
        while (current != null) {
            if (current.name.equals(name)) {
                current.number = number;
                return;
            }

            last = current;
            if (name.compareTo(current.name) < 0) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }

        if (name.compareTo(last.name) < 0) {
            last.left = new Contact(name, number);
            last.left.parent = last;
        }
        else {
            last.right = new Contact(name, number);
            last.right.parent = last;
        }
    }

    public void list() {
        list(root);
    }

    public void list(Contact root) {
        if (root == null) {
            return;
        }

        list(root.left);
        writer.println(root.name + " " + root.number);
        list(root.right);
    }

    public void remove(String name) {
        Contact current = root;
        Contact toDelete = null;
        while (current != null) {

            if (name.compareTo(current.name) < 0) {
                current = current.left;
            }
            else if (name.compareTo(current.name) > 0) {
                current = current.right;
            }
            else {
                toDelete = current;
                break;
            }
        }

        if (toDelete == null) {
            return;
        }

        if (toDelete.left == null) {
            replace(toDelete, toDelete.right);
        }
        else if (toDelete.right == null) {
            replace(toDelete, toDelete.left);
        }
        else {
            Contact min = min(toDelete.right);
            if (min.parent != toDelete) {
                replace(min, min.right);
                min.right = toDelete.right;
                min.right.parent = min;
            }
            replace(toDelete, min);
            min.left = toDelete.left;
            min.left.parent = min;
        }
    }

    private Contact min(Contact root) {
        Contact min = root;
        while (min != null && min.left != null) {
            min = min.left;
        }
        return min;
    }

    private void replace(Contact nodeToReplace, Contact replacement) {
        if (nodeToReplace.parent == null) {
            root = replacement;
        }
        else if (nodeToReplace.parent.left == nodeToReplace) {
            nodeToReplace.parent.left = replacement;
        }
        else {
            nodeToReplace.parent.right = replacement;
        }

        if (replacement != null) {
            replacement.parent = nodeToReplace.parent;
        }
    }

    public String lookup(String name) {
        return lookup(name, root);
    }

    private String lookup(String name, Contact root) {
        if (root == null) {
            return "NOT FOUND!";
        }

        if (root.name.equals(name)) {
            return Integer.toString(root.number);
        }

        if (name.compareTo(root.name) < 0) {
            return lookup(name, root.left);
        }

        if (name.compareTo(root.name) >= 0) {
            return lookup(name, root.right);
        }

        return "NOT FOUND!";
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