public class Queue<T> {
    private Vector<T> elements = new Vector<T>();

    public void push(T value) {
        elements.add(value);
    }

    public T pop() throws Exception {
        T value = elements.get(0);
        elements.remove(0);
        return value;
    }

    public T peek() throws Exception {
        return elements.get(elements.size() - 1);
    }

    public int size() {
        return elements.size();
    }
}
