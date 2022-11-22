package grafo;

import java.util.ArrayList;
import java.util.List;


/** Grafo dinamico */
public class GrafoD<E, C> implements Grafo<E, C> {

    ArrayList<Vertice<E, C>> vertices = new ArrayList<>();

    @Override
    public Vertice<E, C> getVertice(int pos) {
        return vertices.get(pos);
    }

    @Override
    public C getCosto(int aPos, int bPos) {
        var a = vertices.get(aPos);
        E b = vertices.get(bPos).dato;
        for (var arista : a) {
            if (arista.dato == b) return arista.costo;
        }
        return null;
    }

    @Override
    public void addVertice(E valor, int x, int y) {
        vertices.add(new Vertice<>(valor, x ,y));
    }

    @Override
    public void addCosto(int aPos, int bPos, C costo) {
        var ver = vertices.get(aPos);
        ver.addArista(new Arista<>(vertices.get(bPos).dato, costo));
    }

    @Override
    public List<E> getSucesores(int pos) {
        ArrayList<E> sucesores = new ArrayList<>();

        for (var s : vertices.get(pos)) {
            sucesores.add(s.dato);
        }

        return sucesores;
    }

    @Override
    public int orden() {
        return vertices.size();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var vertice : vertices) {
            sb.append(vertice.dato).append(", ");
        }
        return sb.toString();
    }

}
