/**
 * Created by Deyan on 11.6.2015 ã..
 */
public class InsertionSort {
    public int[] sort(int[] elements) {
        int[] sortedElements = elements.clone();

        for (int i = 1; i < sortedElements.length; i++) {
            for (int j = i; j > 0 && sortedElements[j] < sortedElements[j - 1]; j--) {
                int temp = sortedElements[j];
                sortedElements[j] = sortedElements[j - 1];
                sortedElements[j - 1] = temp;
            }
        }

        return sortedElements;
    }
}
