package Stack;

import java.util.LinkedList;



public class LinkedQueue<E> implements Queue<E> {
    private LinkedList<E> list = new LinkedList<>();
    public LinkedQueue() {}
    public int size() { return list.size(); }
    public boolean isEmpty() { return list.isEmpty(); }
    public E first() { return list.getFirst(); }
    public void enqueue(E e) { list.addLast(e); }
    public E dequeue() { return list.removeFirst(); }


    public static void main(String[] args) {
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
