import java.util.HashMap;

/**
 * Created by Deyan on 11.6.2015 ã..
 */
public class CountingSort {
    public int[] sort(int[] elements, int min, int max) {
        int[] sortedElements = new int[elements.length];
        countingSort(sortedElements, elements, min, max);
        return sortedElements;
    }

    private void countingSort(int[] sorted, int[] elements, int min, int max) {
        int[] histogram = new int[max + Math.abs(min) + 1];
        for (int i = 0; i < elements.length; i++) {
            histogram[elements[i] + Math.abs(min)]++;
        }

        int index = 0;
        for (int i = 0; i < histogram.length; i++) {
            int count = histogram[i];
            for (int j = 0; j < count; j++) {
                sorted[index] = i - Math.abs(min);
                index++;
            }
        }
    }

}
