package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Objects;

public class Edge {

    private String name;

    private ArrayList<Vertex> vertices;

    private boolean accept;

    public Edge(String name, boolean accept) {
        vertices=new ArrayList<>();
        this.name = name;
        this.accept = accept;
    }

    /*public Edge(String name, boolean accept) {
        this.name = name;
        this.accept = accept;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Vertex> getVertices() {
        //deep copy
        return vertices;
    }

//    public ArrayList<Vertex> getVertices() {
//        ArrayList<Vertex> copy=new ArrayList<>();
//        for(int i=0;i<vertices.size();i++){
//            copy.add(vertices.get(0));
//        }
//        return copy;
//    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public void addVertex(Vertex vertex){
        vertices.add(vertex);
    }

    @Override
    public String toString() {
        return name + " ";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return accept == edge.accept && name.equals(edge.name) && vertices.equals(edge.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vertices, accept);
    }
}
