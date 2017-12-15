package graph;

import java.util.*;

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
		/* An egonet in a social network graph is a single user's network
		 * including their friends and friendships between them
		 */

		// This egonet should be a new (sub)graph
		Graph egonet = new CapGraph();
		egonet.addVertex(center);

		CapVertex ego = vertices.get(center);
		HashSet<Integer> neighbors = ego.getNeighbors();
		neighbors.add(center);

		// Add all the center's neighbors and the connecting edges
		for (CapEdge edge: ego.getEdges()) {
			egonet.addVertex(edge.getEndPoint());
			egonet.addEdge(center, edge.getEndPoint());
		}

		// Add all edges connecting the center's neighbors amongst themselves
		for (Integer id : neighbors) {
			CapVertex neighbor = vertices.get(id);
			for (CapEdge e: neighbor.getEdges()) {
				if (neighbors.contains(e.getEndPoint())) {
					egonet.addEdge(id, e.getEndPoint());
				}
			}
		}
		return egonet;
	}


	/**
	 * Returns all strongly connected components in a directed graph.
	 * @see graph.Graph#getSCCs()
	 * @return a list of all SCCs of the graph.
	 */
	@Override
	public List<Graph> getSCCs() {
		/*
		 *  Algorithm Overview
		 *  ******************
		 *  	1. DFS(G) keeping track of the order in which vertices finish
		 *  	2. Compute the transpose of G ==> GT
		 *  	3. DFS(GT) exploring in the reverse order of finish time from step 1
		 *  	4. Construct the list of strongly connected components
		 */

		// Step 1: run DFS on the graph
		Stack<CapVertex> toExplore = new Stack<>();
		toExplore.addAll(vertices.values());
		Stack<CapVertex> finished = this.depthFirstSearch(toExplore);

		// Step 2: compute the transpose of the graph
		Graph transpose = computeTranspose();

		// Step 3: run DFS on the transpose of the graph
		Stack<CapVertex> toExploreT = new Stack<>();
		for (CapVertex node: finished) {
			CapVertex vertex = ((CapGraph) transpose).getVertices().get(node.getNodeId());
			toExploreT.add(vertex);
		}
		Stack<CapVertex> finishedT = ((CapGraph) transpose).depthFirstSearch(toExploreT);

		// Step 4: construct the list of strongly connected components
		List<Graph> components;
		components = constructComponents(transpose, finishedT);
		return components;
	}


	/**
	 * Export this graph as a HashMap where:
	 *  - the keys are all the vertices in the graph
	 *  - the values are the set of vertices that are reachable via a directed
	 * 	  edge from the corresponding key.
	 * The returned representation ignores edge weights and multi-edges.
	 * @see graph.Graph#exportGraph()
	 * @return a readable format of the graph's connections.
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		HashMap<Integer, HashSet<Integer>> export = new HashMap<>();
		for (CapVertex vertex: vertices.values()) {
			HashSet<Integer> neighbors = new HashSet<>(vertex.getNeighbors());
			export.put(vertex.getNodeId(), neighbors);
		}
		return export;
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

	/**
	 * Run the DFS algorithm on the graph with the aim of:
	 * 	- visiting all the vertices of the graph
	 * 	- keeping track of the order in which we finish exploring
	 * 	  the graph from each particular node
	 * @param toExplore a stack of vertices to explore
	 * @return the stack keeping track of the order of visited vertices
	 */
	private Stack<CapVertex> depthFirstSearch(Stack<CapVertex> toExplore) {
		Stack<CapVertex> finished = new Stack<>();
		Set<CapVertex> visited = new HashSet<>();

		while (! toExplore.isEmpty()) {
			CapVertex vertex = toExplore.pop();
			if (! visited.contains(vertex)) {
				dfsVisit(vertex, visited, finished);
			}
		}
		return finished;
	}

	/**
	 * This method explores the graph from a given node
	 * @param vertex the start node
	 * @param visited a set of nodes that are already visited
	 * @param finished a stack to keep track of the order in which vertices finish
	 */
	private void dfsVisit(CapVertex vertex, Set<CapVertex> visited,
						  Stack<CapVertex> finished) {
		visited.add(vertex);

		for (CapVertex node: getNeighbors(vertex)) {
			if (! visited.contains(node)) {
				dfsVisit(node, visited, finished);
			}
		}

		finished.push(vertex);
	}

	/**
	 * Return the set of a given vertex neighbors in the graph
	 * @param vertex the node to explore from
	 * @return the set of its vertex neighbors
	 */
	private HashSet<CapVertex> getNeighbors(CapVertex vertex) {
		HashSet<CapVertex> neighbors = new HashSet<>();
		for (Integer id: vertex.getNeighbors()) {
			neighbors.add(vertices.get(id));
		}
		return neighbors;
	}

	/**
	 * Compute the transpose of this (directed) graph
	 * ===> the transpose holds the same vertices and edges as the original graph
	 * ===> the edges are "flipped", i.e: the ege direction is reversed
	 * @return the transpose of the graph
	 */
	private Graph computeTranspose() {
		Graph transpose = new CapGraph();
		for (CapVertex vertex: vertices.values()) {
			transpose.addVertex(vertex.getNodeId());
			for (Integer from: vertex.getNeighbors()) {
				transpose.addVertex(from);
				transpose.addEdge(from, vertex.getNodeId());
			}
		}
		return transpose;
	}

	/**
	 * From a given graph, and a stack of vertices from the graph,
	 * construct a list of their strongly connected components
	 * @param graph the graph being explored
	 * @param toExplore the stack that kept kept track of the order in which nodes finished
	 * @return the list of strongly connected components
	 */
	private List<Graph> constructComponents(Graph graph, Stack<CapVertex> toExplore) {
		List<Graph> components = new ArrayList<>();

		Set<CapVertex> visited = new HashSet<>();
		for (CapVertex vertex: toExplore) {

			if (! visited.contains(vertex)) {
				Stack<CapVertex> finished = new Stack<>();
				((CapGraph) graph).dfsVisit(vertex, visited, finished);

				Graph component = new CapGraph();
				for (CapVertex node: finished) {
					component.addVertex(node.getNodeId());
				}
				components.add(component);
			}
		}

		return components;
	}

}
