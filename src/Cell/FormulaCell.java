package Cell;
import static Other.OperatorEnum.Addition;

import java.util.Vector;

import Other.OperatorEnum;

public class FormulaCell extends Cell<Double>{
    static int nOperands = 2;
    Vector<Double> operandValues;
    OperatorEnum operator;
    /**
     * 
     * @param row numero riga
     * @param col numero colonna
     * @param value valore
     * @param m matrice di celle
     * @throws Exception qualora non sia possibile istanziare una cella 
     */
    public FormulaCell(int row, int col, String value, Cell<?>[][] m) throws Exception {
        super(row, col, value, m);
    }
    @Override
    public boolean isNumeric() {
        return true;
    }
    @Override
    public Double convert(String value) {
        if(operator == Addition)
            return operandValues.firstElement()+operandValues.lastElement();
        return operandValues.firstElement()-operandValues.lastElement();
    }
    /**
     * dice se il valore è valido
     * @param s valore
     * @return ritorna se la stringa passata come parametro è valida (formula)
     */
    public boolean isValueValid(String s) {
        if(isValueDouble(s) || isValueAddableCell(s))
            return true;
        return false;
    }
    /**
     * ritorrna true se il tipo di dato messo in info è 
     * @param s stringa contenente il numero
     * @return  true se value è double
     */
    public boolean isValueDouble(String s) {
        try {
            Double a = Double.parseDouble(s);
            operandValues.add(a);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    /**
     * ritorna true se la stringa passata in input è sommabile 
     * @param s stringa 
     * @return  se la stringa passata in input è sommabile 
     */
    public boolean isValueAddableCell(String s) {
        try {
            int column = s.charAt(0) - 'A';
            if(column <0 || column >= 26)
                return false;
            try {
                try {
                    int row = Integer.parseInt(s.substring(1))-1;
                    if(row < 0 || row >= 100)
                        return false;
                    if(m[row][column].isNumeric()) {
                        try {
                            Double info = Double.parseDouble(m[row][column].getInfo().toString());
                            operandValues.add(info);
                            return true;
                        } catch (Exception e) {}                        
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
        operandValues = new Vector<Double>();
        if (value.toString().startsWith("=")) {
            String [] parts = value.substring(1).split("[+-]");
            if(parts.length != 2)
                return false;
            if(value.contains("+"))
                operator = OperatorEnum.Addition;
            else   
                operator = OperatorEnum.Subtraction; 
            for (String x : parts) {
                if(!isValueValid(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}