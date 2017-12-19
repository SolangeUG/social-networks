package graph;

import java.util.HashSet;

/**
 * This class represents a vertex (a node)
 * in a graph (social network data graph)
 * @author Solange U. Gasengayire
 *
 */
public class CapVertex {

    private int nodeId;
    private HashSet<CapEdge> edges;
    private HashSet<Integer> neighbors;

    /**
     * Create a new vertex
     * @param node the vertex number id
     */
    CapVertex(int node) {
        this.nodeId = node;
        this.edges = new HashSet<>();
        this.neighbors = new HashSet<>();
    }

    /**
     * Return this vertex number id
     * @return nodeId
     */
    int getNodeId() {
        return this.nodeId;
    }

    /**
     * Return this vertex set of connections
     * @return set of edges
     */
    HashSet<CapEdge> getEdges() {
        return new HashSet<>(edges);
    }

    /**
     * Return this vertex connections count
     * @return the number of this vertex connections
     */
    int getEdgeCount() {
       return edges.size();
    }

    /**
     * Add a connection to this vertex set of edges
     * @param edge the connection to be added
     * @return true if the edge was successfully added
     *         false otherwise
     */
    boolean addEdge(CapEdge edge) {
        boolean result = false;
        if (edge != null) {
            result = edges.add(edge);
        }
        if (result) {
            neighbors.add(edge.getEndPoint());
        }
        return result;
    }

    /**
     * Return the string representation of this vertex
     * @return this node as a string
     */
    @Override
    public String toString() {
        return String.valueOf(nodeId);
    }

    /**
     * Return all the vertices neighbors to this one
     * @return a set of this node's neighbors
     */
    HashSet<Integer> getNeighbors() {
        return new HashSet<>(neighbors);
    }

    /**
     * Indicate whether some other object is equal to this vertex
     * @param obj some other object
     * @return true if this connection equals to obj
     *         false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        CapVertex other = (CapVertex) obj;
        return this.nodeId == other.nodeId;
    }

    /**
     * Return a hash code value for this vertex.
     * This method is supported for the benefit of hash tables
     * such as those provided by
     * @see java.util.HashMap
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + nodeId;
        return result;
    }
}
