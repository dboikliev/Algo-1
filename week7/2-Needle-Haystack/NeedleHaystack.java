import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 20.7.2015 �..
 */


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
public class NeedleHaystack {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String haystack = scanner.nextLine();
        String needle = scanner.nextLine();
        find(haystack, needle);
    }


    public static void find(String haystack, String needle) {
        int base = 31;
        int current = 0;
        int m = 10007;
        for (int i = 0; i < needle.length(); i++) {
            current = current * base + haystack.charAt(i);//current + pow(base, needle.length() - i - 1, m) * haystack.charAt(i);
            current = current % m;
        }

        int needleHash = 0;
        for (int i = 0; i < needle.length(); i++) {
            needleHash = needleHash * base + needle.charAt(i); // needleHash + pow(base, needle.length() - i - 1, m) * needle.charAt(i);
            needleHash = needleHash % m;
        }

        int maxBase = pow(base, needle.length() - 1, m);
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int j = i + needle.length() - 1;
            if (current == needleHash && equals(haystack, needle, i, j)) {
                System.out.println(i);
            }
            current = (current + m - (haystack.charAt(i) * maxBase) % m) * base + haystack.charAt(Math.min(haystack.length() - 1, j + 1));
            current = current % m;
        }
    }

    private static int pow(int base, int pow, int modulo) {
        int res = 1;
        for (int i = 0; i < pow; i++) {
            res = res * base;
            res = res % modulo;
        }
        return res;
    }

    private static boolean equals(String haystack, String needle, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (Character.compare(haystack.charAt(i), needle.charAt(i - start)) != 0) {
                return false;
            }
        }
        return true;
    }
}
