package gui.components;

import java.awt.Graphics;
import javax.swing.JPanel;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;

public class Menu extends JPanel {
    private Grafo<Ciudad, Viaje> ciudades;
    
    public Menu(Grafo<Ciudad, Viaje> ciudades) {
        this.ciudades = ciudades;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
}
