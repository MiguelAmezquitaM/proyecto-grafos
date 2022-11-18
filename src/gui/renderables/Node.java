/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.renderables;

import datos.Ciudad;
import gui.Renderable;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Maria Jose Amezquita
 */
public class Node implements Renderable {
    private int x, y;
    private Color color;
    private Ciudad ciudad;

    public Node(int x, int y, Color color, Ciudad ciudad) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.ciudad = ciudad;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
        g.fillOval(x, y, 50, 50);
    }
    
    
}
