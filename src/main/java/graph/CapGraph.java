package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Graph implementation
 * @author Solange U. Gasengayire
 *
 */
public class CapGraph implements Graph {

	private HashMap<Integer, CapVertex> vertices;

	/**
	 * Create a new empty graph
	 */
	public CapGraph() {
		this.vertices = new HashMap<>();
	}

	/**
	 * Adds a vertex with the given id number to the graph
	 * @see graph.Graph#addVertex(int)
	 * @param num the vertex id
	 */
	@Override
	public void addVertex(int num) {
		if (! vertices.containsKey(num)) {
			CapVertex vertex = new CapVertex(num);
			vertices.put(num, vertex);
		}
	}


	/**
	 * Adds an edge from a vertex to another
	 * @see graph.Graph#addEdge(int, int)
	 * @param from the origin vertex
	 * @param to the destination vertex
	 */
	@Override
	public void addEdge(int from, int to) {
		CapVertex start = vertices.get(from);
		if (start != null) {
			CapEdge edge = new CapEdge(from, to);
			start.addEdge(edge);
		}
	}


	/**
	 * Finds the egonet centered at a given node's id
	 * @see graph.Graph#getEgonet(int)
	 * @param center the center vertex id
	 * @return the computed egonet
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Returns all strongly-connected components in a directed graph.
	 * @see graph.Graph#getSCCs()
	 * @return a list of all SCCs of the graph.
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Returns the graph's connections in a readable format.
	 * The keys in this HashMap are the vertices in the graph.
	 * The values are the nodes that are reachable via a directed
	 * edge from the corresponding key.
	 * The returned representation ignores edge weights and multi-edges.
	 * @see graph.Graph#exportGraph()
	 * @return a readable format of the graph's connections.
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return the number of vertices in this graph
	 * NB: this method is for JUnit testing purposes only
	 * @return vertex count
	 */
	int getVertexCount() {
		return vertices.size();
	}

	/**
	 * Return the number of edges in this graph
	 * NB: this method is for JUnit testing purposes only
	 * @return edge count
	 */
	int getEdgeCount() {
		int result = 0;
		for (CapVertex vertex : vertices.values()) {
			result += vertex.getEdgeCount();
		}
		return result;
	}

	/**
	 * Return this graph vertices
	 * NB : this is a utility method (for JUnit testing purposes)
	 * @return vertices
	 */
	HashMap<Integer, CapVertex> getVertices() {
		return new HashMap<>(vertices);
	}

}
