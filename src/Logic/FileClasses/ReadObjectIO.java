package Logic.FileClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadObjectIO {
    String path;
    /**
     * Costruttore di ReadObjectIO
     * @param path percorso del file da cui leggere le celle 
     */
    public ReadObjectIO(String path) { 
        this.path = path;
    }
    /**
     * Legge un file e salva su un array di stringhe i valori delle celle
     * @return Array di stringhe contenente i valori delle celle
     */
    public String[] ReadObjectToFile() {
        String[]s  = new String[100*26];
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            int i = 0;  
            while (i < (100*26) && myReader.hasNextLine()) {
              String data = myReader.nextLine();
              s[i] = data;
              i++;
            }
            myReader.close();
            return s;
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred. reading the file");
            return s;
          }
    }
}