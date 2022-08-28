package Graphics.Center;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.Color;

public class MainJTable extends JTable{
    /**
     * Costruttore della tabella principale
     * @param data TableModel da dare in input al costruttore
     */
    public MainJTable(TableModel data) {
        super(data);
        this.setBackground(Color.white);
        this.getTableHeader().setReorderingAllowed(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 26; i++) {
            this.getColumnModel().getColumn(i).setMinWidth(40);
            this.getColumnModel().getColumn(i).setMaxWidth(100);
        }        
    }
}
