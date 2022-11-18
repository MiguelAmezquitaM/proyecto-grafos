package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Renderer {
    ArrayList<Renderable> renderableObjects = new ArrayList<>();

    public void add(Renderable obj) {
        renderableObjects.add(obj);
    }

    public void remove(Renderable obj) {
        renderableObjects.remove(obj);
    }

    public void render(Graphics g) {
        for (Renderable rd : renderableObjects) {
            rd.draw((Graphics2D) g);
        }
    }
}
