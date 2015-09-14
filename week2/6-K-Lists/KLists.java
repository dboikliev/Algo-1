import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class KLists {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int k = scanner.nextInt();
        Heap heap = new Heap();

        for (int i = 0; i < k; i++) {
            int number = scanner.nextInt();
            while ((number) != -1) {
                heap.insert(number);
                number = scanner.nextInt();
            }
        }
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
        while (heap.size() > 1) {
            writer.print(heap.min());
            writer.print(" ");
            heap.removeMin();
        }
        writer.print(heap.min());
        writer.close();
    }
}

class Heap {
    private int currentIndex = 1;
    private ArrayList<Integer> elements = new ArrayList<>();

    public Heap() {
        elements.add(Integer.MIN_VALUE);
    }

    public void insert(int value) {
        elements.add(value);
        int index = currentIndex;
        while (index > 1 && elements.get(parent(index)) > elements.get(index)) {
            Collections.swap(elements, index, parent(index));
            index = parent(index);
        }
        currentIndex++;
    }

    public void removeMin() {
        if (size() <= 0) {
            return;
        }

        Collections.swap(elements, 1, elements.size() - 1);
        elements.remove(elements.size() - 1);
        maintainMin(1);
    }

    public int size() {
        return elements.size() - 1;
    }

    private int parent(int index) {
        return index / 2;
    }

    private int left(int index) {
        return 2 * index;
    }

    private int right(int index) {
        return 2 * index + 1;
    }

    private void maintainMin(int index) {
        int left = left(index);
        int right = right(index);
        int smallest = index;
        if (left < elements.size() && elements.get(left) < elements.get(smallest)) {
            smallest = left;
        }
        if (right < elements.size() && elements.get(right) < elements.get(smallest)) {
            smallest = right;
        }

        if (smallest != index) {
            Collections.swap(elements, smallest, index);
            maintainMin(smallest);
        }
    }

    public int min() {
        return elements.get(1);
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