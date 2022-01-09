package Graphics;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableModel;

import Cell.Cell;
import Cell.TextualCell;
import Logic.MainTableModel;
public class MainPanel extends JPanel {
    public MainPanel() {
        this.setBackground(Color.BLACK);
        int numCol = 26;
        int numRow = 100;
        Cell<?>[][] m = new Cell[numRow][numCol];
        for(int i = 0; i < numRow; i++) 
            for(int j = 0; j < numCol; j++)
                m[i][j] = new TextualCell(i, j, "v", m);
        setBackground(Color.BLACK);       
        TableModel dataModel = new MainTableModel(m, numRow, numCol);
        MainJTable t = new MainJTable(dataModel);
        MainScrollPane p = new MainScrollPane(t);
        InfoField f = new InfoField();
        //this.add(new JScrollPane(t));
        this.add(f);
        this.add(p, BorderLayout.CENTER);
    }
    
}