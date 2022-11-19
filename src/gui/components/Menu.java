package gui.components;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

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

        setSize(1200, 800);
        setPreferredSize(new Dimension(1200, 800));
        setLocation(new Point(0, 0));
        setBackground(Color.black);
        setLayout(new BoxLayout(this, 2));

        add(sideBar);
        add(mapa);
    }

    public Menu(Grafo<Ciudad, Viaje> ciudades) {
        this.ciudades = ciudades;
        initialize();
    }
}
