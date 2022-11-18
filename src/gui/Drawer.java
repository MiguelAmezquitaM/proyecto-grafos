/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Maria Jose Amezquita
 */
public class Drawer {
    private Graphics g;

    public Drawer(Graphics g) {
        this.g = g;
    }
    
    public void drawNode(Node node) {
        g.setColor(node.getColor());
        g.fillOval(node.getX(), node.getY(), 50, 50);
    }
    
    public void drawArrow(Arrow arrow) {
        Node a = arrow.getA();        
        Node b = arrow.getB();

        g.setColor(Color.white);
        g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
    }
}
