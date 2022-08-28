package Graphics;
import javax.swing.*;

public class MainFrame extends JFrame {
    MainPanel panel;
    /**
     * Costruttore di Mainframe che richiama il costruttore di JFrame
     */
    public MainFrame() {
        this("");
    }
    /**
     * Costruttore di Mainframe che richiama il costruttore di JFrame con titolo
     * @param titolo titolo da passare al costruttore di JFrame
     */
    public MainFrame(String titolo) {
        super(titolo);
        setBounds(0, 0, 900, 900);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MainPanel panel = new MainPanel();
        this.setVisible(true);
        this.add(panel);
    }
}