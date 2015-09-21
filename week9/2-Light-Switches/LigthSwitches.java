import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Deyan on 3.8.2015 �..
 */
public class LigthSwitches {
    public static void main(String[] args) {
        int lamps = 0;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 16; i++) {
            String lamp = sc.next("[a-z]+");
            if (lamp.equals("on")) {
                lamps <<= 1;
                lamps ^= 1;
            }
            else {
                lamps <<= 1;
            }
        }

        ArrayList<Integer> switches = new ArrayList<>();
        HashMap<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            int lampSwitch = 0;
            for (int j = 0; j < 16; j++) {
                lampSwitch <<= 1;
                lampSwitch ^= sc.nextInt();
            }
            switches.add(lampSwitch);
            maps.put(i + 1, lampSwitch);
        }
        turnOffLamps(lamps, switches, maps);
    }

    public static void turnOffLamps(int lamps, ArrayList<Integer> switches, HashMap<Integer, Integer> maps) {
        HashMap<Integer, Boolean> visited = new LinkedHashMap<>();
        HashMap<Integer, Integer> path = new HashMap<>();
        Queue<Integer> states = new LinkedList<>();
        states.add(lamps);
        visited.put(lamps, true);
        int last = 0;
        boolean isFound = false;
        while (!states.isEmpty() && !isFound) {
            int current = states.remove();
            for (int i = 0; i < switches.size(); i++) {
                int newState = switches.get(i) ^ current;
                if (visited.get(newState) == null || !visited.get(newState)) {
//                    System.out.println(i + 1);
                    ;
                    path.put(newState, i + 1);
                    if (newState == 0) {
                        System.out.println("YES");
                        last = newState;
//                        return;
                        isFound = true;
                    }

                    states.add(newState);
                    visited.put(newState, true);
                }
            }
        }

        Integer current = path.get(last);
        while (current != null) {
            System.out.println(current);
            last = maps.get(current) ^ last;
            current = path.get(last);
        }
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

