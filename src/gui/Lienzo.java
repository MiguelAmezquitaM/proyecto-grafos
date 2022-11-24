
package gui;

import datos.Ciudad;
import datos.Viaje;
import grafo.Grafo;
import gui.util.Vector2D;
import gui.util.Vector2F;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

class Flecha {
    public Vector2D A;
    public Vector2D B;
    public Vector2D R1;
    public Vector2D R2;
    public Vector2D StrPos;
    public Viaje cost;

    public Flecha(Vector2D a, Vector2D b, Vector2D r1, Vector2D r2, Vector2D StrPos, Viaje cost) {
        A = a;
        B = b;
        R1 = r1;
        R2 = r2;
        this.StrPos = StrPos;
        this.cost = cost;
    }
}

/**
 * @author david
 */
public class Lienzo {
    // Optimizacion
    private static final HashMap<Integer, Flecha> flechas = new HashMap<>();

    public static ArrayList<Integer> modified = new ArrayList<>();

    private static final Font font = new Font("Monospaced", Font.BOLD, 16);

    private static final Color colorCirculo = new Color(209, 242, 235);

    private static final Color bordeCirculo = new Color(33, 47, 60);

    private static final Color colorTexto = new Color(248, 249, 249);

    private static final Color colorFlecha = new Color(130, 224, 170);

    private static final BasicStroke stroke = new BasicStroke(2);

    public static void dibujarGrafo(Graphics2D g, Grafo<Ciudad, Viaje> grafo) {
        for (int i = 0; i < grafo.orden(); i++) {
            var c = grafo.getVertice(i);
            var cp = c.getPosition();
            pintarCirculo(g, c.getNombre(), cp.x, cp.y);
            for (int j = 0; j < grafo.orden(); j++) {
                var ccost = grafo.getCosto(i, j);

                if (ccost == null) continue;

                var flecha = flechas.get(i + j * grafo.orden());

                if (flecha != null && !modified.contains(i) && !modified.contains(j)) pintarFlecha(g, flecha);

                var ccp = grafo.getVertice(j).getPosition();

                flechas.put(i + j * grafo.orden(), pintarFlechaImpl(g, cp.x, cp.y, ccp.x, ccp.y, ccost));
            }
        }
        modified.clear();
    }

    public static void limpiar(Graphics2D g, JPanel panel) {
        var size = panel.getSize();
        g.setColor(panel.getBackground());
        g.clearRect(0, 0, size.width, size.height);
    }

    public static void pintarCirculo(Graphics2D g, String nombre, int x, int y) {
        g.setColor(new Color(209, 242, 235));
        g.fillOval(x, y, 30, 30);

        g.setColor(new Color(33, 47, 60));
        g.drawOval(x, y, 30, 30);

        g.setColor(new Color(248, 249, 249));
        g.setFont(font);
        var strMetrics = g.getFontMetrics();
        var width = strMetrics.stringWidth(nombre);
        g.drawString(nombre, x + 15 - width / 2, y + 55);
    }

    private static void pintarFlecha(Graphics2D g, Flecha f) {
        g.setStroke(stroke);
        g.setColor(colorFlecha);
        g.drawLine(f.A.x, f.A.y, f.B.x, f.B.y);
        g.drawLine(f.A.x, f.A.y, f.A.x + f.R1.x, f.A.y + f.R1.y);
        g.drawLine(f.A.x, f.A.y, f.A.x + f.R2.x , f.A.y + f.R2.y);
        g.setFont(font);
        g.drawString(String.valueOf(f.cost.getTiempo()), f.StrPos.x, f.StrPos.y);
    }

    private static Flecha pintarFlechaImpl(Graphics2D g, int x1, int y1, int x2, int y2, Viaje v) {
        // Matematicas
        // Hallar el centro del circulo
        Vector2D B = new Vector2D(x1 + 15, y1 + 15);
        Vector2D A = new Vector2D(x2 + 15, y2 + 15);

        // Calcular las flechitas
        // Vertor desde A hasta B
        Vector2D AB = new Vector2D(B.x - A.x, B.y - A.y);
        // Magnitud
        double len = Math.sqrt(AB.x * AB.x + AB.y * AB.y);
        // Vector unitario
        Vector2F ABu = new Vector2F((double)AB.x / len, (double)AB.y / len);

        // Calculando el punto mas cercano al exterior del circulo
        A = A.plus(ABu.by(15.0));
        B = B.mines(ABu.by(15.0));

        g.drawLine(A.x, A.y, B.x, B.y);

        // Rotando ABu 40 grados
        double tetha = 40 * Math.PI / 180;

        double sintheha = Math.sin(tetha);
        double costheha = Math.cos(tetha);

        Vector2F r1 = new Vector2F(
                ABu.x * costheha - ABu.y * sintheha,
                ABu.x * sintheha + ABu.y * costheha
        );
        Vector2F r2 = new Vector2F(
                ABu.x * costheha - ABu.y * (-sintheha),
                ABu.x * (-sintheha) + ABu.y * costheha
        );

        r1 = r1.by(15.0);
        r2 = r2.by(15.0);

        g.drawLine(A.x, A.y, A.x + r1.x.intValue() , A.y + r1.y.intValue());
        g.drawLine(A.x, A.y, A.x + r2.x.intValue() , A.y + r2.y.intValue());

        g.setFont(font);

        var ABup = new Vector2F(ABu.y, -ABu.x);
        var ABm = new Vector2D(A.x + AB.x / 2, A.y + AB.y / 2);

        var StrPos = new Vector2D(ABm.x + (int)(25.0 * ABup.x), ABm.y + (int)(25.0 * ABup.y));

        g.drawString(String.valueOf(v.getTiempo()), StrPos.x, StrPos.y);

        var R1 = new Vector2D(r1.x, r1.y);
        var R2 = new Vector2D(r2.x, r2.y);

        return new Flecha(A, B, R1, R2, StrPos, v);
    }

    public static void clickSobreNodo(Graphics2D g, int x, int y, Color co, String n) {
        g.setColor(co);
        g.setStroke(new BasicStroke(4));
        g.fillOval(x, y, 30, 30);
        g.setColor(Color.black);
        g.drawOval(x, y, 30, 30);
    }

}
