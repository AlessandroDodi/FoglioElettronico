package Cell;
public class TextualCell extends Cell<String>{
    /**
     * costruttore classe TextualCell
     * @param row  riga
     * @param col colonna
     * @param value valore ddella cella
     * @param m matrice delle celle
     * @throws Exception qualora non fosse possibile istanziare una cella
     */
    public TextualCell(int row, int col, String value, Cell<?>[][] m) throws Exception{
        super(row, col, value, m);
    }
    @Override
    public boolean isNumeric() {
        return false;
    }
    @Override
    public String convert(String value) {
        return value;
    }
    @Override
    public boolean isValid(String value) {
        return true;
    }

}
