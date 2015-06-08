public class Vector {
    int initialCapacity = 2;
    int lastElementIndex = -1;
    int[] elements = new int[initialCapacity];

    public void insert(int index, int element) {
        if (lastElementIndex + 1 == elements.length) {
            increaseCapacity();
        }

        for (int i = lastElementIndex; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
    }

    private void increaseCapacity() {
        int[] expandedArray = new int[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            expandedArray[i] = elements[i];
        }
        elements = expandedArray;
    }

    public void add(int element) {
        if (lastElementIndex + 1 == elements.length) {
            increaseCapacity();
        }
        lastElementIndex++;
        elements[lastElementIndex] = element;
    }

    public int get(int index) throws Exception {
        if (index > lastElementIndex) {
            throw new Exception("Out of vector bounds");
        }

        return elements[index];
    }

    public void remove(int index) throws Exception {
        if (index < 0 || index > lastElementIndex) {
            throw new Exception("Out of vector bounds");
        }
        if (index <= lastElementIndex) {
            for (int i = index; i < lastElementIndex; i++) {
                elements[i] = elements[i + 1];
            }
            lastElementIndex--;
        }

        if (lastElementIndex < elements.length / 2) {
            decreaseCapacity();
        }
    }

    private void decreaseCapacity() {
        int[] shrinkedArray = new int[elements.length / 2];
        for (int i = 0; i < shrinkedArray.length; i++) {
            shrinkedArray[i] = elements[i];
        }
        elements = shrinkedArray;
    }


    public int pop() throws Exception {
        if (lastElementIndex - 1 < 0) {
            throw new Exception("The vector is empty");
        }
        int lastElement = elements[lastElementIndex];
        lastElementIndex--;
        return lastElement;
    }

    public int size() {
        return lastElementIndex + 1;
    }

    public int capacity() {
        return elements.length;
    }
}
