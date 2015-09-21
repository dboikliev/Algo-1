import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 13.7.2015 ã..
 */
public class VitoshaRun {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int startRow = scanner.nextInt();
        int startCol = scanner.nextInt();
        int endRow = scanner.nextInt();
        int endCow = scanner.nextInt();
        int[][] heights = new int[n][n];

        int[][] dist = new int[n][n];
        minutes(heights, new Position(startCol, startRow, heights[startRow][startRow]), endRow, endCow);
    }

    private static void minutes(int[][] heights, Position root, int destRow, int destCol) {
        Queue<Position> positions = new LinkedList<>();
        positions.add(root);
        while (!positions.isEmpty()) {
            Position current = positions.remove();
            if (current.Col < 0 || current.Col >= heights.length || current.Row < 0 || current.Row > heights.length) {
                continue;
            }

            if (current.Col == destCol && current.Row == destRow) {
                System.out.print(current.distance);
                break;
            }

//            if (current.Col - 1 >= 0 && current.Row - 1 >= 0) {
            positions.add(new Position(current.Col - 1, current.Row - 1, current.distance + 1));
//            }
//            if (current.Col - 1 >= 0) {
            positions.add(new Position(current.Col - 1, current.Row, current.distance + 1));
//            }
//            if (current.Col - 1 >= 0 && current.Row + 1 >= 0) {
            positions.add(new Position(current.Col - 1, current.Row + 1, current.distance + 1));
            positions.add(new Position(current.Col, current.Row + 1, current.distance + 1));
            positions.add(new Position(current.Col + 1, current.Row + 1, current.distance + 1));
            positions.add(new Position(current.Col + 1, current.Row, current.distance + 1));
            positions.add(new Position(current.Col + 1, current.Row - 1, current.distance + 1));
//            }
        }
    }
}

class Position {
    public int Col;
    public int Row;
    public int distance;

    public Position(int col, int row, int distance) {
        this.Col = col;
        this.Row = row;
        this.distance = distance;
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