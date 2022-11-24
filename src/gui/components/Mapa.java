package gui.components;

import javax.swing.JPanel;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;
import gui.Renderer;
import gui.renderables.Node;
import gui.util.Vector2D;

public class Mapa extends JPanel {
    private final Grafo<Ciudad, Viaje> ciudades;

    private final Renderer renderer = new Renderer();

    public Mapa(Grafo<Ciudad, Viaje> ciudades) {
        this.ciudades = ciudades;
        initRenderer();
        initialize();
    }

    private void initRenderer() {
        for (int i = 0; i < ciudades.orden(); ++i) {
            Vector2D pos = new Vector2D((int)(Math.random() * 100), (int)(Math.random() * 100));
            renderer.add(new Node(pos, Color.green, ciudades.getVertice(i)));
        }
    }

    private void initialize() {
        Canvas mapa = new Canvas() {
            public void paint(Graphics g) {
                renderer.render(g);
            }
        };

        add(mapa);
    }
}
