package gui;

import grafo.Grafo;

import java.awt.*;

public class PopupMenu {
    final static int OPTION_HEIGHT = 50;
    final static int OPTION_WIDTH = 150;
    final static int FONT_HEIGHT = 18;

    final static String[] options = new String[] {
            "Eliminar", "Aislar"
    };

    private static Rectangle opt = new Rectangle(OPTION_WIDTH, OPTION_HEIGHT);

    private static Rectangle rect = new Rectangle(OPTION_WIDTH, OPTION_HEIGHT * options.length);

    public static int selected = -1;

    public static void draw(int x, int y, Graphics2D g, int idx) {
        rect.x = x; rect.y = y; selected = idx;

        g.setColor(Color.black);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        g.setColor(Color.white);

        g.setFont(new Font("Cascadia Code", Font.BOLD, FONT_HEIGHT));
        for (int i = 0; i < options.length; i++) {
            g.drawRect(rect.x, rect.y + i * OPTION_HEIGHT, OPTION_WIDTH, OPTION_HEIGHT);
            g.drawString(options[i], rect.x + 15, rect.y + (i + 1) * OPTION_HEIGHT - (OPTION_HEIGHT - FONT_HEIGHT) / 2);
        }
    }

    public static int click(int x, int y) {
        if (!rect.contains(x, y)) return -1;

        for (int i = 0; i < options.length; i++) {
            opt.x = rect.x; opt.y = rect.y + OPTION_HEIGHT * i;
            if (opt.contains(x, y)) return i;
        }

        return -1;
    }

}
