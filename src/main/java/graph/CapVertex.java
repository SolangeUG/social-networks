package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a vertex (a node)
 * in a graph (social network data graph)
 * @author Solange U. Gasengayire
 *
 */
public class CapVertex {

    private int nodeId;
    private List<CapEdge> edges;

    /**
     * Create a new vertex
     * @param node the vertex number id
     */
    CapVertex(int node) {
        this.nodeId = node;
        this.edges = new ArrayList<>();
    }

    /**
     * Return this vertex number id
     * @return nodeId
     */
    int getNodeId() {
        return this.nodeId;
    }

    /**
     * Return this vertex list of connections
     * @return list of edges
     */
    List<CapEdge> getEdges() {
        return new ArrayList<>(edges);
    }

    /**
     * Return this vertex connections count
     * @return the number of this vertex connections
     */
    int getEdgeCount() {
       return edges.size();
    }

    /**
     * Add a connection to this vertex list of edges
     * @param edge the connection to be added
     * @return true if the edge was successfully added
     *         false otherwise
     */
    boolean addEdge(CapEdge edge) {
        boolean result = false;
        if (! edges.contains(edge)) {
            result = edges.add(edge);
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
     * @return a list of this node's neighbors
     */
    List<Integer> getNeighbors() {
        List<Integer> neighbors = new ArrayList<>();
        for (CapEdge edge: edges) {
            int neighbor = edge.getEndPoint();
            if (! neighbors.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }
}
