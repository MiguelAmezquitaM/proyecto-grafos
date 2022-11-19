package gui;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;
import grafo.GrafoD;
import gui.components.Menu;

public class App extends JFrame {
    public final int WIDTH = 1200;

    public final int HEIGHT = 800;

    private Grafo<Ciudad, Viaje> ciudades = new GrafoD<>();

    private Menu menu = new Menu(ciudades);

    private JPanel mainPanel;

    private void initCiudades() {
        ciudades.addVertice(new Ciudad("Santa Marta", "Colombia"));
        ciudades.addVertice(new Ciudad("Barranquilla", "Colombia"));
    }

    public void init() {
        initCiudades();

        mainPanel = new JPanel();
        menu = new Menu(ciudades);

        mainPanel.setBackground(Color.white);
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        mainPanel.setLocation(new Point(0, 0));
        mainPanel.setLayout(new BoxLayout(mainPanel, 1));

        mainPanel.add(menu);
        add(mainPanel);

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
        setTitle("Proyecto Grafos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        App app = new App();
        app.init();
    }
}
