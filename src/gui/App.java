package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;
import grafo.GrafoD;
import gui.components.Menu;

public class App extends JFrame {

    private Grafo<Ciudad, Viaje> ciudades = new GrafoD<>();

    public final int WIDTH = 800;

    public final int HEIGHT = 600;

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

        mainPanel.add(menu);
        add(mainPanel);

        setSize(WIDTH, HEIGHT);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        App app = new App();
        app.init();
    }
}
