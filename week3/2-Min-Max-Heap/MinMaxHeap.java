import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MinMaxHeap {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n =  scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
            int level = (int)(log(i + 1, 2)) + 1;

            if (level %  2 == 0) {
                if (numbers[i] < numbers[(i - 1) / 2])  {
                    System.out.println("NO");
                    return;
                }
                else {
                    int currentLevel = level;
                    int parent = (i - 1) / 2;
                    while  (currentLevel > 1) {
                        currentLevel--;
                        if (currentLevel % 2 == 0 && numbers[parent] < numbers[i]) {
                            System.out.println("NO");
                            return;
                        }

                        parent = (parent - 1) / 2;
                    }
                }
            }
            else {
                if (numbers[i] > numbers[(i - 1) / 2])  {
                    System.out.println("NO");
                    return;
                }
                else {
                    int currentLevel = level;
                    int parent = (i - 1) / 2;
                    while  (currentLevel > 1) {
                        currentLevel--;
                        if (currentLevel % 2 != 0 && numbers[parent] > numbers[i]) {
                            System.out.println("NO");
                            return;
                        }

                        parent = (parent - 1) / 2;
                    }
                }
            }
        }
        System.out.println("YES");
    }

    private static double log(double number, double base) {
        return Math.log(number) / Math.log(base);
    }



    public static class Node {

        public int value;
        public Node left;
        public Node right;
    }


    // Checks if a binary tree is a min/max heap.
    public boolean isMinMax(Node root) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        int level = 0;
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
            level++;
            if (level % 2 == 0) {
                if (node.left != null && node.left.value > node.value || node.right != null && node.right.value > node.value) {
                    return false;
                }
            }
            else {
                if (node.left != null && node.left.value < node.value || node.right != null && node.right.value < node.value) {
                    return false;
                }
            }
        }
        return true;
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