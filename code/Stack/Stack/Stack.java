package Stack;

public interface Stack<E> {
    public abstract void push(E e);
    public abstract E pop();
    public abstract E top();
    public abstract int size();
    public abstract boolean isEmpty();

}
