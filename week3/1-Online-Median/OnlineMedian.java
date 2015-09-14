import org.omg.CORBA.INTERNAL;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 6.7.2015 ..
 */
class MaxHeapComparator implements Comparator<Integer>
{
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}


public class OnlineMedian {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        OnlineMedian m = new OnlineMedian();
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
        for (int i = 0; i < n; i++) {
            writer.println(m.insert(scanner.nextInt()));
        }
        writer.close();
    }

    private Queue<Integer> maxHeap = new PriorityQueue<>(10, new MaxHeapComparator());
    private Queue<Integer> minHeap = new PriorityQueue<>();

    int size = 0;

    //inserts the number and returns the median
    public int insert(int number){
        maxHeap.add(number);
        if (size % 2 == 0) {
            if (!minHeap.isEmpty() && (maxHeap.peek() > minHeap.peek())) {
                int min = minHeap.poll();
                int max = maxHeap.poll();
                minHeap.add(max);
                maxHeap.add(min);
            }
        }
        else {
            minHeap.add(maxHeap.poll());
        }
        size++;
        return size % 2 == 0 ? minHeap.peek() : maxHeap.peek();
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