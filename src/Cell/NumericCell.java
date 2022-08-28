package Cell;
public class NumericCell extends Cell<Double>{
    /**
     * costruttore classe numericCell
     * @param row  riga
     * @param col colonna
     * @param value valore ddella cella
     * @param m matrice delle celle
     * @throws Exception qualora non fosse possibile istanziare una cella
     */
    public NumericCell(int row, int col, String value, Cell<?>[][] m) throws Exception {
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