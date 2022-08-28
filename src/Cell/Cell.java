package Cell;

import java.io.Serializable;

public abstract class Cell<TypeofCellData> implements Serializable{
    int row;
    int col;
    String value;
    TypeofCellData info;
    Cell<?>[][] m;
    /**
     * costruttore di Cell
     * @param row numero di riga
     * @param col numero di colonna
     * @param value valore da mettere nella cella
     * @param m matrice di celle
     * @throws Exception se valore non è valido
     */
    public Cell(int row, int col, String value, Cell<?>[][] m) throws Exception{
        this.m = m;
        setRow(row);
        setCol(col);
        if(!isValid(value))
            throw new Exception("");
        else
            setInfoAndValue(value);
    }
    /**
     * setta il numero di riga
     * @param row riga
     */
    public void setRow(int row) {
        this.row = row;
    }
    /**
     * setta il numero di colonna
     * @param col colonna
     */
    public void setCol(int col) {
        this.col = col;
    }
    /**
     * setta il valore
     * @param value valore
     */
    private void setValue(String value) {
        this.value = value;
    }
    /**
     * setta l'info
     * @param info informazione (valore con tipo di dato che viene usato nella definizione attraverso un Generics)
     */
    private void setInfo(TypeofCellData info) {
        this.info = info;
    }
    /**
     * setta l 'informazione ed il valore, attraverso il richiamo del metodo convert'
     * @param v valore 
     */
    public void setInfoAndValue(String v) {
        setValue(v);
        setInfo(convert(v));
    }
    /**
     * ritorna il numero di riga
     * @return numero di riga
     */
    public int getRow() {
        return this.row;
    }
    /**
     * ritorna il numero di colonna
     * @return colonna
     */
    public int getCol() {
        return this.col;
    }
    /**
     * ritorna il valore
     * @return valore
     */
    public String getValue() {
        return this.value;
    }
    /**
     * ritorna l'informazione della cella (tipo di dato del generics)
     * @return
     */
    public TypeofCellData getInfo() {
        return info;
    }
    /**
     * se il tipo di informazione è numerica
     * @return ritorna se il tipo di informazione è numerica
     */
    public abstract boolean isNumeric();
    /**
     * se la stringa value è valida per il tipo di cella
     * @param value valore
     * @return
     */
    public abstract boolean isValid(String value);
    /**
     * converto la string avalore nell'informazione del tipo di dato dato in fase di definizione (generics)
     * @param value
     * @return
     */
    public abstract TypeofCellData convert(String value);
    @Override
    public String toString() {
        return value+System.lineSeparator();
    }
}
