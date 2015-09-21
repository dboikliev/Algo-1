import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 13.7.2015 ã..
 */
public class LowCostFlights {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        long[][] flights = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                flights[i][j] = scanner.nextInt();
                if (i != j && flights[i][j] == 0) {
                    flights[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (flights[i][j] > flights[i][k] + flights[k][j]) {
                        flights[i][j] = Math.min(flights[i][j], flights[i][k] + flights[k][j]);
                    }
                }
            }
        }

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(flights[a][b] < Integer.MAX_VALUE ? flights[a][b] : "NO WAY");
        }
    }

    private static void floydWarshall(int[][] graph) {

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