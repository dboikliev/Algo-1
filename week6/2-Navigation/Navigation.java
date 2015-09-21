import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 13.7.2015 �..
 */
class Edge {
    public int start;
    public int end;
    public int distance;

    public Edge(int start, int end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}

public class Navigation {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int c = scanner.nextInt();
        ArrayList<Edge>[] streets = new ArrayList[2 * n];
        for (int i = 0; i < streets.length; i++) {
            streets[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int distance = scanner.nextInt();
            streets[start].add(new Edge(start, end, distance));
            streets[end].add(new Edge(end, start, distance));
        }

        boolean[] visited = new boolean[n * 2];

        PriorityQueue<Edge> edges = new PriorityQueue<>(new MinComparator());
        for (int i = 0; i < streets[s].size(); i++) {
            edges.add(streets[s].get(i));
        }
        visited[s] = true;
        ArrayList<Integer> path = new ArrayList<>();
        while (!edges.isEmpty()) {
            Edge e = edges.remove();
            if (visited[e.end]) {
                continue;
            }
            path.add(e.start);
            if (e.end == c) {
                System.out.println(e.distance);
                path.add(e.end);
                break;
            }

            visited[e.start] = true;
            for (int i = 0; i < streets[e.end].size(); i++) {
                Edge current = streets[e.end].get(i);
                if (!visited[current.end]) {
                    visited[current.start] = true;
                    edges.add(new Edge(e.end, current.end, e.distance + current.distance));
                }
            }
        }

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }
    }
}

class MinComparator implements Comparator<Edge> {

    @Override
    public int compare(Edge o1, Edge o2) {
        return o1.distance - o2.distance;
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