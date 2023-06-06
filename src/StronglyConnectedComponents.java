//Alunos: Maurício Mafra Monnerat, Mauro Fialho

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StronglyConnectedComponents {

    private static Graph graph;

    public static List<Map<String, String>> getComponents(Graph graphParam, int tests) {
        List<Map<String, String>> ret = new ArrayList<>();
        for (int i = 0; i < tests; i++) {
            graph = graphParam.clone();
            Map<String, String> result = getResult(i, getStronglyConnectedComponents(i));
            ret.add(result);
        }
        return ret;
    }

    private static List<List<Vertice>> getStronglyConnectedComponents(int test) {
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
        //GERAR COMPONENTES
        List<List<Vertice>> components = generateComponents(dfsResult2);
        return components;
    }

    private static HashMap<String, String> getResult(int test, List<List<Vertice>> components) {
        HashMap<String, String> ret = new HashMap<>();
        ret.put("TEST", String.valueOf(test+1));
        ret.put("COMPONENTS_LENGTH", String.valueOf(components.size()));
        String componentsString = "";
        for (List<Vertice> component : components) {
            componentsString += "{";
            for (Vertice v : component) {
                componentsString += v.verticeName;
                if (component.indexOf(v) == component.size()-1) {
                   componentsString += "}\n";
                } else {
                    componentsString += ",";
                }
            }
        }
        ret.put("COMPONENTS", componentsString);
        return ret;
    }

    private static List<List<Vertice>> generateComponents(Map<String, Map<Vertice, Integer>> result) {
        List<List<Vertice>> components = new ArrayList<>();

        Map<Vertice, Integer> ctMap = result.get("CLOSE_TIME");
        Map<Vertice, Integer> vtMap = result.get("VISIT_TIME");

        List<Map.Entry<Vertice, Integer>> ct = new ArrayList<>(ctMap.entrySet());
        ct.sort(Map.Entry.comparingByValue());
        List<Map.Entry<Vertice, Integer>> vt = new ArrayList<>(vtMap.entrySet());
        vt.sort(Map.Entry.comparingByValue());

        Vertice v;
        Vertice vStart = null;
        List<Vertice> component = new ArrayList<>();
        for (int i = 1; i <= (graph.order*2); i++) {
            v = find(i, ct, vt);
            if (vStart == v) {
                components.add(component);
                component = new ArrayList<>();
                vStart = null;
            } else {
                if (!component.contains(v)) {
                    component.add(v);
                }
                if (vStart == null) {
                    vStart = v;
                }
            }
        }
        return components;

    }

    private static Vertice find(int time, List<Map.Entry<Vertice, Integer>> ct, List<Map.Entry<Vertice, Integer>> vt) {
        Vertice ret = null;
        for (Map.Entry<Vertice, Integer> e : ct) {
            if (e.getValue() == time) {
                ret = e.getKey();
            }
        }
        if (ret != null) return ret;
        for (Map.Entry<Vertice, Integer> e : vt) {
            if (e.getValue() == time) {
                ret = e.getKey();
            }
        }
        return ret;
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
