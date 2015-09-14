import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


class HeapSort {
    public static void main(String[] args) throws IOException {
        Heap heap = new Heap();
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            heap.insert(scanner.nextInt());
        }


        for (int i = 0; i < k - 1; i++) {
            heap.removeMin();
        }
        System.out.print(heap.min());
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