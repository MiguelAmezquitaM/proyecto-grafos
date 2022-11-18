/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.renderables;

import java.awt.Color;
import java.awt.Graphics2D;

import datos.Viaje;
import gui.Renderable;
import gui.util.Vector2D;

/**
 *
 * @author Maria Jose Amezquita
 */
public class Arrow implements Renderable {
    private Node a, b;
    private Viaje viaje;

    public Arrow(Node a, Node b, Viaje viaje) {
        this.a = a;
        this.b = b;
        this.viaje = viaje;
    }

    public Node getA() {
        return a;
    }

    public void setA(Node a) {
        this.a = a;
    }

    public Node getB() {
        return b;
    }

    public void setB(Node b) {
        this.b = b;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    @Override
    public void draw(Graphics2D g) {
        Vector2D A = a.getPosition();
        Vector2D B = a.getPosition();
        Vector2D AB = B.mines(A);
        g.setColor(Color.white);
        g.drawLine(A.x, A.y, B.x, B.y);
    }
}
