import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InTheArmyNow {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int bitSize = calcBitSize(n);
        int maxJumps = -1;
        int row = 0;
        for (int i = 0; i < k; i++) {
            BIT bit = new BIT(bitSize);
            int[] elements = new int[n];
            int jumps = 0;
            for (int j = 0; j < n; j++) {
                int number = scanner.nextInt();
                elements[j] = number;
            }

            for (int j = n -1; j >= 0; j--) {
                int index = elements[j] - 1;
                bit.set(index, 1);
                jumps += bit.query(index);
            }

            if (jumps > maxJumps) {
                row = i + 1;
                maxJumps = jumps;
            }
        }
        System.out.println(row);
    }

    private static int calcBitSize(int n) {
        double power = 1;
        double elementsSize = Math.pow(2.0 ,power);
        while (elementsSize < n) {
            elementsSize = Math.pow(2.0, ++power);
        }
//        elements = new int[2 * (int)elementsSize];
//        offset = (int)elementsSize;
        return (int)elementsSize;
    }

    public static class BIT {
        private int[] elements;
        private int offset;

        public BIT(int size) {
            elements = new int[2 * size];
            offset = size;
        }

        public void set(int index, int value) {
            int newIndex = offset + index;
            elements[newIndex] = value;
            while (newIndex > 0) {
                newIndex /= 2;
                elements[newIndex] = elements[newIndex * 2] + elements[newIndex * 2 + 1];
            }
        }

        public int query(int index) {
            int newIndex = index + offset;
            int sum = 0;
            while (newIndex > 0) {
                if (isRight(newIndex)) {
                    sum += leftSibling(newIndex);
                }
                newIndex = parent(newIndex);
            }
            return sum;
        }

        private boolean isRight(int index) {
            return index % 2 > 0 && index > 1;
        }

        private int leftSibling(int index) {
            return elements[index - 1];
        }

        private int parent(int index) {
            return index / 2;
        }
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