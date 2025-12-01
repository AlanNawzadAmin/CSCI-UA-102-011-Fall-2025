package Graphs_final;

public interface Edge<E, V> {
	E getElement();
	Vertex<V>[] getEndpoints();
}
