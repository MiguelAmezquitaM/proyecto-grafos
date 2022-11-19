package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;

public class Menu extends JPanel {
    private Grafo<Ciudad, Viaje> ciudades;

    private Mapa mapa;

    private SideBar sideBar;

    private void initialize() {
        sideBar = new SideBar();
        mapa = new Mapa(ciudades);

        setBackground(Color.black);

        add(mapa);
        add(sideBar);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 550);
    }

    public Menu(Grafo<Ciudad, Viaje> ciudades) {
        this.ciudades = ciudades;
        initialize();
    }
}
