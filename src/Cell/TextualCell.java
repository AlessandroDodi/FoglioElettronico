package Cell;
public class TextualCell extends Cell<String>{
    public TextualCell(int row, int col, String value, Cell<?>[][] m) {
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
        return false;
    }

}
