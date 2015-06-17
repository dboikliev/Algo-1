public class Vector<T> {
    int initialCapacity = 2;
    int lastElementIndex = -1;
    T[] elements = (T[])new Object[initialCapacity];

    public void insert(int index, T element) {
        if (lastElementIndex + 1 == elements.length) {
            increaseCapacity();
        }

        for (int i = lastElementIndex; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
    }

    private void increaseCapacity() {
        T[] expandedArray = (T[])new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            expandedArray[i] = elements[i];
        }
        elements = expandedArray;
    }

    public void add(T element) {
        if (lastElementIndex + 1 == elements.length) {
            increaseCapacity();
        }
        lastElementIndex++;
        elements[lastElementIndex] = element;
    }

    public T get(int index) throws Exception {
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
        T[] shrinkedArray = (T[])new Object[elements.length / 2];
        for (int i = 0; i < shrinkedArray.length; i++) {
            shrinkedArray[i] = elements[i];
        }
        elements = shrinkedArray;
    }


    public T pop() throws Exception {
        if (lastElementIndex - 1 < 0) {
            throw new Exception("The vector is empty");
        }
        T lastElement = elements[lastElementIndex];
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
