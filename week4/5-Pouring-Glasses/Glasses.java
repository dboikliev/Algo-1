import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Deyan on 2.7.2015 ã..
 */
public class Glasses {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        PrintWriter writer = new PrintWriter(System.out);

        int[] capacity = new int[3];
        capacity[0] = scanner.nextInt();
        capacity[1] = scanner.nextInt();
        capacity[2] = scanner.nextInt();

        Integer[] water = new Integer[3];
        water[0] = scanner.nextInt();
        water[1] = scanner.nextInt();
        water[2] = scanner.nextInt();

        int goal = scanner.nextInt();

        boolean[][][] visited = new boolean[31][31][31];
        visited[water[0]][water[1]][water[2]] = true;
        int[][][][] path = new int[31][31][31][3];
        String result = pour(water, capacity, visited, goal, path);
        if (result.equals("")) {
            writer.println("IMPOSSIBLE");
            writer.close();
            return;
        }

        writer.println(result);
        writer.close();
    }

    public static String pour(Integer[] amount, int[] capacity, boolean[][][] visited, int goal, int[][][][] path) {
        Queue<Integer[]> combinations = new LinkedList<>();
        combinations.add(amount);
        while (!combinations.isEmpty()) {
            Integer[] current = combinations.remove();
            for (int i = 0; i < amount.length; i++) {
                for (int j = 0; j < amount.length; j++) {
                    if (i != j) {
                        Integer[] copy = new Integer[3];
                        copy[0] = current[0];
                        copy[1] = current[1];
                        copy[2] = current[2];
                        int subtract = Math.min(copy[i], capacity[j] - copy[j]);
                        copy[i] = copy[i] - subtract;
                        copy[j] = copy[j] + subtract;
                        if (!visited[copy[0]][copy[1]][copy[2]]) {
                            visited[copy[0]][copy[1]][copy[2]] = true;
                            combinations.add(copy);
                            int[] prev = new int[3];
                            prev[0] = i;
                            prev[1] = j;
                            prev[2] = subtract;
                            path[copy[0]][copy[1]][copy[2]] = prev;
                            String r = "";
                            int count = 0;
                            if (copy[0] == goal || copy[1] == goal || copy[2] == goal) {
                                while (copy[0] != amount[0] || copy[1] != amount[1] || copy[2] != amount[2]) {
                                    count++;
                                    int[] prevInPath = path[copy[0]][copy[1]][copy[2]];
                                    r = (prevInPath[0] + 1) + " " + (prevInPath[1] + 1) + "\n" + r;
                                    copy[prevInPath[0]] += prevInPath[2];
                                    copy[prevInPath[1]] -= prevInPath[2];
                                }
                                if (count > 0) {
                                    r = count + "\n" + r;
                                }
                                return r;
                            }
                        }
                    }
                }
            }
        }
        return "";
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