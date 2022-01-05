package Graphics;
import javax.swing.JScrollPane;

import java.awt.Color;
public class MainScrollPane extends JScrollPane{
    public MainScrollPane(MainJTable t) {
        super(t);
        setBackground(Color.ORANGE);
    }
}
