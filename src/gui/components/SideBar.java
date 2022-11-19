package gui.components;

import javax.swing.JPanel;
import java.awt.Dimension;

public class SideBar extends JPanel {
    public SideBar() {
        initialize();
    }

    private void initialize() {
        setPreferredSize(new Dimension(200, 550));
    }
}
