package grafo;

import java.util.Collection;

public interface Grafo<E> {
    public static final double INF = Double.POSITIVE_INFINITY;

    /**
     * Obtener un vertice
     * @param pos Posicion en el cojunto de vertices
     * @return el valor del vertice
     */
    public E getVertice(int pos);

    /**
     * @param aPos vertice de salida
     * @param bPos vertice de llegada
     * @return Costo para "viajar" desde aPos a bPos
     */
    public double getCosto(int aPos, int bPos);

    /**
     * Agregar un vertice
     * @param valor valor del vertice
      */
    public void addVertice(E valor);

    /**
     * Determinar el costo desde un vertice a otro
     * @param aPos Vertice de salida
     * @param bPos Vertice de llegada
     * @param costo Costo para "viajar" de aPos a bPos
     */
    public void addCosto(int aPos, int bPos, double costo);

    /**
     * Obtener los sucesores del vertice en la posicion pos
     * @param pos
     * @return colleccion de sucesores
      */
    public Collection<E> getSucesores(int pos);

    /**
     * @return numero de vertices del grafo
     */
    public int orden();

}
