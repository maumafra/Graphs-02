//Alunos: Maurício Mafra Monnerat, Mauro Fialho

import java.util.Map;

public class Graphs02 {
    public static void main(String[] args) {
        question1();
        question2();
    }
    public static void question1() {
        //Número de vértices
        int input1 = 7;
        //Origem Destino (OBS: Tem que ser uma letra por vertice e separado por espaço)
        String input2 = "F A";
        //Texto da matriz
        String input3 = "0 I I I I I II 0 1I I I I 4 I 0 I I I I I 3 I 01I II I 2 I 0 I II I I 3 I 02I I I I 5 I 0";

        //Professor, fiz um método pra vc colar só o texto daquela matriz dos requisitos do trabalho, aí retorna a matriz pronta já.
        //Só que esse método só vai funcionar se os valores das matrizes forem entre 0 e 9.
        //Mas se quiser colocar uma matriz feita em Java é só substituir nessa variável matrix.
        String[][] matrix = Utils.convertStringToMatrix(input3, input1, input1);
        Graph graph = new Graph(matrix);
        int[] startEndIndexes = Utils.getStartEndIndexes(input2);
        Vertice start = graph.listaVertices.get(startEndIndexes[0]);
        Vertice end = graph.listaVertices.get(startEndIndexes[1]);

        Map<String, String> dijkstraResult =  Dijkstra.findShorterPath(graph, start, end);
        System.out.println("---- Questão 1 ----");
        System.out.println("Caminho mínimo: "+dijkstraResult.get("PATH"));
        System.out.println("Custo: "+dijkstraResult.get("COST"));
    }

    public static void question2() {
        //Número de testes
        int input1 = 1;
        //Quantidade de vértices e arestas
        String input2 = "10 15";
        //Relacionamentos (Copiei dos requisitos)
        String input3 = "ACBAC BC FD AD CECE DFBFGF HH GH II JJ H";

        String[] graphOrderAndLength = Utils.getTwoValuesFromInput(input2);
        int graphOrder = Integer.parseInt(graphOrderAndLength[0]);
        int graphLength = Integer.parseInt(graphOrderAndLength[1]);
        //Da mesma forma da matriz da questão 1, to utilizando o método pra transformar em matriz,
        //mas se o prof tiver uma lista de relacionamentos já pronta na formatação do java, só
        //substituir nessa variável edgesList.
        String[][] edgesList = Utils.convertStringToMatrix(input3, graphLength, 2);
        Graph graph = new Graph(edgesList, graphOrder);
        StronglyConnectedComponents.getComponents(graph, input1);
    }
}