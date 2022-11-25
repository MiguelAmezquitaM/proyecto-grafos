package gui;

import java.util.Comparator;

public class ZIndexComparator implements Comparator<Entidad> {

    @Override
    public int compare(Entidad o1, Entidad o2) {
        return o1.getZIndex() - o2.getZIndex();
    }
    
}
