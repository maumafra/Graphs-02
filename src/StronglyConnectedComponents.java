//Alunos: Maurício Mafra Monnerat, Mauro Fialho

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StronglyConnectedComponents {

    private static Graph graph;

    public static HashMap<String, String> getComponents(Graph graphParam, int tests) {
        graph = graphParam;
        HashMap<Integer, HashMap<String, String[]>> ret = new HashMap<>();
        for (int i = 0; i < tests; i++) {
            getStronglyConnectedComponents(i);
        }
        return new HashMap<String, String>();
    }

    private static HashMap<String, String[]> getStronglyConnectedComponents(int test) {
        int index = test%graph.order;
        Vertice start = graph.listaVertices.get(index);
        //PRIMEIRA DFS
        Map<String, Map<Vertice, Integer>> dfsResult = DepthFirstSearch.search(graph, start);
        //PEGAR ORDEM INVERSA DOS TEMPOS DE FECHAMENTO
        List<Vertice> revCloseTimeOrder = getReverseCloseTimeOrder(dfsResult.get("CLOSE_TIME"));
        graph.listaVertices = revCloseTimeOrder;
        //CRIAR GRAFO INVERSO
        Graph transverseGraph = graph.getTransverseGraph();
        //SEGUNDA DFS
        Map<String, Map<Vertice, Integer>> dfsResult2 = DepthFirstSearch.search(transverseGraph);
        return new HashMap<>();
    }

    private static List<Vertice> getReverseCloseTimeOrder(Map<Vertice, Integer> vertices) {
        List<Map.Entry<Vertice, Integer>> vertCloseTimeList = new ArrayList<>(vertices.entrySet());
        vertCloseTimeList.sort(Map.Entry.comparingByValue());
        List<Vertice> revCloseTimeOrder = new ArrayList<>();
        for (int i = vertCloseTimeList.size() - 1; i >= 0; i--) {
            revCloseTimeOrder.add(vertCloseTimeList.get(i).getKey());
        }
        return revCloseTimeOrder;
    }
}
