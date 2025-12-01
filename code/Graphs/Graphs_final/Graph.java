package Graphs_final;
import LinkedList_final.PositionList;

public interface Graph<V, E> {
	PositionList<Vertex<V>> vertices();
	PositionList<Edge<E, V>> edges();
	
	PositionList<Edge<E, V>> outgoingEdges(Vertex<V> v);
	PositionList<Edge<E, V>> incomingEdges(Vertex<V> v);
	
	Edge<E, V> getEdge(Vertex<V> from, Vertex<V> to);
	Vertex<V>[] endVertices(Edge<E, V> e);
	Vertex<V> opposite(Vertex<V> v, Edge<E, V> e);
	
	void insertVertex(V x);
	void insertEdge(Vertex<V> from, Vertex<V> to, E x);
	void removeVertex(Vertex<V> v);
	void removeEdge(Edge<E, V> e);
	
	int numVertices();
	int numEdges();
	int outDegree(Vertex<V> v);
	int inDegree(Vertex<V> v);
}
