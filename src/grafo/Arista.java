package grafo;

public class Arista<E> {
    public double costo;
    public E dato;

    public Arista(E dato, double costo) {
        this.dato = dato;
        this.costo = costo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Arista<?>)) return false;
        Arista<?> o = (Arista<?>) obj;
        return o.costo == costo && o.dato.equals(dato);
    }

}
