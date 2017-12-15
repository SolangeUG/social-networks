package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.GraphLoader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is a JUnit test class for the
 * @link graph.CapGraph CapGraph class
 * @author Solange U. Gasengayire
 */
class CapGraphTest {

    private CapGraph unitGraph;
    private CapGraph smallGraph;
    private CapGraph ucsdGraph;
    private CapGraph twitterGraph;
    private CapGraph sccGraph;
    private CapGraph f1000Graph;
    private CapGraph f2000Graph;

    @BeforeEach
    void setUp() {
        // simple graph
        unitGraph = new CapGraph();
        // small data graph
        smallGraph = new CapGraph();
        GraphLoader.loadGraph(smallGraph, "data/small_test_graph.txt");

        // facebook 1000 graph
        f1000Graph = new CapGraph();
        GraphLoader.loadGraph(f1000Graph, "data/facebook_1000.txt");
        // facebook 2000 graph
        f2000Graph = new CapGraph();
        GraphLoader.loadGraph(f2000Graph, "data/facebook_2000.txt");

        // facebook UCSD graph
        ucsdGraph = new CapGraph();
        GraphLoader.loadGraph(ucsdGraph, "data/facebook_ucsd.txt");
        // twitter Higgs graph
        twitterGraph = new CapGraph();
        GraphLoader.loadGraph(twitterGraph, "data/twitter_higgs.txt");

        // testing strongly connected components
        sccGraph = new CapGraph();
        GraphLoader.loadGraph(sccGraph, "data/scc/example.txt");
    }

    @Test
    @DisplayName("Test adding vertices to a graph")
    void addVertex() {
        // the same node shouldn't be added to the graph multiple times
        unitGraph.addVertex(2);
        unitGraph.addVertex(2);
        assertEquals(1, unitGraph.getVertexCount());

        // testing a small, a medium and a large graph
        assertAll("small, medium and large graph vertex count",
                () -> assertEquals(14, smallGraph.getVertexCount()),
                () -> assertEquals(783, f1000Graph.getVertexCount()),
                () -> assertEquals(1780, f2000Graph.getVertexCount()),
                () -> assertEquals(14948, ucsdGraph.getVertexCount())
        );
    }

    @Test
    @DisplayName("Test adding edges to a graph")
    void addEdge() {
        unitGraph.addVertex(2);
        unitGraph.addVertex(4);

        unitGraph.addEdge(2, 4);
        assertEquals(1, unitGraph.getEdgeCount());
        unitGraph.addEdge(4, 2);
        assertEquals(2, unitGraph.getEdgeCount());

        // the same edge shouldn't be added to the graph multiple times
        unitGraph.addEdge(4, 2);
        assertEquals(2, unitGraph.getEdgeCount());

        // testing a small, a large undirected and a large directed graph
        assertAll("small, large undirected and large directed graph edge count",
                () -> assertEquals(34, smallGraph.getEdgeCount()),
                () -> assertEquals(886442, ucsdGraph.getEdgeCount()),
                () -> assertEquals(328132, twitterGraph.getEdgeCount())
        );
    }

    @Test
    @DisplayName("Test retrieving an egonet from a graph")
    void egonet() {
        CapGraph egonet = (CapGraph) smallGraph.getEgonet(4);
        assertAll("extract an egonet from a small graph",
                () -> assertNotNull(egonet),
                () -> assertEquals(6, egonet.getEdgeCount()),
                () -> assertEquals(3, egonet.getVertexCount())
        );

        // testing a large graph
        CapGraph net = (CapGraph) ucsdGraph.getEgonet(0);
        assertNotNull(net);
    }

    @Test
    @DisplayName("Test exporting the graph")
    void exportGraph() {
        // testing a small graph
        HashMap<Integer, HashSet<Integer>> small = smallGraph.exportGraph();
        assertNotNull(small);
        assertEquals(14, small.size());

        // testing a large graph
        HashMap<Integer, HashSet<Integer>> large = ucsdGraph.exportGraph();
        assertNotNull(large);
    }

    @Test
    @DisplayName("Test the string representation of the graph")
    void stringValue() {
        String small = smallGraph.toString();
        String large = ucsdGraph.toString();

        assertAll("string value of a small and a large graph",
                () -> assertNotNull(small),
                () -> assertNotSame("", small),
                () -> assertNotNull(large),
                () -> assertNotSame("", large)
        );
    }

    @Test
    @DisplayName("Test extracting strongly connected components from a graph")
    void stronglyConnectedComponents() {

        /* Strongly connected components only make sense on directed graphs.
         * A strongly connected graph is a directed graphh in which for all pairs
         * of nodes u and v, there is a path in both directions between u and v.
         */

        List<Graph> components = sccGraph.getSCCs();
        assertNotNull(components);
        assertEquals(4, components.size());

        CapGraph component = (CapGraph) components.get(0);
        CapGraph graph = new CapGraph();
        int count = 0;
        for (CapVertex node: component.getVertices().values()) {
            count++;
            graph.addVertex(node.getNodeId());
        }
        System.out.printf("SCC - graph node count = %s\n", count);

        HashMap<Integer, HashSet<Integer>> graphExp = graph.exportGraph();
        HashMap<Integer, HashSet<Integer>> compExp = component.exportGraph();
        System.out.println(graphExp);
        System.out.println(compExp);
    }
}
