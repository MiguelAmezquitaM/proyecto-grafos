package gui;

import javax.swing.JPanel;
import java.awt.*;

import datos.Ciudad;

public class ECiudad extends Entidad {

    private static Font font = new Font(Font.MONOSPACED, Font.BOLD, 16);
    
    private static Color color = new Color(253, 254, 254);

    private static Color bordeCirculo = new Color(33, 47, 60);
    
    private static Color colorTexto = new Color(248, 249, 249);
    
    public ECiudad(Ciudad ciudad, JPanel panel, Camera camera, int radio) {
        this.ciudad = ciudad;
        this.panel = panel;
        this.camera = camera;
        this.radio = radio;
    }

    @Override
    public void pintar() {
        int x = ciudad.getPosition().x;
        int y = ciudad.getPosition().y;
        var cameraPos = camera.getPosition();
        var cameraScale = camera.getScale();
        var g = (Graphics2D) panel.getGraphics();

        x += cameraPos.x; y += cameraPos.y;
        x *= cameraScale; y *= cameraScale;

        g.setColor(color);
        g.fillOval(x, y, radio, radio);

        g.setColor(bordeCirculo);
        g.drawOval(x, y, radio, radio);

        g.setColor(colorTexto);
        g.setFont(font);

        var strMetrics = g.getFontMetrics();
        var width = strMetrics.stringWidth(ciudad.getNombre());

        g.drawString(ciudad.getNombre(), x + radio / 2 - width / 2, y + (int)(radio * 1.7));
    }

    @Override
    public void onDrag(MouseEvt ev) {
        var pos = ciudad.getPosition();
        pos.x = ev.getX();
        pos.y = ev.getY();
        ciudad.setPosition(pos);
    }

    @Override
    public void onClick(MouseEvt ev) {
        // Click derecho: Mostrar el PopupMenu
        if (ev.getKeyCode() == 1) {
            // ...
        }
        // Click izquierdo: Marcar como seleccionado
        if (ev.getKeyCode() == 3) {
            // ...
        }
    }

    @Override
    public boolean isClicked(int x, int y) {
        var pos = ciudad.getPosition();
        var rect = new Rectangle(pos.x, pos.y, radio, radio);
        return rect.contains(x, y);
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public JPanel getPanel() {
        return panel;
    }

    public Camera getCamera() {
        return camera;
    }

    public int getRadio() {
        return radio;
    }

    private Ciudad ciudad;
    
    private JPanel panel;

    private Camera camera;

    private int radio;
}
