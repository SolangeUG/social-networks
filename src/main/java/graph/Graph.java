package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface Graph {

    /**
     * Creates a vertex with the given id number
     * @param num the vertex id
     */
    void addVertex(int num);


    /**
     * Creates an edge from a vertex to another
     * @param from the origin vertex
     * @param to the destination vertex
     */
    void addEdge(int from, int to);


    /**
     * Finds the egonet centered at a given node's id
     * @param center the center vertex id
     * @return the computed egonet
     */
    Graph getEgonet(int center);


    /**
     * Returns all strongly-connected components in a directed graph.
     * @return a list of all SCCs of the graph.
     */
    List<Graph> getSCCs();


    /**
     * Returns the graph's connections in a readable format.
     * The keys in this HashMap are the vertices in the graph.
     * The values are the nodes that are reachable via a directed
     * edge from the corresponding key.
     * The returned representation ignores edge weights and multi-edges.
     * @return a readable format of the graph's connections.
     */
    HashMap<Integer, HashSet<Integer>> exportGraph();

} 
