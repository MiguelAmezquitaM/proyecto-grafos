package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;

public class ELienzo extends Entidad {

    public ELienzo(JPanel panel, ManejoEntidades mEntidades, Grafo<Ciudad, Viaje> grafo, Camera camera) {
        this.panel = panel;
        this.mEntidades = mEntidades;
        this.grafo = grafo;
        this.camera = camera;
    }

    @Override
    public void onClick(MouseEvt ev) {
        // Click left: Create a new vertex
        switch (ev.getKeyCode()) {
            case 1:
                String nombre, pais;
                try {
                    nombre = JOptionPane.showInputDialog(null, "Nombre de la ciudad: ");
                    pais = JOptionPane.showInputDialog(null, "Nombre del pais: ");
                    if (pais == null || nombre == null)
                        throw new Exception();
                } catch (Exception e) {
                    break;
                }
                
                var ciudad = new Ciudad(nombre, pais, camera.realPosition(ev.getX(), ev.getY()));
                grafo.addVertice(ciudad);
                mEntidades.registrar(new ECiudad(ciudad, panel, camera, 30));
                panel.repaint();
                break;
            // Click right: Desplegate Popup menu
            case 2:
                
            default: 
                break;
        }
    }

    @Override
    public void onDrag(MouseEvt ev) {
        // Move camera
    }

    @Override
    public void onWheel(MouseWheelEvt ev) {
        // Change camera scale
        camera.setScale(camera.getScale() + 0.05 * ev.getRotation());
    }

    @Override
    public boolean isClicked(int x, int y) {
        return true;
    }

    private JPanel panel;

    private ManejoEntidades mEntidades;

    private Grafo<Ciudad, Viaje> grafo;

    private Camera camera;
}
