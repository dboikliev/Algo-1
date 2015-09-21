import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 30.7.2015 �..
 */
public class MaxBanana {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println(countBananas(matrix));
    }

    private static int countBananas(int[][] matrix) {
        int[][] count = matrix;

        for (int i = count.length - 1; i >= 0; i--) {
            for (int j = 0; j < count.length; j++) {
                if (i < count.length - 1 && j == 0) {
                    count[i][j] += count[i + 1][j];
                }
                else if (i == count.length - 1 && j > 0) {
                    count[i][j] += count[i][j - 1];
                }
                else if (i < count.length - 1 && j > 0) {
                    count[i][j] = Math.max(count[i][j] + count[i + 1][j], count[i][j] + count[i][j - 1]);
                }
            }
        }

        return count[0][count.length - 1];

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