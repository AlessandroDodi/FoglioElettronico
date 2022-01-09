package Cell;

import Other.OperatorEnum;

public class FormulaCell extends Cell<Double> implements Addable{
    static int nOperands = 2;
    Double operandValues[];
    int indexRow[];
    int indexCOl[];
    OperatorEnum operator;
    
    public FormulaCell(int row, int col, String value, Cell<?>[][] m) { // Cell<?>[][] m
        super(row, col, value, m);
        isValid(value);
    }

    @Override
    public boolean isNumeric() {
        return true;
    }
    @Override
    public Double convert(String value) {
        //Double op1 = 0.0, op2 = 0.0;
        System.out.println((Double.parseDouble(m[0][0].getValue())+Double.parseDouble(m[0][1].getValue())));
        return Double.parseDouble(m[0][0].getValue())+Double.parseDouble(m[0][1].getValue());
    }
    public Double getDouble(String s) {
        return 0.0;
    }
    public boolean isValueValid(String s) {
        if(isValueDouble(s) || isValueAddableCell(s))
            return true;
        return false;
    }
    public boolean isValueDouble(String s) {
        try {
            Double a = Double.parseDouble(s);
            System.out.println(a);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    public boolean isValueAddableCell(String s) {
        try {
            int row = s.charAt(0) - 'A';
            if(row <0 || row > 26)
                return false;
            try {
                try {
                    int column = Integer.parseInt(s.substring(1));
                    if(column < 0 || column > 100)
                    return false;
                    if(m[row][column].isNumeric()) {
                        System.out.println("m["+row+"]["+column+"] = "+m[row][column].getInfo());
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    return false;
                }
                
                
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        
        
    }
    @Override
    public boolean isValid(String value) {
        if (value.toString().startsWith("=")) {
            String [] parts = value.substring(1).split("[+-]");
            if(parts.length < 2)
                return false;
            for (String x : parts) {
                System.out.println(x);
                if(!isValueValid(x)) {
                    System.out.println("NOT Valid");
                    return false;
                }
            }
            System.out.println("Is Valid");
            return true;
        }
        System.out.println("NOT Valid");
        return false;
    }
}