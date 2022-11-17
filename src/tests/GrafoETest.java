package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import grafo.Grafo;
import grafo.GrafoE;

public class GrafoETest {
    @Test
    public void addVertice() {
        Grafo<String, String> g = new GrafoE<String, String>(10, new ArrayList<>(), String.class);
        g.addVertice("8");
        g.addVertice("9");
        g.addVertice("2");
        g.addVertice("7");
        assertTrue("8".equals(g.getVertice(0)));
        assertTrue("9".equals(g.getVertice(1)));
        assertTrue("2".equals(g.getVertice(2)));
        assertTrue("7".equals(g.getVertice(3)));
    }

    @Test
    public void addArista() {
        var g = new GrafoE<String, String>(5, new ArrayList<>(), String.class);
        g.addVertice("8");
        g.addVertice("9");
        g.addVertice("2");
        g.addCosto(0, 1, "152");
        g.addCosto(1, 2, "485");
        g.addCosto(2, 0, "740");
        assertTrue("152".equals(g.getCosto(0, 1)));
        assertTrue("485".equals(g.getCosto(1, 2)));
        assertTrue("740".equals(g.getCosto(2, 0)));
        assertTrue(null == g.getCosto(0, 2));
        assertTrue(null == g.getCosto(1, 0));
        assertTrue(null == g.getCosto(2, 1));
    }
}
