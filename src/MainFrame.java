import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // final private Font mainFont = new Font("Poppins", Font.PLAIN, 18);

    public void initialize() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(10, 12, 13));

        add(mainPanel);

        setTitle("Proyecto Grafos");
        setSize(1000, 800);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.initialize();
    }
}
