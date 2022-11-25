package gui;

public class MouseWheelEvt extends MouseEvt {

    public MouseWheelEvt(int x, int y, double rot) {
        super(x, y, 0);
        rotation = rot;
    }
    
    public double getRotation() {
        return rotation;
    }

    private double rotation;
}
