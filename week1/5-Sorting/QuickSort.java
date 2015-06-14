/**
 * Created by Deyan on 11.6.2015 ã..
 */
public class QuickSort {
    public int[] sort(int[] elements) {
        int[] sortedElements = elements.clone();
        quickSort(sortedElements, 0, sortedElements.length - 1);
        return sortedElements;
    }

    private int reorder(int[] a, int low, int high) {
        int pivot = ((int)(Math.random() * (high - low))) + low;
        int pivotValue = a[pivot];
        swap(a, high, pivot);
        int swapIndex = low;
        for (int i = low; i < high; i++) {
            if (a[i] < pivotValue) {
                swap(a, i, swapIndex);
                swapIndex++;
            }
        }
        swap(a, swapIndex, high);
        return swapIndex;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    private void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivotIndex = reorder(a, low, high);
            quickSort(a, low, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, high);
        }
    }



}
