import java.io.*;
import java.util.StringTokenizer;

public class BirthdayRanges {

    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        BirthdayRanges ph = new BirthdayRanges(366);
        for (int i = 0; i < n; i++) {
            ph.add(scanner.nextInt(), 1);
        }
        for (int i = 0; i < m; i++) {
            String command = scanner.nextString();
            if (command.equals("add")) {
                ph.add(scanner.nextInt(), scanner.nextInt());
            }
            else if (command.equals("count")) {
                writer.println(ph.count(scanner.nextInt(), scanner.nextInt()));
            }
            else if (command.equals("remove")) {
                ph.remove(scanner.nextInt(), scanner.nextInt());
            }
        }
        writer.close();
    }

//    private int[] sums = new int[1024];
    private int[] sums;
    private int offset;
    public BirthdayRanges(int n) {
        sums = new int[n];
        double power = 1;
        double elementsSize = Math.pow(2.0, power);
        while (elementsSize < n) {
            ++power;
            elementsSize = Math.pow(2.0, power);
        }
        sums = new int[2*(int)elementsSize];
//        for (int i = 0; i < sums.length; i++) {
//            sums[i] = 0;
//        }
        offset = (int)elementsSize;
    }


    // adds people who are born on a specific day
    public void add(int day, int numberOfPeople) {
        int index = offset + day;
        sums[index] += numberOfPeople;
        while (index > 0) {
            index /= 2;
            sums[index] = sums[index * 2] + sums[index * 2 + 1];
        }
    }

    // removes people who are born on a specific day
    public void remove(int day, int numberOfPeople) {
        int index = offset + day;
        sums[index] = Math.max(0, sums[index] - numberOfPeople);
        while (index > 0) {
            index /= 2;
            sums[index] = sums[index * 2] + sums[index * 2 + 1];
        }
    }

    // returns the number of people born in a range
    public int count(int startDay, int endDay) {
        return query(endDay + 1) - query(startDay);
    }

    private int query(int index) {
        int newIndex = index + offset;
        int sum = 0;
        while (newIndex > 0) {
            if (isRightChild(newIndex)) {
                sum += leftSibling(newIndex);
            }
            newIndex = parent(newIndex);
        }
        return sum;
    }

    private boolean isRightChild(int index) {
        return index % 2 == 1;
    }

    private int leftSibling(int index) {
        return sums[index - 1];
    }

    private int parent(int index) {
        return index / 2;
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