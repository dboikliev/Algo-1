import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Deyan on 3.8.2015 �..
 */
public class BankRobbery {
    public static void main(String[] args) throws IOException {
        MyScanner sc = new MyScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int node = sc.nextInt();
            int nextNode = sc.nextInt();
            addNode(node, nextNode, map);
            addNode(nextNode, node, map);
        }
        int bank = sc.nextInt();
        int police = sc.nextInt();
        int heli = sc.nextInt();
        System.out.print(escape(n, m, map, bank, police, heli));
    }

    private static void addNode(int node, int nextNode, HashMap<Integer, ArrayList<Integer>> map) {
        if (map.containsKey(node)) {
            map.get(node).add(nextNode);
        }
        else {
            map.put(node, new ArrayList<>());
            map.get(node).add(nextNode);
        }
    }

    public static int escape(int n, int m, HashMap<Integer, ArrayList<Integer>> graph, int bank, int police, int heli) {
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> nodes = new LinkedList<>();
        nodes.add(police);
        visited[police] = true;
        while (!nodes.isEmpty()) {
            int current = nodes.remove();
            ArrayList<Integer> nextNodes = graph.get(current);
            if (nextNodes != null) {
                for (int i = 0; i < nextNodes.size(); i++) {
                    int sib = nextNodes.get(i);
                    if (!visited[sib]) {
                        visited[sib] = true;
                        dist[sib] = dist[current] + 1;
                        nodes.add(sib);
                    }

                }
            }
        }

        int min = 0;
        int max = n;
        boolean foundHeli = false;
        while (!foundHeli) {
            foundHeli = false;
            int waited = (min + max) / 2;
            Queue<Integer> nodesRobber = new LinkedList<>();
            nodesRobber.add(bank);
            int[] distRobber = new int[n + 1];
            distRobber[bank] = waited;
            boolean[] visitedRobber = new boolean[n + 1];
            visitedRobber[bank] = true;
            while (!nodesRobber.isEmpty() && !foundHeli) {
                int current = nodesRobber.remove();
                ArrayList<Integer> nextNodes = graph.get(current);
                if (nextNodes != null) {
                    for (int i = 0; i < nextNodes.size(); i++) {
                        int sib = nextNodes.get(i);
                        if (!visitedRobber[sib]) {
                            distRobber[sib] = distRobber[current] + 1;
                            if (distRobber[sib] < dist[sib]) {
                                nodesRobber.add(sib);
                                if (sib == heli) {
                                    foundHeli = true;
                                    min = waited;
                                    break;
                                }
                                visitedRobber[sib] = true;
                            }
}
                    }
                }
            }

            if (!foundHeli) {
                max = waited;
            }
        }
        return min;
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

