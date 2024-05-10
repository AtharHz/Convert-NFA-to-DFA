package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static void gettingInput(ArrayList<String> alphabet,ArrayList<Edge> edgesDFA){
        ArrayList<Vertex> verticesDFA =new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        alphabet.add("e");
        System.out.println("Enter the number of alphabet");
        int alphaNum=scanner.nextInt();
        System.out.println("Enter the alphabet");
        for(int t=0;t<alphaNum;t++){
            String alpha=scanner.next();
            alphabet.add(alpha);
        }
        System.out.println("Enter the number of Edges");
        int num=scanner.nextInt();
        System.out.println("Enter the name of first Edge");
        String name =scanner.next();
        System.out.println("Is it final state?\nEnter true or false");
        boolean accept =scanner.nextBoolean();
        edgesDFA.add(new Edge(name,accept));
        for(int i=0;i<num-1;i++) {
            System.out.println("Enter the name of Edge");
            name =scanner.next();
            System.out.println("Is it final state?\nEnter true or false");
            accept =scanner.nextBoolean();
            edgesDFA.add(new Edge(name,accept));
        }
        System.out.println("Enter the first vertex:");
        boolean ans=true;
        while (ans){
            System.out.println("Choose the beginning edge");
            for(int i = 0; i< edgesDFA.size(); i++){
                System.out.println(i+"."+ edgesDFA.get(i));
            }
            int number=scanner.nextInt();
            Edge chosen= edgesDFA.get(number);
            System.out.println("Enter the value");
            String val=scanner.next();
            while (!alphabet.contains(val)){
                System.out.println("Input is not from the automata's alphabet");
                System.out.println("Enter the new value");
                val=scanner.next();
            }
            System.out.println("Choose the destination edge");
            for(int i = 0; i< edgesDFA.size(); i++){
                System.out.println(i+"."+ edgesDFA.get(i));
            }
            int numberDes=scanner.nextInt();
            Edge chosenDes= edgesDFA.get(numberDes);
            Vertex vertex=new Vertex(val,chosen,chosenDes);
            if(!verticesDFA.contains(vertex)){
                verticesDFA.add(vertex);
                chosen.addVertex(vertex);
                System.out.println("Do you want to add another vertex?");
                ans=scanner.nextBoolean();
            }else{
                System.out.println("You have added this vertex before,please add a different one");
                ans=true;
            }
        }
        alphabet.remove("e");
    }


    public static ArrayList<DFAEdge> convertToDFA(){
        ArrayList<Edge> edgesNFA =new ArrayList<>();
        ArrayList<String> alphabet=new ArrayList<>();
        ArrayList<DFAEdge> dfaEdges =new ArrayList<>();
        DFAEdge dfaEdge =new DFAEdge();
        gettingInput(alphabet, edgesNFA);
        dfaEdge.addEdge(edgesNFA.get(0));
        for(int j = 0; j< edgesNFA.get(0).getVertices().size(); j++){
            if(edgesNFA.get(0).getVertices().get(j).getWeight().equals("e")){
                dfaEdge.addEdge(edgesNFA.get(0).getVertices().get(j).getDestinationEdge());
            }
        }
        dfaEdges.add(dfaEdge);
        int i=0;
        int flag;
        boolean br=false;
        while(dfaEdges.size()!=i && !br){
            flag=0;
            for(int k=0;k<alphabet.size();k++){
                DFAEdge dfaEd =new DFAEdge();
                DFAVertex dfaVertex =new DFAVertex(alphabet.get(k), dfaEdges.get(i));
                for(int j = 0; j< dfaEdges.get(i).getEdges().size(); j++) {
                    for (int t = 0; t < dfaEdges.get(i).getEdges().get(j).getVertices().size(); t++) {
                        if (dfaEdges.get(i).getEdges().get(j).getVertices().get(t).getWeight().equals(alphabet.get(k))){
                            if (!dfaEd.getEdges().contains(dfaEdges.get(i).getEdges().get(j).getVertices().get(t).getDestinationEdge())) {
                                Edge added= dfaEdges.get(i).getEdges().get(j).getVertices().get(t).getDestinationEdge();
                                dfaEd.addEdge(added);
                                if(added.isAccept()){
                                    dfaEd.setAccept(true);
                                }
                                for(int c=0;c<added.getVertices().size();c++){
                                    if(added.getVertices().get(c).getWeight().equals("e")){
                                        if(!dfaEd.getEdges().contains(added.getVertices().get(c).getDestinationEdge())) {
                                            dfaEd.addEdge(added.getVertices().get(c).getDestinationEdge());
                                            if(added.getVertices().get(c).getDestinationEdge().isAccept()){
                                                dfaEd.setAccept(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(dfaEd.getEdges().size()!=0) {
                    dfaVertex.setDestinationEdge(dfaEd);
                    dfaEdges.get(i).addVertex(dfaVertex);
                    if (!repetitious(dfaEd, dfaEdges)) {
                        dfaEdges.add(dfaEd);
                    }else {
                        flag++;
                    }
                }
                if(flag== edgesNFA.size()){
                    br=true;
                }
            }
            i++;
        }
        printDFA(dfaEdges);
        return dfaEdges;
    }

    public static void printDFA(ArrayList<DFAEdge> dfaEdges){
        for(int l = 0; l< dfaEdges.size(); l++){
            System.out.println("/////////////////////////////////////////");
            System.out.println("Edge : "+ dfaEdges.get(l).getEdges());
            System.out.println("Is it final : "+ dfaEdges.get(l).isAccept());
            for(int j = 0; j< dfaEdges.get(l).getVertices().size(); j++) {
                System.out.println(dfaEdges.get(l).getVertices().get(j));
                System.out.println("to Edge"+ dfaEdges.get(l).getVertices().get(j).getDestinationEdge());
            }
        }
    }


    public static boolean repetitious(DFAEdge dfaEdge, ArrayList<DFAEdge> dfaEdgeArrayList){
        for (int i = 0; i< dfaEdgeArrayList.size(); i++) {
            if (dfaEdgeArrayList.get(i).getEdges().size() == dfaEdge.getEdges().size()) {
                int equal=0;
                for (int j = 0; j < dfaEdge.getEdges().size(); j++) {
                    if (dfaEdgeArrayList.get(i).getEdges().contains(dfaEdge.getEdges().get(j))) {
                        equal++;
                    }
                }
                if (equal== dfaEdge.getEdges().size()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Canvas canvas=new Canvas(1200,550);
        GraphicsContext gc= canvas.getGraphicsContext2D();
        drawShapes(gc,convertToDFA(),root);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root,Color.rgb(255,228,181));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc, ArrayList<DFAEdge> dfaEdgeArrayList, Group root) {
        gc.setLineWidth(2);
        int n= dfaEdgeArrayList.size();
        gc.setFont(new Font("Arial", 18));
        for(int i = 0; i< dfaEdgeArrayList.size(); i++) {
            gc.setFill(Color.rgb(0,128,128));
            gc.fillOval(110+((1200-(20*n))/n)*i, 160, 120, 120);
            gc.setFill(Color.rgb(0,0,20));
            if(dfaEdgeArrayList.get(i).isAccept()){
                gc.strokeOval(110+((1200-(20*n))/n)*i, 160, 120, 120);
            }
            gc.fillText(dfaEdgeArrayList.get(i).getEdges().toString(), 128+((1200-20*n)/n)*i, 220,5000);
        }
        gc.setFont(new Font("Arial", 10));
        for(int i=0;i<n;i++){
            for (int j = 0; j< dfaEdgeArrayList.get(i).getVertices().size(); j++){
                for(int t=0;t<n;t++){
                    if(equalDFAEdges(dfaEdgeArrayList.get(t), dfaEdgeArrayList.get(i).getVertices().get(j).getDestinationEdge())){
                        QuadCurve c = new QuadCurve();
                        if(i>t) {
                            c.setStartX(160 + ((1200 - (20 * n)) / n) * i);
                            c.setStartY(160);
                            c.setControlX(160 + ((1200 - (20 * n)) / n) * ((t + i) / 2));
                            c.setControlY(160 - (40 * (i+1)));
                            c.setEndX(160 + ((1200 - (20 * n)) / n) * t);
                            c.setEndY(160);
                            gc.fillText(dfaEdgeArrayList.get(i).getVertices().get(j).getWeight(),160 + ((1200 - (20 * n)) / n) * ((t + i) / 2),160 - (40 * (i+1)));
                        }if(i<t){
                            c.setStartX(160 + ((1200 - (20 * n)) / n) * i);
                            c.setStartY(160+120);
                            c.setControlX(160 + ((1200 - (20 * n)) / n) * ((t + i) / 2));
                            c.setControlY(160+120 + (40 * (i+1)));
                            c.setEndX(160 + ((1200 - (20 * n)) / n) * t);
                            c.setEndY(160+120);
                            gc.fillText(dfaEdgeArrayList.get(i).getVertices().get(j).getWeight(),160 + ((1200 - (20 * n)) / n) * ((t + i) / 2),160+120 + (40 * (i+1)));
                        }else {
                            c.setStartX(160 + ((1200 - (20 * n)) / n) * i);
                            c.setStartY(160);
                            c.setControlX(170 + ((1200 - (20 * n)) / n) * i);
                            c.setControlY(120);
                            c.setEndX(175 + ((1200 - (20 * n)) / n) * i);
                            c.setEndY(160);
                            gc.fillText(dfaEdgeArrayList.get(i).getVertices().get(j).getWeight(),170 + ((1200 - (20 * n)) / n) * i,120);
                        }
                        c.setFill(Color.TRANSPARENT);
                        c.setStrokeWidth(3);
                        c.setStroke(Color.rgb(0, 0, 20));
                        root.getChildren().add(c);
                    }
                }
            }
        }
    }

    public static boolean equalDFAEdges(DFAEdge first, DFAEdge second){
        if(first.getEdges().size()!=second.getEdges().size()){
            return false;
        }else {
            for(int i=0;i<first.getEdges().size();i++){
                if(!second.getEdges().contains(first.getEdges().get(i))){
                    return false;
                }
            }
        }
        return true;
    }
}
