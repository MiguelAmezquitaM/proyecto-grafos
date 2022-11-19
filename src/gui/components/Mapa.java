package gui.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;
import gui.Renderable;
import gui.Renderer;
import gui.renderables.Node;
import gui.util.Vector2D;

public class Mapa extends Canvas {
    private Grafo<Ciudad, Viaje> ciudades;

    private Renderer renderer = new Renderer();

    public Mapa(Grafo<Ciudad, Viaje> ciudades) {
        this.ciudades = ciudades;
        initialize();
        initRenderer();
    }

    private void initRenderer() {
        for (int i = 0; i < ciudades.orden(); ++i) {
            int x = (int)(Math.random() * (getWidth() - Node.RAD));
            int y = (int)(Math.random() * (getHeight() - Node.RAD));
            Vector2D pos = new Vector2D(x, y);
            renderer.add(new Node(pos, new Color(209, 242, 235), ciudades.getVertice(i)));
        }
    }

    @Override
    public void paint(Graphics g) {
        renderer.render(g);
    }

    private void initialize() {
        setSize(new Dimension(800, 800));
        setPreferredSize(new Dimension(800, 800));
        setLocation(new Point(400, 0));
        setBackground(new Color(17, 122, 101));

        addMouseListener(new MouseListener());
    }
}

class MouseListener extends MouseAdapter {
    private Renderer renderer;

    @Override
    public void mouseDragged(MouseEvent e) {
        for (Renderable node : renderer.r()) {
            
        }
    }
}