import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quadruplets {
    class BinarySearch {
        public int find(List<Integer> elements, int value) {
            return binarySearch(elements, value, 0, elements.size());
        }

        public int find(List<Integer> elements, int value, int start) {
            return binarySearch(elements, value, start, elements.size());
        }

        private int binarySearch(List<Integer> elements, int value, int left, int right) {
            if (left >= elements.size()) {
                return -1;
            }

            if (left == right && elements.get(left) <= value) {
                return left;
            } else if (left == right && elements.get(left) != value) {
                return left;
            }

            int mid = (left + right) / 2;
            if (elements.get(mid) >= value) {
                return binarySearch(elements, value, left, mid);
            } else if (value > elements.get(mid)) {
                return binarySearch(elements, value, mid + 1, right);
            }
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }

        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = scanner.nextInt();
        }

        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = scanner.nextInt();
        }

        Quadruplets q = new Quadruplets();
        System.out.println(q.zeroQuadrupletsCount(a, b, c, d));
    }

    // Returns the number of quadruplets that sum to zero.
    public int zeroQuadrupletsCount(int[] a, int[] b, int[] c, int[] d) {
        List<Integer> ab = mergeArrays(a, b);
        List<Integer> cd = mergeArrays(c, d);
        Collections.sort(cd);
        BinarySearch bs = new BinarySearch();
        int quadruplets = 0;

        for (int i : ab) {
            int minIndex =  bs.find(cd, -i);
            if (minIndex < 0) {
                continue;
            }

            int maxIndex = cd.size() - 1;
            if (cd.get(maxIndex) != -i)
                maxIndex = bs.find(cd, -i + 1, Math.max(minIndex, 0));
				
            if (maxIndex >= 1 && maxIndex <= cd.size() - 1 && cd.get(maxIndex - 1) == -i && cd.get(maxIndex) != -i) {
                maxIndex--;
            }

                if (maxIndex >= 0 && cd.get(Math.max(minIndex, 0)) == -i  && maxIndex >= 0 && maxIndex <= cd.size() - 1 && cd.get(maxIndex) == -i) {
                quadruplets += (maxIndex - Math.max(minIndex, 0) + 1);
            }
        }
        return quadruplets;
    }

    private ArrayList<Integer> mergeArrays(int[] a, int[] b) {
        ArrayList<Integer> ab = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                ab.add(a[i] + b[j]);
            }
        }
        return ab;
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

}