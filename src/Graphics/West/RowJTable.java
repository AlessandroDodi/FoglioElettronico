package Graphics.West;
import java.awt.Color;

import javax.swing.JTable;
public class RowJTable extends JTable{
    public RowJTable() {
        super(new RowJTableModel());
        getColumnModel().getColumn(0).setPreferredWidth(30);
        this.setBackground(Color.lightGray);
        setRowHeight(0, 21);
    }
}
