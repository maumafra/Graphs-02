//Alunos: Maurício Mafra Monnerat, Mauro Fialho
import java.util.ArrayList;
import java.util.List;

public class Vertice {

    public List<Vertice> adjacents = new ArrayList<>();
    public String verticeName = "";
    public int index = 0;

    public Vertice(String name, int index) {
        this.verticeName = name;
        this.index = index;
    }

    public void addAdjacent(Vertice v) {
        this.adjacents.add(v);
    }
}
