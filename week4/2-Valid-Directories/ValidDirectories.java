import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class ValidDirectories {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        PrintWriter writer = new PrintWriter(System.out);
        int n = scanner.nextInt();
        int[][] fileSystem = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fileSystem[i][j] = scanner.nextInt();
            }
        }

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            boolean[] isInPath = new boolean[n];
            if (!visited[i] && !isValid(fileSystem, isInPath, visited, i)) {
                writer.println(false);
                writer.close();
                return;
            }
        }
        writer.println(true);
        writer.close();
        return;
    }

    private static boolean isValid(int[][] graph, boolean[] path, boolean[] visited, int root) {
        visited[root] = true;
        path[root] = true;
        boolean isOk = true;

        for (int i = 0; i < graph[root].length; i++) {
            if (graph[root][i] == 1 && path[i]) {
                return false;
            }

            if (graph[root][i] == 1 && !visited[i]) {
                isOk &= isValid(graph, path, visited, i);
            }
        }

        path[root] = false;
        return isOk;
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