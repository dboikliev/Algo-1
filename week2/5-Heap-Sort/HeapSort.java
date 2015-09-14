import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 18.6.2015 ..
 */
public class HeapSort {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        HeapSort sort = new HeapSort();
        sort.sort(nums);
        for (int i = 0; i < n - 1; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.print(nums[n - 1]);
    }

    public void sort(int[] sequence) {
        Heap heap = new Heap();
        for (int i : sequence) {
            heap.insert(i);
        }

        for (int i = sequence.length - 1; i >= 0; i--) {
            sequence[i] = heap.max();
            heap.removeMax();
        }
    }

    public void sort(List<Integer> sequence) {
        Heap heap = new Heap();
        for (int i : sequence) {
            heap.insert(i);
        }

        for (int i = sequence.size() - 1; i >= 0; i--) {
            sequence.set(i, heap.max());
            heap.removeMax();
        }
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
        while (index > 1 && elements.get(parent(index)) < elements.get(index)) {
            Collections.swap(elements, index, parent(index));
            index = parent(index);
        }
        currentIndex++;
    }

    public void removeMax() {
        if (size() <= 0) {
            return;
        }

        Collections.swap(elements, 1, elements.size() - 1);
        elements.remove(elements.size() - 1);
        maintainMax(1);
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

    private void maintainMax(int index) {
        int left = left(index);
        int right = right(index);
        int largest = index;
        if (left < elements.size() && elements.get(left) > elements.get(largest)) {
            largest = left;
        }
        if (right < elements.size() && elements.get(right) > elements.get(largest)) {
            largest = right;
        }

        if (largest != index) {
            Collections.swap(elements, largest, index);
            maintainMax(largest);
        }

    }

    public int max() {
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