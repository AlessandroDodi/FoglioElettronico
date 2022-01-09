package Logic;

import javax.swing.table.AbstractTableModel;

import Cell.Cell;
import Cell.FormulaCell;
import Cell.NumericCell;
import Cell.TextualCell;

public class MainTableModel extends AbstractTableModel {
    Cell<?>[][] m;
    private char[] ColName = null;
    private int numRow;
    
    public MainTableModel(Cell<?>[][] m, int numRow, int numCol) {
        super();
        this.m = m;
        this.numRow = numRow;
        ColName = setColname(numCol);
    }
    public int getColumnCount() {
        return ColName.length;
    }
    public int getRowCount() {
        return numRow;
    }
    public Object getValueAt(int row, int col) {
        //if (m[row][col].getClass().toString() == "FormulaCell")
          //  return m[row][col].getInfo();
        return m[row][col].getInfo().toString();
    }
    public boolean isCellEditable(int row, int col) {
        return true;
    }
    public void setValueAt(Object value, int row, int col) {
        //NumericCell.is
        try {
            Double.parseDouble(value.toString().replace(',', '.'));
            m[row][col] = new NumericCell(row, col, value.toString(), m);
            System.out.println("numeric");
        } catch (NumberFormatException e) {
            if(value.toString().startsWith("=")) {
                m[row][col] = new FormulaCell(row, col, value.toString().replace(" ", "").toUpperCase(), m);
                System.out.println("formula");
            } else {
                m[row][col] = new TextualCell(row, col, value.toString(), m);
                System.out.println("textual");
            }
        }
        fireTableDataChanged();
    }
    private char[] setColname(int n) {
        char[] c = new char[n];
        char temp = 'A';
        for(int i = 0; i < n; i++) 
            c[i] = temp++;
        return c;
    }
}
