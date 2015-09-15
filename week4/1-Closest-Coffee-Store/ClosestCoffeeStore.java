import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ClosestCoffeeStore {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        PrintWriter writer = new PrintWriter(System.out);
        int n = scanner.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        int start = scanner.nextInt();
        boolean[] isCoffeeStore  = new boolean[n];
        for (int i = 0; i < n; i++) {
            isCoffeeStore[i] = (scanner.nextInt() == 1);
        }

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Queue<Integer> nodes = new LinkedList<>();
        nodes.add(start);
        visited[start] = true;
        while (!nodes.isEmpty()) {
            int currentNode = nodes.remove();
            if (isCoffeeStore[currentNode]) {
                writer.println(dist[currentNode]);
                writer.close();
                return;
            }
            for (int i = 0; i < n; i++) {
                if (graph[currentNode][i] == 1 && !visited[i]) {
                    nodes.add(i);
                    visited[i] = true;
                    dist[i] = dist[currentNode] + 1;
                }
            }
        }
        writer.println(-1);
        writer.close();
        return;
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