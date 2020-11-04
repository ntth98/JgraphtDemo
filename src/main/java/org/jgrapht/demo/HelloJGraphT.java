package org.jgrapht.demo;

import org.jgrapht                                                  .Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class HelloJGraphT {
    private HelloJGraphT() {
    } // ensure non-instantiability.

    /**
     * The starting point for the demo.
     *
     * @param args ignored.
     * @throws URISyntaxException if invalid URI is constructed.
     * @throws ExportException    if graph cannot be exported.
     */
    public static void main(String[] args)
            throws URISyntaxException,
            ExportException, FileNotFoundException {

        Graph<String, DefaultEdge> simpleGraph = createSimpleGraph();
        readSimpleDirectedGraph(simpleGraph);
    }

    private static Graph<String, DefaultEdge> createSimpleGraph() {
        Graph<String, DefaultEdge> g = new SimpleDirectedGraph<>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);


        // add edges to create a circuit
        DefaultEdge e1 = g.addEdge("v1", "v2");
        // g.setEdgeWeight(e1, 5);
        //g.addEdge("James", "John", new LabelEdge("TestLabel"));
        DefaultEdge e2 = g.addEdge("v2", "v3");
        // g.setEdgeWeight(e2, 5);

        DefaultEdge e3 = g.addEdge("v3", "v4");
        //g.setEdgeWeight(e3, 5);

        DefaultEdge e4 = g.addEdge("v4", "v1");
        DefaultEdge e5 = g.addEdge("v2", "v1");
        //g.setEdgeWeight(e4, 5);

        return g;
    }

    private static void readSimpleDirectedGraph(Graph<String, DefaultEdge> g) throws FileNotFoundException {
        ArrayList<DefaultEdge> listOfEdges = new ArrayList<>();
        StringBuilder writeOutput1 = new StringBuilder();
        
        g.vertexSet().forEach(vertex ->
                Graphs.successorListOf(g, vertex)
                        .forEach(vertex2 -> listOfEdges.add(g.getEdge(vertex, vertex2))));

        for (DefaultEdge vertex : listOfEdges) {
            Matcher matcher = Pattern.compile("(..)\\s.\\s(..)", Pattern.CASE_INSENSITIVE).matcher(vertex.toString());
            if (matcher.find()) {
                String output = matcher.group(1)+"--"+matcher.group(2);
                writeOutput1.append(output).append("\n");
            }
            else { System.out.println("Match not found");
                }

        }

        try {
            FileWriter myWriter = new FileWriter("filename.gka");
            myWriter.write(writeOutput1.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
    }
    }



