package Graphics.North;
import javax.swing.JTextField;

import Cell.Cell;

public class InfoField extends JTextField{
    Cell<?>[][] m;
    /**
     * Costruttore di InfoField che richiama il costruttore di JTextField
     * @param m matrice delle celle
     */
    public InfoField(Cell<?>[][] m) {
        super();
        this.m = m;
        setColumns(30);
        setEditable(false);
    }
}