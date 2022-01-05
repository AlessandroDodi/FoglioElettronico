package Cell;

import Other.OperatorEnum;

public class FormulaCell extends Cell<String>{
    float operando1;
    float operando2;
    OperatorEnum operator;
    public FormulaCell(int row, int col, String value) {
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
    @Override
    public boolean isValid(String value) {
        // TODO Auto-generated method stub
        return false;
    }
    
}