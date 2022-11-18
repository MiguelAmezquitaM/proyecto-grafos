package gui.util;

public class Vector2D {
    public Integer x, y;

    public Vector2D(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2D plus(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D mines(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }

}
