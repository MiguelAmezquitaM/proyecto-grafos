package gui;

import java.awt.Color;

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

    private final int WIDTH = 800;

    private final int HEIGHT = 600;

    private Menu menu = new Menu(ciudades);

    private void initCiudades() {
    
    }

    public void init() {
        JPanel mainPanel = new JPanel();
        
        mainPanel.setBackground(Color.black);

        add(mainPanel);

        initCiudades();
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
