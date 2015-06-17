public class Stack<T> {
    private Queue<T> elements = new Queue<T>();

    public void push(T value) {
        elements.push(value);
    }

    public T pop() throws Exception {
        Queue temp = new Queue();
        while (elements.size() > 1) {
            T value = elements.pop();
            temp.push(value);
        }
        T top = elements.pop();
        elements = temp;
        return top;
    }

    public T peek() throws Exception {
        Queue temp = new Queue();
        T top = null;
        while (elements.size() > 0) {
            T value = elements.pop();
            top = value;
            temp.push(value);
        }
        elements = temp;
        return top;
    }

    public int size() {
        return elements.size();
    }
}
