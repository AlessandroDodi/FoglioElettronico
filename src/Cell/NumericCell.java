package Cell;
public class NumericCell extends Cell<Double>{
    public NumericCell(int row, int col, String value, Cell<?>[][] m) {
        super(row, col, value, m);
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
        try {
            Double.parseDouble(value.toString()); //.replace(',', '.')
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        
    }

}