import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Deyan on 22.9.2015 ã..
 */
public class DnaSequence {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        HashSet<String> used = new HashSet<>();
        HashMap<String, ArrayList<Edge>> graph = new HashMap<>();
        String s = "";

        for (int i = 0; i < n; i++) {
            String nucleotide = scanner.nextString();
            String start = nucleotide.substring(0, 3);
            if (s.equals("")) {
                s = start;
            }

            if (graph.get(start) == null) {
                graph.put(start, new ArrayList<>());
            }

            String end = nucleotide.substring(nucleotide.length() - 3, nucleotide.length());
            if (graph.get(end) == null) {
                graph.put(end, new ArrayList<>());
            }

            String value = nucleotide.substring(3, nucleotide.length() - 3);
            Edge edge1 = new Edge(value, end);
            graph.get(start).add(edge1);
            Edge edge2 = new Edge(value, start);
            graph.get(end).add(edge2);

        }
        Deque<String> sequence = new LinkedList<>();
        sequence.addLast("");
        sequence.addLast(s);
        HashMap<String, Integer> nodes = new HashMap<>();
        nodes.put(s, 1);
        buildSequence(graph, s, sequence, used, "", "", "", nodes, graph.size());
        while (!sequence.isEmpty()) {
            System.out.print(sequence.removeFirst());
        }
        System.out.println();
    }

    private static void buildSequence(HashMap<String, ArrayList<Edge>> graph, String root, Deque<String> sequence, HashSet<String> used, String start, String value, String end, HashMap<String, Integer> nodes, int count) {
        ArrayList<Edge> siblings = graph.get(root);


        for (int i = 0; i < siblings.size(); i++) {
            Edge e = siblings.get(i);

            if (!used.contains(root + e.value + e.next) && !used.contains(e.next + e.value + root)) {
                sequence.addLast(e.value);
                sequence.addLast(e.next);
                used.add(root + e.value + e.next);
                used.add(e.next + e.value + root);
                nodes.put(e.next, nodes.get(e.next) == null ? 1 : nodes.get(e.next) + 1);
                buildSequence(graph, e.next, sequence, used, root, e.value, e.next, nodes, count);
            }
        }



        if (nodes.size() == count) {
//            System.out.println(sequence);
            return ;
        }
        else {

            if (nodes.get(end) != null) {
                if (nodes.get(end) - 1 == 0) {
                    nodes.remove(end);
                } else {
                    nodes.put(end, nodes.get(end) - 1);
                }
            }


            used.remove(start + value + end);
            used.remove(end + value + start);
            sequence.removeLast();
            sequence.removeLast();
            return ;
        }
    }
}

class Edge {
    String value;
    String next;

    public Edge(String value, String next) {
        this.value = value;
        this.next = next;
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
