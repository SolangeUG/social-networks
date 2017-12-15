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

	/**
	 * Adds a vertex with the given id number to the graph
	 * @see graph.Graph#addVertex(int)
	 * @param num the vertex id
	 */
	@Override
	public void addVertex(int num) {
		// TODO Auto-generated method stub

	}


	/**
	 * Adds an edge from a vertex to another
	 * @see graph.Graph#addEdge(int, int)
	 * @param from the origin vertex
	 * @param to the destination vertex
	 */
	@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub

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

}
