
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author david
 */
public class Lienzo {
    
    public static void pintarCirculo(Graphics g, String nombre, int x, int y){
        ((Graphics2D)g).setColor(Color.red);
        ((Graphics2D)g).setStroke(new BasicStroke(4));
        ((Graphics2D)g).fillOval(x, y, 30, 30);
        ((Graphics2D)g).setColor(Color.black);
        ((Graphics2D)g).drawOval(x, y, 30, 30);
        ((Graphics2D)g).setColor(Color.orange);
        Font fuente = new Font("Monospaced",Font.BOLD, 16);
        g.setFont(fuente);
        ((Graphics2D)g).drawString(nombre, x, y+16);
    }
    
    public static void pintarFlecha(Graphics g, int x1, int y1, int x2, int y2, int tam) {

        int xAux = 0, yAux = 0;
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(2);
        ((Graphics2D)g).setStroke(stroke);
        x1=x1+15;
        y1=y1+15;
        x2=x2+15;
        y2=y2+15;
        ((Graphics2D)g).drawLine(x1, y1, x2, y2);
        if (x1<=x2) xAux = ((x2-x1)/2)+x1;
        if (x1>x2) xAux = ((x1-x2)/2)+x2;
        if (y1<y2) yAux = ((y2-y1)/2)+y1;
        if (y1>=y2) yAux = ((y1-y2)/2)+y2;
        double alfa=Math.atan2(y2-y1,x2-x1);
        int k=5;
        int xa=(int)(x2-k*Math.cos(alfa+1));
        int ya=(int)(y2-k*Math.sin(alfa+1));
        // Se dibuja un extremo de la dirección de la flecha.
        ((Graphics2D)g).drawLine(xa,ya,x2-10,y2-10);
        xa=(int)(x2-k*Math.cos(alfa-1));
        ya=(int)(y2-k*Math.sin(alfa-1));
        // Se dibuja el otro extremo de la dirección de la flecha.
        ((Graphics2D)g).drawLine(xa,ya,x2,y2);
        Font fuente = new Font("Monospaced", Font.PLAIN, 12);
        g.setFont(fuente);
        ((Graphics2D)g).drawString(String.valueOf(tam), xAux, yAux);
    }
    
    public static void pintarCamino(Graphics g, int x1, int y1, int x2, int y2, Color color) {
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(2);
        ((Graphics2D)g).setStroke(stroke);
        g.setColor(color);
        ((Graphics2D)g).drawLine(x1+10, y1+10, x2+10, y2+10);   
    }
    
    public static void clickSobreNodo(Graphics g, int x, int y, Color co, String n) {
        ((Graphics2D)g).setColor(co);
        ((Graphics2D)g).setStroke(new BasicStroke(4));
        ((Graphics2D)g).fillOval(x, y, 30, 30);
        ((Graphics2D)g).setColor(Color.black);
        ((Graphics2D)g).drawOval(x, y, 30, 30);
        Font fuente = new Font("Monospaced",Font.BOLD, 16);
        g.setFont(fuente);
        ((Graphics2D)g).drawString(n, x, y+18);
    }
    
}
