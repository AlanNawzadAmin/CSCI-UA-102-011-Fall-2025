package Stack;

import java.util.LinkedList;

public class LinkedStack<E> implements Stack<E> {
    private LinkedList<E> list = new LinkedList<>();
    public LinkedStack() {}
    public int size() { return list.size(); }
    public boolean isEmpty() { return list.isEmpty(); }
    public void push(E e) {list.addFirst(e);}
    public E top() {return list.getFirst();}
    public E pop() { return list.removeFirst();}

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}


