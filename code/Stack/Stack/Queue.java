package Stack;

public interface Queue<E> {
    public abstract int size();
    public abstract boolean isEmpty();
    public abstract void enqueue(E e);
    public abstract E dequeue();
    public abstract E first();
}
