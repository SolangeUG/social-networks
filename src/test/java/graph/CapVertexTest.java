package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is a JUnit test class for the
 * @link graph.CapVertex CapVertex class
 * @author Solange U. Gasengayire
 *
 */
class CapVertexTest {

    private CapVertex node;

    @BeforeEach
    void setUp() {
        node = new CapVertex(4);
    }

    @Test
    void nodeIdValue() {
        assertAll("nodeId value",
                () -> assertNotNull(node.getNodeId()),
                () -> assertEquals(4, node.getNodeId()),
                () -> assertNotSame(2, node.getNodeId())
        );
    }

    @Test
    void addEdge() {
        CapEdge edge = new CapEdge(4, 2);
        assertAll("add edge",
                () -> assertTrue(node.addEdge(edge)),
                () -> assertFalse(node.addEdge(edge)),
                () -> assertEquals(4, edge.getStartPoint()),
                () -> assertEquals(1, node.getEdgeCount())
        );
    }

    @Test
    void edgeCount() {
        for (int i = 0; i < 12; i += 2) {
            CapEdge edge = new CapEdge(4, i);
            node.addEdge(edge);
        }
        assertAll("edge count",
                () -> assertEquals(6, node.getEdgeCount()),
                () -> assertEquals(6, node.getNeighbors().size())
        );
    }

    @Test
    void edgesList() {
        for (int i = 1; i < 12; i += 2) {
            CapEdge edge = new CapEdge(4, i);
            node.addEdge(edge);
        }

        int size = node.getEdges().size();
        List<Integer> ids = Arrays.asList(1, 3, 5, 7, 9, 11);
        for (int j = 0; j < size; j++) {
            CapEdge capEdge = node.getEdges().get(j);
            assertTrue(ids.contains(capEdge.getEndPoint()));
        }
    }

    @Test
    void stringValue() {
        assertAll("string value",
                () -> assertNotNull(node.toString()),
                () -> assertNotSame("", node.toString()),
                () -> assertEquals(String.valueOf(4), node.toString())
        );
    }
}
