/**
 * Created by Deyan on 11.6.2015 �..
 */
public class MergeSort {
    public int[] sort(int[] elements) {
        int[] sortedElements = elements.clone();
        mergeSort(sortedElements, 0, sortedElements.length - 1);
        return sortedElements;
    }

    private void mergeSort(int[] a, int left, int right) {

        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    private void merge(int[] a, int left, int mid, int right) {
        int[] l = new int[mid - left + 1];
        int[] r = new int[right - mid];

        for (int i = 0; i < l.length; i++) {
            l[i] = a[left + i];
        }

        for (int i = 0; i < r.length; i++) {
            r[i] = a[mid + i + 1];
        }

        int m = 0;
        int n = 0;
        int k = left;
        while (k < right) {
            if (m == l.length || n == r.length) {
                break;
            }

            if (l[m] <= r[n]) {
                a[k] = l[m];
                m++;
            }
            else {
                a[k] = r[n];
                n++;
            }
            k++;
        }

        while (m < l.length) {
            a[k] = l[m];
            m++;
            k++;
        }

        while (n < r.length) {
            a[k] = r[n];
            n++;
            k++;
        }
    }
}