package grafo;

import java.util.List;

public interface Grafo<E, C> {
    /**
     * Obtener un vertice
     * @param pos Posicion en el cojunto de vertices
     * @return el valor del vertice
     */
    public Vertice<E, C> getVertice(int pos);

    /**
     * @param aPos vertice de salida
     * @param bPos vertice de llegada
     * @return Costo para "viajar" desde aPos a bPos
     */
    public C getCosto(int aPos, int bPos);

    /**
     * Agregar un vertice
     * @param valor valor del vertice
      */
    public void addVertice(E valor, int x, int y);

    /**
     * Determinar el costo desde un vertice a otro
     * @param aPos Vertice de salida
     * @param bPos Vertice de llegada
     * @param costo Costo para "viajar" de aPos a bPos
     */
    public void addCosto(int aPos, int bPos, C costo);

    /**
     * Obtener los sucesores del vertice en la posicion pos
     * @param pos
     * @return colleccion de sucesores
      */
    public List<E> getSucesores(int pos);

    /**
     * @return numero de vertices del grafo
     */
    public int orden();

}
