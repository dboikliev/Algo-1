import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 22.6.2015 ..
 */
public class Main {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
            if (i > 0) {
                if (i % 2 == 1 && numbers[i] != 0 && numbers[i] > numbers[(i - 1) / 2]) {
                    System.out.println("NO");
                    return;
                }
                else if (i % 2 == 0 && numbers[i] != 0 && numbers[i] < numbers[(i - 1) / 2]) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
    }

//    public static boolean isBST(int[] numbers, int root) {
//        int leftIndex = 2 * root + 1;
//        int rightIndex = 2 * root + 2;
//        if ((leftIndex < numbers.length && numbers[leftIndex] != 0 && numbers[leftIndex] >= numbers[root]) || (rightIndex < numbers.length && numbers[rightIndex] != 0 && numbers[rightIndex] <= numbers[root])) {
//            return false;
//        }
//
//        boolean result = true;
//        if (leftIndex < numbers.length && numbers[leftIndex] > 0) {
//            result &= isBST(numbers, leftIndex);
//        }
//
//        if (rightIndex < numbers.length && numbers[rightIndex] > 0) {
//            result &= isBST(numbers, leftIndex);
//        }
//
//        return result;
//    }
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