package gui;

import javax.swing.JPanel;
import java.awt.*;

import datos.Viaje;
import gui.util.Vector2D;
import gui.util.Vector2F;

public class EViaje extends Entidad {

    private static final Stroke stroke = new BasicStroke(2);

    private static final Color color = new Color(213, 245, 227);

    private static final Font font = new Font(Font.MONOSPACED, Font.BOLD, 14);

    private static final double tetha = 40 * Math.PI / 180;

    private static final double costetha = Math.cos(tetha);

    private static final double sintetha = Math.sin(tetha);

    public EViaje(Camera camera, JPanel panel, Viaje viaje, ECiudad ciudadDestino, ECiudad ciudadOrigen) {
        this.camera = camera;
        this.panel = panel;
        this.viaje = viaje;
        this.ciudadDestino = ciudadDestino;
        this.ciudadOrigen = ciudadOrigen;
    }

    @Override
    public void pintar() {
        if (flecha != null) {
            pintar(flecha);
            return;
        }

        var g = (Graphics2D) panel.getGraphics();

        g.setColor(color);
        g.setStroke(stroke);

        // Matematicas
        int radio1 = (int) (ciudadOrigen.getRadio() * camera.getScale());
        int radio2 = (int) (ciudadDestino.getRadio() * camera.getScale());

        Vector2D A = ciudadOrigen.getCiudad().getPosition();
        Vector2D B = ciudadDestino.getCiudad().getPosition();

        A = camera.virtualPosition(A.x, A.y);
        B = camera.virtualPosition(B.x, B.y);

        // Hallar el centro del circulo
        A.x += radio1 / 2;
        A.y += radio1 / 2;
        B.x += radio2 / 2;
        B.y += radio2 / 2;

        // Calcular las flechitas
        // Vertor desde A hasta B
        Vector2D AB = new Vector2D(B.x - A.x, B.y - A.y);
        // Magnitud
        double len = Math.sqrt(AB.x * AB.x + AB.y * AB.y);
        // Vector unitario
        Vector2F ABu = new Vector2F((double) AB.x / len, (double) AB.y / len);

        // Calculando el punto mas cercano al exterior del circulo
        A = A.plus(ABu.by(radio1 >> 1));
        B = B.mines(ABu.by(radio2 >> 1));

        g.drawLine(A.x, A.y, B.x, B.y);

        Vector2F r1 = new Vector2F(
                ABu.x * costetha - ABu.y * sintetha,
                ABu.x * sintetha + ABu.y * costetha);
        Vector2F r2 = new Vector2F(
                ABu.x * costetha - ABu.y * (-sintetha),
                ABu.x * (-sintetha) + ABu.y * costetha);

        r1 = r1.by(radio1 >> 1);
        r2 = r2.by(radio2 >> 1);

        g.drawLine(A.x, A.y, A.x + r1.x.intValue(), A.y + r1.y.intValue());
        g.drawLine(A.x, A.y, A.x + r2.x.intValue(), A.y + r2.y.intValue());

        g.setFont(font);

        var ABup = new Vector2F(ABu.y, -ABu.x);
        var ABm = new Vector2D(A.x + AB.x / 2, A.y + AB.y / 2);

        var StrPos = new Vector2D(ABm.x + (int) (25.0 * ABup.x), ABm.y + (int) (25.0 * ABup.y));

        g.drawString(viaje.toString(), StrPos.x, StrPos.y);

        var R1 = new Vector2D(r1.x, r1.y);
        var R2 = new Vector2D(r2.x, r2.y);

        flecha = new Flecha(A, B, R1, R2, StrPos, viaje);
    }

    private void pintar(Flecha f) {
        var g = (Graphics2D) panel.getGraphics();
        g.setStroke(stroke);
        g.setColor(color);
        g.drawLine(f.A.x, f.A.y, f.B.x, f.B.y);
        g.drawLine(f.A.x, f.A.y, f.A.x + f.R1.x, f.A.y + f.R1.y);
        g.drawLine(f.A.x, f.A.y, f.A.x + f.R2.x, f.A.y + f.R2.y);
        g.setFont(font);
        g.drawString(f.cost.toString(), f.StrPos.x, f.StrPos.y);
    }

    public Color getColor() {
        return _color;
    }

    public Flecha getFlecha() {
        return flecha;
    }

    public Camera getCamera() {
        return camera;
    }

    public JPanel getPanel() {
        return panel;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public ECiudad getCiudadDestino() {
        return ciudadDestino;
    }

    public ECiudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    private Color _color = EViaje.color;

    private Flecha flecha;

    private Camera camera;

    private JPanel panel;

    private Viaje viaje;

    private ECiudad ciudadDestino;

    private ECiudad ciudadOrigen;
}
