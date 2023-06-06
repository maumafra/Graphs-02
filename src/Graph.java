//Alunos: Maurício Mafra Monnerat, Mauro Fialho
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public List<Vertice> listaVertices = new ArrayList<>();
    public int order;
    public String[][] matrix;

    public Graph(String[][] matrix){
        this.order = matrix.length;
        this.matrix = matrix;
        //percorre a matriz pra criar todos os vértices
        for (int row = 0; row < order; row++) {
            Vertice v = new Vertice(Utils.getCharacterAt(row), row);
            listaVertices.add(v);
        }
        //percorre a matriz pra criar as relações entre os vértices
        for (int row = 0; row < order; row++) {
            for (int column = 0; column < order; column++) {
                if (isValidEdge(matrix[row][column])){
                    listaVertices.get(row).addAdjacent(listaVertices.get(column));
                }
            }
        }
    }

    public Graph(String[][] edgesList, int graphOrder){
        this.order = graphOrder;
        for (int row = 0; row < order; row++) {
            Vertice v = new Vertice(Utils.getCharacterAt(row), row);
            listaVertices.add(v);
        }
        for (int row = 0; row < edgesList.length; row++) {
            listaVertices.get(Utils.indexOfCharacter(edgesList[row][0])).addAdjacent(listaVertices.get(Utils.indexOfCharacter(edgesList[row][1])));
        }
    }

    private boolean isValidEdge(String edgeValue) {
        if (edgeValue.equals("I")) {
            return false;
        }
        if (Integer.valueOf(edgeValue) <= 0) {
            return false;
        }
        return true;
    }
}
