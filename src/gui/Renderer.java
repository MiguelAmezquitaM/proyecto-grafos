package gui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Renderer extends JPanel {
    ArrayList<Renderable> renderableObjects = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
