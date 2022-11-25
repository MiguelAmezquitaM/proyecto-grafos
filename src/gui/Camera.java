package gui;

import gui.util.Vector2D;

public class Camera {
    private Vector2D position = new Vector2D(0, 0);

    private double scale  = 1.0;

    public Camera() {
    }

    public Camera(int x, int y, double scale) {
        this.position.x = x;
        this.position.y = y;
        this.scale = scale;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Vector2D realPosition(int x, int y) {
        x /= scale; y /= scale;
        x -= position.x; y -= position.y;
        return new Vector2D(x, y);
    }

    public Vector2D virtualPosition(int x, int y) {
        x += position.x; y += position.y;
        x *= scale; y *= scale;
        return new Vector2D(x, y);
    }
}
