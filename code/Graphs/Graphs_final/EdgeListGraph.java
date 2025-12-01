package Graphs_final;
import LinkedList_final.DoublyLinkedList;
import LinkedList_final.PositionList;
import LinkedList_final.Position;
import Maps_final.Map;
import Maps_final.HashMapSC;
import StackQueues.LinkedStack;
import StackQueues.Stack;

public class EdgeListGraph<V, E> implements Graph<V, E>{
	private static class InnerVertex<V> implements Vertex<V>{
		V element;
		InnerVertex(V element){
			this.element = element;
		}
		public V getElement() {
			return element;
		}
	}
	
	private static class InnerEdge<E, V> implements Edge<E, V>{
		E element;
		InnerVertex<V> endpoints[];
		InnerEdge(Vertex<V> from, Vertex<V> to, E element){
			this.element = element;
			endpoints = (InnerVertex<V>[])(new InnerVertex[2]);
			endpoints[0] = (InnerVertex<V>)(from);
			endpoints[1] = (InnerVertex<V>)(to);
		}
		public E getElement() {
			return element;
		}
		public Vertex<V>[] getEndpoints() {
			return endpoints;
		}
	}
	
	DoublyLinkedList<Vertex<V>> vertices;
	DoublyLinkedList<Edge<E, V>> edges;
	int n_vertices;
	int n_edges;
	EdgeListGraph(){
		vertices = new DoublyLinkedList<Vertex<V>>();
		edges = new DoublyLinkedList<Edge<E, V>>();
	}
	
	public PositionList<Vertex<V>> vertices(){
		return vertices;
	}

	public PositionList<Edge<E, V>> edges(){
		return edges;
	}
	
	public PositionList<Edge<E, V>> outgoingEdges(Vertex<V> v){
		DoublyLinkedList<Edge<E, V>> outgoing = new DoublyLinkedList<Edge<E, V>>();
		Position<Edge<E, V>> current_pos = edges.first();
		for(int i=0; i< n_edges;i++) {
			Edge<E, V> edge = current_pos.getElement(); 
			if(v == edge.getEndpoints()[0]) {
				outgoing.addLast(edge);
			}
			current_pos = edges.after(current_pos);
		}
		return outgoing;
	}
	
	public PositionList<Edge<E, V>> incomingEdges(Vertex<V> v){
		DoublyLinkedList<Edge<E, V>> incoming = new DoublyLinkedList<Edge<E, V>>();
		Position<Edge<E, V>> current_pos = edges.first();
		for(int i=0; i< n_edges;i++) {
			Edge<E, V> edge = current_pos.getElement(); 
			if(v == edge.getEndpoints()[1]) {
				incoming.addLast(edge);
			}
			current_pos = edges.after(current_pos);
		}
		return incoming;
	}
	
	public Edge<E, V> getEdge(Vertex<V> from, Vertex<V> to){
		for(Edge<E, V> edge: edges) {
			if(from == edge.getEndpoints()[0] && to == edge.getEndpoints()[1]) {
				return edge;
			}
		}
		return null;
	}
	
	public Vertex<V>[] endVertices(Edge<E, V> e){
		return e.getEndpoints();
	}
	
	public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e){
		Vertex<V>[] endpoints = e.getEndpoints();
		if(v == endpoints[0]) {
			return endpoints[1];
		} else if (v == endpoints[1]) {
			return endpoints[0];
		} else {
			return null;
		}
	}
	
	public void insertVertex(V x) {
		vertices.addLast(new InnerVertex<V>(x));
		n_vertices++;
	}
	
	public void insertEdge(Vertex<V> from, Vertex<V> to, E x){
		edges.addLast(new InnerEdge<E, V>(from, to, x));
		n_edges++;
	}
	
	public void removeEdge(Edge<E, V> e){
		Position<Edge<E, V>> current_pos = edges.first();
		for(int i=0; i< n_edges;i++) {
			Edge<E, V> edge = current_pos.getElement(); 
			if(edge == e) edges.remove(current_pos);
			current_pos = edges.after(current_pos);
		}
		n_edges--;
	}
	
	public void removeVertex(Vertex<V> v) {
		Position<Edge<E, V>> current_pos = edges.first();
		int n_edges_store = n_edges;
		for(int i=0; i<n_edges_store; i++) {
			Edge<E, V> edge = current_pos.getElement();
			if(edge.getEndpoints()[0] == v || edge.getEndpoints()[1] == v) {
				edges.remove(current_pos);
				n_edges--;
			}
			current_pos = edges.after(current_pos);
		}
		Position<Vertex<V>> current_pos_v = vertices.first();
		for(int i=0; i< n_vertices;i++) {
			Vertex<V> vert = current_pos_v.getElement(); 
			if(vert == v) vertices.remove(current_pos_v);
			current_pos_v = vertices.after(current_pos_v);
		}
		n_vertices--;
	}
	
	public int numVertices() {
		return n_vertices;
	}
	public int numEdges() {
		return n_edges;
	}
	public int outDegree(Vertex<V> v) {
		return outgoingEdges(v).size();
	}
	public int inDegree(Vertex<V> v){
		return incomingEdges(v).size();
	}
	
	public boolean depthFirstSearch(Vertex<V> start, Vertex<V> end){
		Stack<Vertex<V>> stack = new LinkedStack<Vertex<V>>();
		Map<Vertex<V>, Object> seen = new HashMapSC<Vertex<V>, Object>();
		stack.push(start);
		while (stack.size() > 0) {
			Vertex<V> next_pos = stack.pop();
			if (seen.get(next_pos) == null) {
				if (next_pos == end) return true;
				for(Edge<E, V> edge: outgoingEdges(next_pos)) {
					stack.push(edge.getEndpoints()[1]);
				}
				seen.put(next_pos, 0);
			}
		}
		return false;
	}
	
	public boolean breadthFirstSearch(Vertex<V> start, Vertex<V> end){
		Map<Vertex<V>, Object> seen = new HashMapSC<Vertex<V>, Object>();
		DoublyLinkedList<Vertex<V>> level = new DoublyLinkedList<Vertex<V>>();
		seen.put(start, 0);
		level.addLast(start);
		while (level.size() > 0) {
			DoublyLinkedList<Vertex<V>> next_level = new DoublyLinkedList<Vertex<V>>();
			for(Vertex<V> vert: level) {				
				DoublyLinkedList<Edge<E, V>> outgoing = (DoublyLinkedList<Edge<E, V>>)(outgoingEdges(vert));
				for(Edge<E, V> outgoing_edge: outgoing) {
					Vertex<V> opp = outgoing_edge.getEndpoints()[1];
					if (seen.get(opp) == null) {
						if (opp == end) return true;
						next_level.addLast(opp);
						seen.put(opp, 0);
					}
				}
			}
			level = next_level;
		}
		return false;
	}
	
}
