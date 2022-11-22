package grafo;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertice<E, C> implements Iterable<Arista<E, C>> {

    private LinkedList<Arista<E, C>> sucesores = new LinkedList<>();
    public E dato;
    public int cordX;
    public int cordY;

    public Vertice(E dato, int x, int y) {
        this.dato = dato;
        this.cordX = x;
        this.cordY = y;
    }

    public void addArista(Arista<E, C> ar) {
        int i = sucesores.indexOf(ar);
        if (i == -1)
            sucesores.add(ar);
        else
            sucesores.set(i, ar);
    }

    public Arista<E, C> getArista(int pos) {
        return sucesores.get(pos);
    }

    @Override
    public Iterator<Arista<E, C>> iterator() {
        return sucesores.iterator();
    }

}
