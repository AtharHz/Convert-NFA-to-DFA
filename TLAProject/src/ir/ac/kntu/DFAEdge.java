package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Objects;

public class DFAEdge {

    private boolean accept;

    private ArrayList<Edge> edges;

    private ArrayList<DFAVertex> vertices;

    public DFAEdge() {
        edges=new ArrayList<>();
        vertices=new ArrayList<>();
    }

//    public ArrayList<Edge> getEdges() {
//        //deep copy
//        return edges;
//    }

    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> copy=new ArrayList<>();
        for(int i=0;i<edges.size();i++){
            copy.add(edges.get(i));
        }
        return copy;
    }


    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

//    public ArrayList<DFAVertex> getVertices() {
//        return vertices;
//    }


    public ArrayList<DFAVertex> getVertices() {
        ArrayList<DFAVertex> copy=new ArrayList<>();
        for(int i=0;i<vertices.size();i++){
            copy.add(vertices.get(i));
            //System.out.println("mmm"+vertices.get(i));
        }
        return copy;
    }

    public void setVertices(ArrayList<DFAVertex> vertices) {
        this.vertices = vertices;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public void addVertex(DFAVertex vertex){
        vertices.add(vertex);
    }

    public void renew(){
        edges.clear();
        vertices.clear();
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    @Override
    public String toString() {
        return /*"DFAEdge{" + "edges=" +*/ " "+edges /*+ ", vertices=" + vertices + '}'*/;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DFAEdge nfaEdge = (DFAEdge) o;
        return edges.equals(nfaEdge.edges) && vertices.equals(nfaEdge.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edges, vertices);
    }
}
