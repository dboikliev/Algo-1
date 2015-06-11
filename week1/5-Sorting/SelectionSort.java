/**
 * Created by Deyan on 11.6.2015 ã..
 */
public class SelectionSort {
    public int[] sort(int[] elements) {
        int[] sortedElements = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            sortedElements[i] = elements[i];
        }

        for (int i = 0; i < sortedElements.length; i++) {
            int min = sortedElements[i];
            int lastIndex = i;
            for (int j = i; j < sortedElements.length; j++) {
                if (sortedElements[j] < min) {
                    min = sortedElements[j];
                    lastIndex = j;
                }
            }
            sortedElements[lastIndex] = sortedElements[i];
            sortedElements[i] = min;
        }
        return sortedElements;
    }
}
