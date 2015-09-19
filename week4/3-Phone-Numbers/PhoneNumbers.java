import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Deyan on 1.7.2015 ã..
 */
public class    PhoneNumbers {
    public static void main(String[] args) throws IOException {

        MyScanner scanner = new MyScanner();
        PrintWriter writer = new PrintWriter(System.out);
        int n = scanner.nextInt();
        HashMap<Integer, Integer> numbersStudents = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            numbersStudents.put(scanner.nextInt(), i);
        }

        ArrayList<Integer>[] studentsContacts = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            studentsContacts[i] = new ArrayList<Integer>();
            for (int j = 0; j < m; j++) {
                studentsContacts[i].add(numbersStudents.get(scanner.nextInt()));
            }
        }

        boolean[] visited = new boolean[n];
        int calls = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                calls++;
                Queue<Integer> nodes = new LinkedList<Integer>();
                nodes.add(i);
                while (!nodes.isEmpty()) {
                    int current = nodes.remove();
                    ArrayList<Integer> currentContacts = studentsContacts[current];
                    for (int j = 0; j < currentContacts.size(); j++) {
                        int contact = currentContacts.get(j);
                        if (!visited[contact]) {
                            nodes.add(contact);
                            visited[contact] = true;
                        }
                    }
                }
            }
        }
        writer.println(calls);
        writer.close();
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