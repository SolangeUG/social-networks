package graph;

/**
 * This class represents an edge (a connection)
 * in a graph (social network data graph)
 * @author Solange U. Gaengayire
 *
 */
public class CapEdge {

    private int startPoint;
    private int endPoint;

    /**
     * Create a new edge (connection)
     * @param start the starting point of the edge
     * @param end the end point of the edge
     */
    CapEdge(int start, int end) {
        this.startPoint = start;
        this.endPoint = end;
    }

    /**
     * Return this connection starting point
     * @return startPoint
     */
    int getStartPoint() {
        return this.startPoint;
    }

    /**
     * Return this connection end point
     * @return endPoint
     */
    int getEndPoint() {
        return this.endPoint;
    }

    /**
     * Return the string representation of this connection
     * @return this edge string representation
     */
    @Override
    public String toString() {
        String result = "[ " + startPoint;
        result = result + " ---> " + endPoint;
        result = result + " ]";
        return result;
    }

    /**
     * Indicates whether some other object is equel to this connection
     * @param obj some other object
     * @return true if this connection equls to obj
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

        CapEdge other = (CapEdge) obj;
        return endPoint == other.endPoint
                && startPoint == other.startPoint;
    }

    /**
     * Return a hash code value for this connection.
     * This method is supported for the benefit of hash tables
     * such as those provided by
     * @see java.util.HashMap
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + endPoint;
        result = prime * result + startPoint;
        return result;
    }
}
