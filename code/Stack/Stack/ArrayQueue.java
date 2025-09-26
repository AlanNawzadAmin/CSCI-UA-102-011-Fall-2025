package Stack;

public class ArrayQueue<E> implements Queue<E> {
    private E[] array;
    private int front = 0;
    private int size = 0;

    public ArrayQueue() {this(1000);}

    public ArrayQueue(int capacity) {
        array = (E[]) new Object[capacity];
    }

    public int size() { return size;}
    public boolean isEmpty() { return size == 0;}
    public void enqueue(E e) throws IllegalStateException {
        if (size == array.length) throw new IllegalStateException("Queue is full");
        int avail = (front + size) % array.length;
        array[avail] = e;
        size++;
    }

    public E first() {
        if (isEmpty()) return null;
        return array[front];
    }

    public E dequeue() {
        if (isEmpty()) return null;
        E answer = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        return answer;
    }
}
