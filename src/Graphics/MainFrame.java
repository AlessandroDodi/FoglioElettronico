package Graphics;

import javax.swing.*;

public class MainFrame extends JFrame {
    MainPanel panel;
    public MainFrame() {
        this("");
    }
    public MainFrame(String titolo) {
        super(titolo);
        setBounds(0, 0, 900, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MainPanel panel = new MainPanel();
        this.setVisible(true);
        this.add(panel);
    }
}