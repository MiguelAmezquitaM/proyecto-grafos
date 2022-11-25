package tests;

import grafo.Floyd;
import grafo.Grafo;
import grafo.GrafoD;
import org.junit.Test;
import static org.junit.Assert.*;


public class FloydTest {

    @Test
    public void getF() {
        Grafo<String, Double> g = new GrafoD<>();
        Floyd<String, Double> floyd;
        g.addVertice("8");
        g.addVertice("9");
        g.addVertice("2");
        g.addVertice("15");
        g.addCosto(0, 1, 152.0);
        g.addCosto(0, 2, 252.0);
        g.addCosto(0, 3, 452.0);
        g.addCosto(1, 0, 45.0);
        g.addCosto(1, 2, 100.0);
        g.addCosto(2, 0, 540.0);
        g.addCosto(2, 1, 620.0);
        g.addCosto(2, 3, 25.0);
        g.addCosto(3, 0, 640.0);
        g.addCosto(3, 1, 240.0);

        floyd = new Floyd<>(g, new DoubleIndicator(), Double.class);
        var f = floyd.getF();
        assertEquals(152.0, f[0][1], 1.0);
        assertEquals(45.0, f[1][0], 1.0);
        assertEquals(277.0, f[0][3], 1.0);
        assertEquals(340.0, f[3][2], 1.0);
    }

    @Test
    public void getR() {
    }
}