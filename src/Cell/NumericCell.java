package Cell;
public class NumericCell extends Cell<Double>{
    public NumericCell(int row, int col, String value) {
        super(row, col, value);
    }

    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public Double convert(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public boolean isValid(String value) {
        // TODO Auto-generated method stub
        return false;
    }

}