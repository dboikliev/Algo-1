import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 16.7.2015 �..
 */
public class Quadruplets {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; ++i) {
            b[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; ++i) {
            c[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; ++i) {
            d[i] = scanner.nextInt();
        }

        int next = 0;
        int[] ab = new int[n * n];
        HashMap<Integer, Integer> encounters = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
//                ab[next] = a[i] + b[j];
//                next++;
                int sum = a[i] + b[j];
                Integer count = encounters.get(sum);
                if (count != null) {
                    encounters.put(sum, count + 1);
                }
                else {
                    encounters.put(sum, 1);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int sum = c[i] + d[j];
                Integer res = encounters.get(-sum);
                count += (res == null ? 0 : res);
            }
        }
        System.out.println(count);
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