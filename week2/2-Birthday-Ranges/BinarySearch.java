import java.util.List;

public class BinarySearch {
    public int find(List<Integer> elements, int value) {
        return binarySearch(elements, value, 0, elements.size());
    }

    private int binarySearch(List<Integer> elements, int value, int left, int right) {
        if (left >= elements.size()) {
            return -1;
        }

        if (left == right && elements.get(left) == value) {
            return left;
        } else if (left == right && elements.get(left) != value) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (elements.get(mid) >= value) {
            return binarySearch(elements, value, left, mid);
        } else if (value > elements.get(mid)) {
            return binarySearch(elements, value, mid + 1, right);
        }
        return -1;
    }
}