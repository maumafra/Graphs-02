//Alunos: Maurício Mafra Monnerat, Mauro Fialho
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {
    private static Integer[] d;
    private static Vertice[] pi;
    private static boolean[] s;
    private static boolean[] q;
    private static Graph graph;
    public static Map<String, String> findShorterPath(Graph graphParam, Vertice start, Vertice end){
        graph = graphParam;
        initDijkstra(graph.order, start.index);
        while (qHasVertices()) {
            Vertice u = getShorterVertice();
            if (u == null) break;
            for (Vertice v : u.adjacents) {
                relax(u, v);
            }
        }
        return getResult(start, end);
    }

    private static Map<String, String> getResult(Vertice start, Vertice end) {
        HashMap<String, String> ret = new HashMap<>();
        try {
            Integer cost = d[end.index];
            String path = " -> "+end.verticeName;
            int nextIdx = end.index;
            for (int i = 0; i < graph.order; i++) {
                Vertice v = pi[nextIdx];
                nextIdx = v.index;
                if (nextIdx == start.index) {
                    path = v.verticeName + path;
                    break;
                } else {
                    path = " -> " + v.verticeName + path;
                }
            }
            ret.put("PATH", path);
            ret.put("COST", cost.toString());
        } catch (Exception e) {
            ret.put("PATH", "Não foi possível gerar um caminho da origem para o destino especificados.");
            ret.put("COST", "Infinito");
        }
        return ret;
    }

    private static void relax(Vertice u, Vertice v) {
        if (d[v.index] > d[u.index] + Integer.parseInt(graph.matrix[u.index][v.index])) {
            d[v.index] = d[u.index] + Integer.parseInt(graph.matrix[u.index][v.index]);
            pi[v.index] = u;
        }
    }

    private static void initDijkstra(int graphOrder, int start) {
        d = new Integer[graphOrder];
        pi = new Vertice[graphOrder];
        s = new boolean[graphOrder];
        q = new boolean[graphOrder];
        for (int i = 0; i < graphOrder; i++) {
            d[i] = Integer.MAX_VALUE;
            pi[i] = null;
            s[i] = false;
            q[i] = true;
        }
        d[start] = 0;
    }

    private static Vertice getShorterVertice() {
        int dAux = Integer.MAX_VALUE;
        Vertice ret = null;
        for (Vertice v : graph.listaVertices) {
            if (q[v.index]) {
                if (dAux > d[v.index]) {
                    dAux = d[v.index];
                    ret = v;
                }
            }
        }
        if (ret != null) {
            q[ret.index] = false;
        }
        return ret;
    }
    private static boolean qHasVertices() {
        boolean ret = false;
        for (int i = 0; i < q.length; i++) {
            ret = ret || q[i];
        }
        return ret;
    }
}
