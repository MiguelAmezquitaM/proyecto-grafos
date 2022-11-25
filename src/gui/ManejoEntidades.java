package gui;

import java.util.ArrayList;

import java.awt.event.*;

public class ManejoEntidades extends MouseAdapter implements Dibujable {

    @Override
    public void pintar() {
        for (var e : entidades) {
            e.pintar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent ev) {
        for (Entidad entidad : entidades) {
            if (entidad.isClicked(ev.getX(), ev.getY())) {
                entidad.onClick(new MouseEvt(ev.getX(), ev.getY(), ev.getButton()));
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent ev) {
        for (Entidad entidad : entidades) {
            if (entidad.isClicked(ev.getX(), ev.getY())) {
                entidad.onPress(new MouseEvt(ev.getX(), ev.getY(), ev.getButton()));
                dragging = entidad;
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent ev) {
        if (dragging == null)
            return;
        dragging.onDrag(new MouseEvt(ev.getX(), ev.getY(), ev.getButton()));
    }

    @Override
    public void mouseReleased(MouseEvent ev) {
        if (dragging == null)
            return;
        dragging.onRelease(new MouseEvt(ev.getX(), ev.getY(), ev.getButton()));
        dragging = null;
    }

    public void registrar(Entidad e) {
        entidades.add(e);
        entidades.sort(new ZIndexComparator());
    }

    private ArrayList<Entidad> entidades = new ArrayList<>();

    private Entidad dragging;
}
