package ir.ac.kntu;

import java.util.Objects;

public class Vertex {

    private String weight;

    private Edge beginningEdge;

    private Edge destinationEdge;

    public Vertex(String weight, Edge beginningEdge, Edge destinationEdge) {
        this.weight = weight;
        this.beginningEdge = beginningEdge;
        this.destinationEdge = destinationEdge;
    }

    public Vertex() {
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Edge getBeginningEdge() {
        return beginningEdge;
    }

    public void setBeginningEdge(Edge beginningEdge) {
        this.beginningEdge = beginningEdge;
    }

    public Edge getDestinationEdge() {
        return destinationEdge;
    }

    public void setDestinationEdge(Edge destinationEdge) {
        this.destinationEdge = destinationEdge;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "weight='" + weight + '\'' +
                ", beginningEdge=" + beginningEdge +
                ", destinationEdge=" + destinationEdge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return weight.equals(vertex.weight) && beginningEdge.equals(vertex.beginningEdge) && destinationEdge.equals(vertex.destinationEdge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, beginningEdge, destinationEdge);
    }
}
