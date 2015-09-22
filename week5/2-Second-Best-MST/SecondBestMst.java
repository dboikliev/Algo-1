import jdk.internal.util.xml.impl.Pair;
        import sun.nio.cs.HistoricallyNamedCharset;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.lang.reflect.Array;
        import java.security.Key;
        import java.util.*;

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


class Edge {
    public int start;
    public int end;
    public int weight;
}

class EdgeComparator implements Comparator<Edge> {

    @Override
    public int compare(Edge o1, Edge o2) {
        return o1.weight - o2.weight;
    }
}

class KeyValue {
    public KeyValue(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int key;
    public int value;
}

public class SecondBestMst {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        ArrayList<KeyValue>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            Edge edge = new Edge();
            edge.start = scanner.nextInt();
            edge.end = scanner.nextInt();
            edge.weight = scanner.nextInt();

            if (graph[edge.start] == null) {
                graph[edge.start] = new ArrayList<>();
                graph[edge.start].add(new KeyValue(edge.end, edge.weight));
            }
            else {
                graph[edge.start].add(new KeyValue(edge.end, edge.weight));
            }

            if (graph[edge.end] == null) {
                graph[edge.end] = new ArrayList<>();
                graph[edge.end].add(new KeyValue(edge.start, edge.weight));
            }
            else {
                graph[edge.end].add(new KeyValue(edge.start, edge.weight));
            }
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>(new EdgeComparator());
        for (int i = 0; i <graph[1].size(); i++) {
            Edge e = new Edge();
            e.start = 1;
            e.end = graph[e.start].get(i).key;
            e.weight = graph[e.start].get(i).value;
            edges.add(e);
        }
        boolean[] visited = new boolean[n];
        visited[1] = true;
        int cost = 0;

        ArrayList<KeyValue>[] tree = new ArrayList[n];

        PriorityQueue<Edge> remaining = new PriorityQueue<>(new EdgeComparator());
        int count = 1;
        while (!edges.isEmpty() && count < n) {
            Edge current = edges.remove();
            if (visited[current.end]) {
                remaining.add(current);
                continue;
            }
            count++;
            visited[current.end] = true;
            if (tree[current.start] == null) {
                tree[current.start] = new ArrayList<>();
            }
            tree[current.start].add(new KeyValue(current.end, current.weight));
            if (tree[current.end] == null) {
                tree[current.end] = new ArrayList<>();
            }
            tree[current.end].add(new KeyValue(current.start, current.weight));
            cost += current.weight;

            for (int i = 0; i < graph[current.end].size(); i++) {
                Edge e = new Edge();
                e.start = current.end;
                e.end = graph[e.start].get(i).key;
                e.weight = graph[e.start].get(i).value;
                if (!visited[e.end]) {
                    edges.add(e);
                }
            }
        }

        Edge r = remaining.remove();
        int[][] max = new int[n][n];
        fillMax(max, tree, count);

        System.out.println(cost - max[r.start][r.end] + r.weight);
    }

    private static void fillMax(int[][] max, ArrayList<KeyValue>[] tree, int count) {
        for (int i = 1; i < count; i++) {
            for (int j = 0; j < tree[i].size(); j++) {
                fillMaxVisit(max, tree, i, i);
            }
        }
    }

    private static void fillMaxVisit(int[][] max, ArrayList<KeyValue>[] tree, int u, int current) {
        ArrayList<KeyValue> adjacent = tree[current];
        for (int i = 0; i < adjacent.size(); i++) {
            KeyValue sibling = adjacent.get(i);
            if (max[u][sibling.key] == 0 && u != sibling.key) {
                if (current == u || sibling.value > max[u][current]) {
                    max[u][sibling.key] = sibling.value;
                }
                else {
                    max[u][sibling.key] = max[u][current];
                }
                fillMaxVisit(max, tree, u, tree[current].get(i).key);
            }
        }
    }
}
