//Alunos: Maurício Mafra Monnerat, Mauro Fialho
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthFirstSearch {
    private static Graph graph;
    private static int[] visitTime;
    private static int[] closeTime;
    private static int time;

    public static Map<String, Map<Vertice, Integer>> search(Graph graphParam, Vertice start) {
        graph = graphParam;
        visitTime = new int[graph.order];
        closeTime = new int[graph.order];
        List<Vertice> vertices = new ArrayList<>();
        vertices.addAll(graphParam.listaVertices);
        vertices.remove(start);
        vertices.add(0, start);
        depthFirstSearch(vertices);
        return getResult(vertices);
    }

    public static Map<String, Map<Vertice, Integer>> search(Graph graphParam) {
        graph = graphParam;
        visitTime = new int[graph.order];
        closeTime = new int[graph.order];
        depthFirstSearch();
        return getResult();
    }

    private static Map<String, Map<Vertice, Integer>> getResult(List<Vertice> vertices) {
        Map<String, Map<Vertice, Integer>> ret = new HashMap<>();
        Map<Vertice, Integer> visitTimeMap = new HashMap<>();
        Map<Vertice, Integer> closeTimeMap = new HashMap<>();
        for (int i = 0; i < graph.order; i++) {
            Vertice v = vertices.get(i);
            visitTimeMap.put(v, visitTime[v.index]);
            closeTimeMap.put(v, closeTime[v.index]);
        }
        ret.put("VISIT_TIME", visitTimeMap);
        ret.put("CLOSE_TIME", closeTimeMap);
        return ret;
    }

    private static Map<String, Map<Vertice, Integer>> getResult() {
        Map<String, Map<Vertice, Integer>> ret = new HashMap<>();
        Map<Vertice, Integer> visitTimeMap = new HashMap<>();
        Map<Vertice, Integer> closeTimeMap = new HashMap<>();
        for (int i = 0; i < graph.order; i++) {
            Vertice v = graph.listaVertices.get(i);
            visitTimeMap.put(v, visitTime[v.index]);
            closeTimeMap.put(v, closeTime[v.index]);
        }
        ret.put("VISIT_TIME", visitTimeMap);
        ret.put("CLOSE_TIME", closeTimeMap);
        return ret;
    }

    private static void depthFirstSearch() {
        initDFS();
        for (Vertice u : graph.listaVertices) {
            if (visitTime[u.index] == 0) {
                visitDFS(u);
            }
        }
    }

    private static void depthFirstSearch(List<Vertice> vertices) {
        initDFS();
        for (Vertice u : vertices) {
            if (visitTime[u.index] == 0) {
                visitDFS(u);
            }
        }
    }

    private static void initDFS() {
        time = 0;
        for (int i = 0; i < graph.order; i++) {
            visitTime[i] = 0;
            closeTime[i] = 0;
        }
    }

    private static void visitDFS(Vertice u) {
        time++;
        visitTime[u.index] = time;
        for (Vertice v : u.adjacents) {
            if (visitTime[v.index] == 0) {
                visitDFS(v);
            }
        }
        time++;
        closeTime[u.index] = time;
    }
}
