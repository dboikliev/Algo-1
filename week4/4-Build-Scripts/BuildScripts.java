import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;


/**
 * Created by Deyan on 2.7.2015 ..
 */
public class BuildScripts {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        String[] names = new String[n];
        HashMap<String, Integer> projects = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.nextString();
            names[i] = name;
            projects.put(name, i);
        }
        String toBuild = scanner.nextString();
        HashMap<Integer, ArrayList<Integer>> relations = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            ArrayList<Integer> relatedProjects = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                relatedProjects.add(projects.get(scanner.nextString()));
            }
            relations.put(i, relatedProjects);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> sorted = new LinkedList<>();

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                boolean[] inPath = new boolean[n];
                if (!sort(relations, visited, inPath, i, sorted)) {
                    System.out.println("BUILD ERROR");
                    return;
                }
            }
        }

        while (sorted.size() > 1) {
            System.out.print(names[sorted.remove()] + " ");
        }
        System.out.print(names[sorted.remove()]);
    }

    private static boolean sort(HashMap<Integer, ArrayList<Integer>> relations, boolean[] visited, boolean[] inPath, int startNode, Queue<Integer> sorted) {
        if (inPath[startNode]) {
            return false;
        }
        inPath[startNode] = true;
        ArrayList<Integer> related = relations.get(startNode);
        boolean isOk = true;
        for (int i = 0; i < related.size(); i++) {
            Integer relatedProject = related.get(i);
            if (!visited[relatedProject]) {
                isOk &= sort(relations, visited, inPath, relatedProject, sorted);
            }
        }
        visited[startNode] = true;
        sorted.add(startNode);

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