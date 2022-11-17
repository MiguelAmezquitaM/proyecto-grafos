package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import grafo.Grafo;
import grafo.GrafoD;

public class GrafoDTest {
    @Test
    public void addVertice() {
        Grafo<String> g = new GrafoD<>();
        g.addVertice("8");
        g.addVertice("9");
        g.addVertice("2");
        g.addVertice("7");
        assertEquals("8", g.getVertice(0));
        assertEquals("9", g.getVertice(1));
        assertEquals("2", g.getVertice(2));
        assertEquals("7", g.getVertice(3));
    }

    @Test
    public void addArista() {
        Grafo<String> g = new GrafoD<>();
        g.addVertice("8");
        g.addVertice("9");
        g.addVertice("2");
        g.addCosto(0, 1, 152);
        g.addCosto(1, 2, 485);
        g.addCosto(2, 0, 740);
        assertEquals(152, g.getCosto(0, 1), 0.1);
        assertEquals(485, g.getCosto(1, 2), 0.1);
        assertEquals(740, g.getCosto(2, 0), 0.1);
        assertEquals(Grafo.INF, g.getCosto(0, 2), 0.1);
        assertEquals(Grafo.INF, g.getCosto(1, 0), 0.1);
        assertEquals(Grafo.INF, g.getCosto(2, 1), 0.1);
    }
}
