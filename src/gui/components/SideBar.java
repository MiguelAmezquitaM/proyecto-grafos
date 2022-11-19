package gui.components;

import javax.swing.JPanel;

import java.awt.*;

public class SideBar extends JPanel {
    public SideBar() {
        initialize();
    }

    private void initialize() {
        setSize(new Dimension(400, 800));
        setPreferredSize(new Dimension(400, 800));
        setLocation(new Point(0, 0));
        setBackground(new Color(46, 64, 83));
    }
}
