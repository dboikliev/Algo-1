public class Stack {
    private Queue elements = new Queue();

    public void push(int value) {
        elements.push(value);
    }

    public int pop() throws Exception {
        Queue temp = new Queue();
        while (elements.size() > 1) {
            int value = elements.pop();
            temp.push(value);
        }
        int top = elements.pop();
        elements = temp;
        return top;
    }

    public int peek() throws Exception {
        Queue temp = new Queue();
        int top = 0;
        while (elements.size() > 0) {
            int value = elements.pop();
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
