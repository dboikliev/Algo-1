import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RMQ {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        RMQ rmq = new RMQ(n);
        for (int i = 0; i < n; i++) {
            rmq.set(i, scanner.nextInt());
        }

        for (int i = 0; i < k; i++) {
            String query = scanner.nextString();
            int x1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            if (query.equals("min")) {
                System.out.println(rmq.min(x1, x2));
            }
            else if (query.equals("set")) {
                rmq.set(x1, x2);
            }
        }
    }


    private int[] elements;
    private int[] values;
    private int offset;

    public RMQ(int size) {
        values = new int[size];
        double power = 1;
        double elementsSize = Math.pow(2.0, power);
        while (elementsSize < size) {
            ++power;
            elementsSize = Math.pow(2.0, power);
        }
        elements = new int[2*(int)elementsSize];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = Integer.MAX_VALUE;
        }
    }

    // sets the value at index
    public void set(int index, int value) {
	
        int newIndex = elements.length / 2 + index;
        elements[newIndex] = value;
        while (newIndex > 0) {
            newIndex = (newIndex) / 2;
            elements[newIndex] = Math.min(elements[newIndex * 2], elements[newIndex * 2 + 1]);
        }
    }
	
    public int min(int startIndex, int endIndex) {
        return min(startIndex, endIndex, 0, elements.length / 2 - 1, 1);
    }

    public int min(int startIndex, int endIndex, int low, int high, int root) {
        if (startIndex <= low && endIndex >= high) {
            return elements[root];
        }

        if (startIndex > high || endIndex < low) {
            return Integer.MAX_VALUE;
        }

        int mid = (low + high) / 2;
        int left = root * 2;
        int right = root * 2 + 1;
        return Math.min(min(startIndex, endIndex, low, mid, left), min(startIndex, endIndex, mid + 1, high, right));
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