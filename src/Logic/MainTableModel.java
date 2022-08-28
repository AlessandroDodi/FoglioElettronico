package Logic;
import javax.swing.table.AbstractTableModel;
import Cell.Cell;
import Cell.FormulaCell;
import Cell.NumericCell;
import Cell.TextualCell;

public class MainTableModel extends AbstractTableModel {
    private Cell<?>[][] m;
    private char[] ColName = null;
    private int numRow;

    /**
     * Costruttre di MainTable 
     * @param m matrice di celle
     * @param numRow numero di righe
     * @param numCol numero di colonne
     */
    public MainTableModel(Cell<?>[][] m, int numRow, int numCol) {
        super();
        this.m = m;
        this.numRow = numRow;
        ColName = setColname(numCol);
        
    }
    /**
     * Ritorna l'array di caratteri contenente il nome delle colonne
     * @return ritorna l'array con il nome delle colonne
     */
    public char[] getColName() {
        return ColName;
    }
    /**
     * Ritorna il numero di colonne
     */
    public int getColumnCount() {
        return ColName.length;
    }
    /**
     * Ritorna il numero di righe
     */
    public int getRowCount() {
        return numRow;
    }
    /** */
    public Object getValueAt(int row, int col) {
        //if (m[row][col].getClass().toString() == "FormulaCell")
          //  return m[row][col].getInfo();
        return m[row][col].getValue();
    }
    public boolean isCellEditable(int row, int col) {
        return true;
    }
    public void setValueAt(Object value, int row, int col) {
        //NumericCell.is
        setCell(value.toString(), row, col);
        verifyChanges();
        fireTableDataChanged();
    }
    /**
     * setta l'array con il nome delle colonne attraversoo l'int passato come parametro
     * @param n intero che indica il numero di colonnne
     * @return array con i caratteri (nomi di colonna)
     */
    private  char[] setColname(int n) {
        char[] c = new char[n];
        char temp = 'A';
        for(int i = 0; i < n; i++) 
            c[i] = temp++;
        return c;
    }
    /**
     * setta la cella con il parametro passato per riferimento
     * @param value valore da inserire nella cella
     * @param row riga della cella
     * @param col colonna della cella
     */
    public void setCell(String value, int row, int col) {
        try {
            m[row][col] = new NumericCell(row, col, value, m);
        } catch (Exception e1) {
            try {
                m[row][col] = new FormulaCell(row, col, value.replace(" ", "").toUpperCase(), m);
            } catch (Exception e2) {
                try {
                    m[row][col] = new TextualCell(row, col, value, m);
                } catch (Exception e3) {}
            }
        }
    }
    public void verifyChanges() {
        for(int i = 0; i < this.getRowCount(); i++) {
            for(int j = 0; j < this.getColumnCount(); j++) {
                String v = m[i][j].getValue();
                setCell(v, i, j);
            }
        }
    }
    /**
     * Setta la matrice di colonne attraverso l'array generato dalla lettura di un file
     * @param s
     */
    public void setMatrix(String[] s) {
        int counter = 0;
        for(int i = 0; i < this.getRowCount(); i++) {
            for(int j = 0; j < this.getColumnCount(); j++) {
                String v = s[counter];
                setCell(v, i, j);
                counter++;
            }
        }
        fireTableDataChanged();
    }
    /**
     * Ordina una colonna
     * @param x carattere della colonna da ordinare
     * @param ascendent booleano che indica se la colonna è da ordinare in modo ascendente o discendente
     */
    public void orderColumn(Character x, boolean ascendent) {
        int column = x - 'A';
        for(int i = 0; i < this.getRowCount(); i++) 
            for(int j = 0; j < this.getRowCount(); j++)  {
                String s1 = m[i][column].getValue(); 
                String s2 = m[j][column].getValue();
                if(ascendent) {
                    if(s1 != "" && s2 != "" && s1.compareTo(s2) > 0) {
                        try {
                            m[j][column] = new TextualCell(j, column, s1, m);
                            m[i][column] = new TextualCell(i, column, s2, m);
                        } catch (Exception e) {}
                    }
                } else {
                    if(s1 != "" && s2 != "" && s1.compareTo(s2) < 0) {
                        try {
                            m[j][column] = new TextualCell(j, column, s1, m);
                            m[i][column] = new TextualCell(i, column, s2, m);
                        } catch (Exception e) {}
                    }
                }
            }
        verifyChanges();
        fireTableDataChanged();
    }
}
