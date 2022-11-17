package grafo;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertice<E> implements Iterable<Arista<E>> {

    private LinkedList<Arista<E>> sucesores = new LinkedList<>();
    public E dato;

    public Vertice(E dato) {
        this.dato = dato;
    }

    public void addArista(Arista<E> ar) {
        int i = sucesores.indexOf(ar);
        if (i == -1)
            sucesores.add(ar);
        else
            sucesores.set(i, ar);
    }

    public Arista<E> getArista(int pos) {
        return sucesores.get(pos);
    }

    @Override
    public Iterator<Arista<E>> iterator() {
        return sucesores.iterator();
    }

}
