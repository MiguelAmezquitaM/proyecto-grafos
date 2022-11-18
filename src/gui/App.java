package gui;

import javax.swing.JFrame;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;
import grafo.GrafoD;

public class App extends JFrame {

    private Grafo<Ciudad, Viaje> ciudades = new GrafoD<>();

    private void initCiudades() {

    }

    public void init() {
        initCiudades();
        
    }

    public static void main(String[] args) {
        App app = new App();
        app.init();
    }
}
