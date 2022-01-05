package Cell;
public class TextualCell extends Cell<String>{
    public TextualCell(int row, int col, String value) {
        super(row, col, value);
    }
    @Override
    public boolean isNumeric() {
        return true;
    }
    @Override
    public String convert(String value) {
        return value;
    }

}
