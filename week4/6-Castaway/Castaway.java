import javafx.geometry.Pos;
import org.omg.PortableInterceptor.ServerRequestInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Deyan on 20.9.2015 ã..
 */
public class Castaway {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();

        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = scanner.nextString();
            for (int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j);
            }
        }
        int h = scanner.nextInt();
        HashMap<String, HashSet<String>> connections = new HashMap<>();
        for (int i = 0; i < h; i++) {
            String a = scanner.nextString();
            String b = scanner.nextString();
            if (!connections.containsKey(a)) {
                connections.put(a, new HashSet<String>());
            }
            if (!connections.containsKey(b)) {
                connections.put(b, new HashSet<String>());
            }

            connections.get(a).add(b);
            connections.get(a).add(b);
        }

        ArrayList<String> startPorts = addConnections(connections, map, n, m, startX, startY);
        HashSet<String> endPorts = findEndPorts(connections, map, n, m, endX, endY);

        Queue<String> ports = new LinkedList<>();

        HashMap<String, Integer> distance = new HashMap<>();
        HashMap<String, Boolean> visited = new HashMap<>();

        for (int i = 0; i < startPorts.size(); i++) {
            ports.add(startPorts.get(i));
            visited.put(startPorts.get(i), true);
            distance.put(startPorts.get(i), 1);
        }


        while (!ports.isEmpty()) {
            String current = ports.remove();
            HashSet<String> connectedPorts = connections.get(current);
            if (connectedPorts != null) {
                for (String p : connectedPorts) {
                    String conn = p;
                    if (visited.get(conn) == null || !visited.get(conn)) {
                         ports.add(conn);
                        visited.put(conn, true);
                        int currentDist = distance.get(current) == null ? 0 : distance.get(current);
                        distance.put(conn, currentDist + 1);

                        if (endPorts.contains(conn)) {
                            System.out.println(distance.get(conn) + 1);
                            return;
                        }
                    }
                }
            }
        }
    }

    private static ArrayList<String> addConnections(HashMap<String, HashSet<String>> connections, char[][] map, int n, int m, int startX, int startY) {
        boolean[][] visited = new boolean[n][m];
        ArrayList<String> startingPorts = new ArrayList<>();
        boolean setStartingPorts = false;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (isOnIsland(map[i][j])) {
                    Queue<Position> positions = new LinkedList<>();
                    positions.add(new Position(i, j));
                    visited[i][j] = true;
                    HashSet<String> newConnections = new HashSet<>();
                    while (!positions.isEmpty()) {
                        Position current = positions.remove();
                        if (current.x == startX && current.y == startY) {
                            setStartingPorts = true;
                        }

                        move(positions, newConnections, current.x + 1, current.y, n, m, visited, map);
                        move(positions, newConnections, current.x, current.y + 1, n, m, visited, map);
                        move(positions, newConnections, current.x - 1, current.y, n, m, visited, map);
                        move(positions, newConnections, current.x, current.y - 1, n, m, visited, map);
                    }


                    for (String k : newConnections) {
                        for (String f : newConnections) {
                            if (!k.equals(f)) {
                                if (!connections.containsKey(k)) {
                                    connections.put(k, new HashSet<String>());
                                }
                                connections.get(k).add(f);

                                if (!connections.containsKey(f)) {
                                    connections.put(f, new HashSet<String>());
                                }
                                connections.get(f).add(k);
                            }
                        }
                    }

//                    for (int k = 0; k < newConnections.size(); k++) {
//                        for (int f = 0; f < newConnections.size(); f++) {
//                            if (k != f) {
//                                String key = newConnections.get(k).toString();
//                                String value = newConnections.get(f).toString();
//                                if (!connections.containsKey(key)) {
//                                    connections.put(key, new ArrayList<String>());
//                                }
//                                connections.get(key).add(value);
//
//                                if (!connections.containsKey(value)) {
//                                    connections.put(value, new ArrayList<String>());
//                                }
//                                connections.get(value).add(key);
//                            }
//                        }
//                    }

                    if (setStartingPorts) {
                        for (String c : newConnections) {
                            startingPorts.add(c);
                        }
                        setStartingPorts = false;
                    }
                }
            }
        }
        return startingPorts;
    }

    private static HashSet<String> findEndPorts(HashMap<String, HashSet<String>> connections, char[][] map, int n, int m, int endX, int endY) {
        boolean[][] visited = new boolean[n][m];
        Queue<Position> positions = new LinkedList<>();
        positions.add(new Position(endX, endY));
        visited[endX][endY] = true;
        HashSet<String> endPorts = new HashSet<>();
        while (!positions.isEmpty()) {
            Position current = positions.remove();

            move(positions, endPorts, current.x + 1, current.y, n, m, visited, map);
            move(positions, endPorts, current.x, current.y + 1, n, m, visited, map);
            move(positions, endPorts, current.x - 1, current.y, n, m, visited, map);
            move(positions, endPorts, current.x, current.y - 1, n, m, visited, map);
        }
        return endPorts;
    }

    private static void move(Queue<Position> positions, ArrayList<String> newConnections, int x, int y, int m, int n, boolean[][] visited, char[][] map) {
        if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
            if (isOnIsland(map[x][y])) {
                visited[x][y] = true;
                positions.add(new Position(x, y));
            }
            else if (isPort(map[x][y])) {
                newConnections.add(Character.toString(map[x][y]));
            }
        }
    }

    private static void move(Queue<Position> positions, HashSet<String> newConnections, int x, int y, int m, int n, boolean[][] visited, char[][] map) {
        if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
            if (isOnIsland(map[x][y])) {
                visited[x][y] = true;
                positions.add(new Position(x, y));
            }
            else if (isPort(map[x][y])) {
                newConnections.add(Character.toString(map[x][y]));
            }
        }
    }

    private static boolean isOnIsland(char symbol) {
        return Character.compare(symbol, '#') == 0;
    }

    private static boolean isPort(char symbol) {
        return Character.compare(symbol, '#') != 0 && Character.compare(symbol, '.') != 0;
    }
}

class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object b) {
        Position position = (Position)b;
        if (this.x == position.x && this.y == position.y) {
            return true;
        }
        return false;
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