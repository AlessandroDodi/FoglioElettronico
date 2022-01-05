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
        return m[row][col].getValue();
    }
    public boolean isCellEditable(int row, int col) {
        return true;
    }
    public void setValueAt(Object value, int row, int col) {
        try {
            Double.parseDouble(value.toString().replace(',', '.'));
            m[row][col] = new NumericCell(row, col, value.toString());
            System.out.println("numeric");
        } catch (NumberFormatException e) {
            if(value.toString().startsWith("=")) {
                m[row][col] = new FormulaCell(row, col, value.toString());
                System.out.println("formula");
            } else {
                m[row][col] = new TextualCell(row, col, value.toString());
                System.out.println("textual");
            }
        }
        //fireTableDataChanged();
    }
    private char[] setColname(int n) {
        char[] c = new char[n];
        char temp = 'A';
        for(int i = 0; i < n; i++) 
            c[i] = temp++;
        return c;
    }
}















    /** restituisce il nome della colonna *//*
    public String getColumnName(int col) {
        return Character.toString(ColName[col]);
    }public Class getColumnClass(int col) {
        return getValueAt(0,col).getClass();
    }*/
            //this.getColumnModel().getColumn(0).sizeWidthToFit();
        //this.getColumnModel().getColumn(0).sizeWidthToFit();
        //this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //this.getColumnModel().getColumn(0).setWidth(45);
        //this.getColumnModel().getColumns().setPreferredWidth(); 
        //for (int i = 0; i < 26; i++)
          //  this.getColumnModel().getColumn(i).setPreferredWidth(175);
        //this.setRowHeight(0, 30);
        //this.setLayout(new BorderLayout());