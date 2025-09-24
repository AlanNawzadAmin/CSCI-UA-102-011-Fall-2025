package Stack;

import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {
    private static final int MAX_CAPACITY = 1000;
    private E[] array;
    private int top = -1;
    public ArrayStack() {this(MAX_CAPACITY);}
    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }
    public int size() {
        return top + 1;
    }

    public boolean isEmpty() { return (top == -1); }
    public void push(E e) {
        array[++top] = e;
    }

    public E top() {
        if (isEmpty())
            return null;
        return array[top];
    }

    public E pop() {
        if (isEmpty())
            return null;
        E sol = array[top];
        array[top] = null;
        top--;
        return sol;
    }
}
