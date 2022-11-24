package grafo;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/** 
 * Grafo estatico
 */
public class GrafoE<E, C> implements Grafo<E, C>, Serializable{

    private final C[][] costos;

    private final List<E> vertices;

    public GrafoE(int maxVertices, List<E> vertices, Class<C> c) {
        @SuppressWarnings("unchecked")
        final C[][] costos = (C[][]) Array.newInstance(c, maxVertices, maxVertices);
        this.costos = costos;
        this.vertices = vertices;
    }

    @Override
    public E getVertice(int pos) {
        return vertices.get(pos);
    }

    @Override
    public C getCosto(int aPos, int bPos) {
        return costos[aPos][bPos];
    }

    @Override
    public void addVertice(E valor) {
        vertices.add(valor);
    }

    @Override
    public void addCosto(int aPos, int bPos, C costo) {
        costos[aPos][bPos] = costo;
    }

    @Override
    public List<E> getSucesores(int pos) {
        ArrayList<E> retval = new ArrayList<>();
        for (int i = 0; i < orden(); ++i) {
            if (costos[pos][i] != null && i != pos) {
                retval.add(vertices.get(i));
            }
        }
        return retval;
    }

    @Override
    public int orden() {
        return vertices.size();
    }
    
}
