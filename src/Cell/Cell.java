package Cell;
public abstract class Cell<TypeofCellData> {
    int row;
    int col;
    String value;
    TypeofCellData info;
    Cell<?>[][] m;
    public Cell(int row, int col, String value, Cell<?>[][] m) {
        this.m = m;
        setRow(row);
        setCol(col);
        setInfoAndValue(value);
        
    }
    //set
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public void setInfo(TypeofCellData info) {
        this.info = info;
    }
    public void setInfoAndValue(String v) {
        setValue(v);
        setInfo(convert(v));
    }
    //get
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }
    public String getValue() {
        return this.value;
    }
    public TypeofCellData getInfo() {
        return info;
    }
    
    public abstract boolean isNumeric();
    public abstract boolean isValid(String value);
    public abstract TypeofCellData convert(String value);
}
