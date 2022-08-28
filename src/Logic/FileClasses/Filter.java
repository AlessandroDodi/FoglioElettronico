package Logic.FileClasses;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Filter extends FileFilter{
    /**
     * Costruttore di classe Filter che richiama il costruttore base di FileFilter
     */
    public Filter() {
        super();
    }

    @Override
    public boolean accept(File f) {
        if (f.isFile() && f.getName().endsWith(".sheet"))
            return true;
        return false;
    }

    @Override
    public String getDescription() {
        return ".sheet (Worksheet files)";
    }
    
}
