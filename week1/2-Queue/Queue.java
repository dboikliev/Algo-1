public class Queue {
    private Vector elements = new Vector();

    public void push(int value) {
        elements.add(value);
    }

    public int pop() throws Exception {
        int value = elements.get(0);
        elements.remove(0);
        return value;
    }

    public int peek() throws Exception {
        return elements.get(elements.size() - 1);
    }

    public int size() {
        return elements.size();
    }
}
