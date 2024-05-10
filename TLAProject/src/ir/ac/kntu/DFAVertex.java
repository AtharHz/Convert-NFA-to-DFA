package ir.ac.kntu;

import java.util.Objects;

public class DFAVertex {

    private String weight;

    private DFAEdge beginningEdges;

    private DFAEdge destinationEdges;

    public DFAVertex(String weight, DFAEdge beginningEdge, DFAEdge destinationEdge) {
        this.weight = weight;
        this.beginningEdges = beginningEdge;
        this.destinationEdges = destinationEdge;
    }

    public DFAVertex(String weight, DFAEdge beginningEdg) {
        this.weight = weight;
        this.beginningEdges = beginningEdg;
    }

    public DFAVertex() {
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public DFAEdge getBeginningEdge() {
        return beginningEdges;
    }

    public void setBeginningEdge(DFAEdge beginningEdge) {
        this.beginningEdges = beginningEdge;
    }

    public DFAEdge getDestinationEdge() {
        return destinationEdges;
    }

    public void setDestinationEdge(DFAEdge destinationEdge) {
        this.destinationEdges = destinationEdge;
    }

    public void addDestinationEdge(Edge destinationEdge) {
        destinationEdges.addEdge(destinationEdge);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DFAVertex nfaVertex = (DFAVertex) o;
        return weight.equals(nfaVertex.weight) && beginningEdges.equals(nfaVertex.beginningEdges) && destinationEdges.equals(nfaVertex.destinationEdges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, beginningEdges, destinationEdges);
    }

    @Override
    public String toString() {
        return "NFA Vertex" + " weight='" + weight + '\''
                /* +", beginningEdge=" + beginningEdges +
                ", destinationEdge=" + destinationEdges +
                '}'*/;
    }
}
