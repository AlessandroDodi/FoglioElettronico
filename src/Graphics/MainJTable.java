package Graphics;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.Color;

public class MainJTable extends JTable{
    public MainJTable(TableModel data) {
        super(data);
        this.setBackground(Color.yellow);
        //this.setPreferredScrollableViewportSize(this.getPreferredSize()); //questo fa quasi quello che voglio
        //this.setFillsViewportHeight(true);
        //setSize(new Dimension(900,500));
        //setPreferredSize(new Dimension(900,500));
        //this.setMinimumSize(new Dimension(900, 600));
        //this.setPreferredScrollableViewportSize(new Dimension(1300, 1000));
        //this.setFillsViewportHeight(true);
        //for (int i = 0; i < 26; i++)
          //this.getColumnModel().getColumn(i).setPreferredWidth(175);
    }
}
