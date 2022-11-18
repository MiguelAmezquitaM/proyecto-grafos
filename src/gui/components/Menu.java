package gui.components;

import javax.swing.JPanel;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;

public class Menu extends JPanel {
    private Grafo<Ciudad, Viaje> ciudades;

    private Mapa mapa;

    private SideBar sideBar;

    private void initialize() {
        mapa = new Mapa(ciudades);
        sideBar = new SideBar();

        add(mapa);
        add(sideBar);
    }

    public Menu(Grafo<Ciudad, Viaje> ciudades) {
        this.ciudades = ciudades;
        initialize();
    }
}
