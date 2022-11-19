/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.renderables;

import datos.Ciudad;
import gui.Renderable;
import gui.util.Vector2D;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Maria Jose Amezquita
 */
public class Node implements Renderable {
    private Color color;
    private Ciudad ciudad;
    private Vector2D position;

    public static final int RAD = 50;

    public Node(Vector2D position, Color color, Ciudad ciudad) {
        this.color = color;
        this.ciudad = ciudad;
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(position.x, position.y, RAD, RAD);
    }
}
