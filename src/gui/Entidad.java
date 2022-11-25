package gui;

public abstract class Entidad implements Dibujable {
    private int zIndex = 1;

    public void pintar() {
    }

    public void onClick(MouseEvt ev) {
    }

    public void onPress(MouseEvt ev) {
    }

    public void onRelease(MouseEvt ev) {
    }

    public void onDrag(MouseEvt ev) {
    }

    public void onWheel(MouseWheelEvt ev) {
    }

    public boolean isClicked(int x, int y) {
        return false;
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }
}
