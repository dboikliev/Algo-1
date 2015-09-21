import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 16.7.2015 ã..
 */
public class KIntersect {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, Integer> map = new HashMap<>();
        MyScanner sc = new MyScanner();
        int n = sc.nextInt();
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
        if (n == 1) {
            int m = sc.nextInt();
            for (int i = 0; i < m; ++i) {
                int num = sc.nextInt();
                if (!map.containsKey(num)) {
                    writer.println(num);
                    map.put(num, 1);
                }
            }
            writer.close();
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            int m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                int num = sc.nextInt();
                Integer res = map.get(num);
                if (i == 0) {
                    map.put(num, 0);
                }
                else if (res != null && res == i - 1) {
                    map.put(num, res + 1);
                }
            }
        }

        for (int i = n - 1; i < n; i++) {
            int m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                int num = sc.nextInt();
                Integer res = map.get(num);
                if (res != null && res == i - 1) {
                    System.out.println(num);
                }
            }
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