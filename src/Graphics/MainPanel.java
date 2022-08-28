package Graphics;
import java.awt.*;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import Cell.Cell;
import Cell.TextualCell;
import Graphics.Center.MainJTable;
import Graphics.North.AutoSave;
import Graphics.North.InfoField;
import Graphics.West.RowHeader;
import Logic.MainTableModel;
import Logic.FileClasses.Filter;
import Logic.FileClasses.ReadObjectIO;
import Logic.FileClasses.WriteObjectIO;
import java.util.Timer;
import java.util.TimerTask;
public class MainPanel extends JPanel {
    /**
     * Costruttore di MainPanel. Contiene tutte le definizioni dei componenti che fanno parte del pannello principale
     */
    public MainPanel() {
        Timestamp ts = Timestamp.from(Instant.now());
        String defaultFilePath =FileSystemView.getFileSystemView().getDefaultDirectory().getPath().toString() + "\\" + "JSheet -"+ts.toString().replace(".", "_").replace(":", "_")+ ".jsheet";
        
        this.setBackground(Color.white);
        int numCol = 26;
        int numRow = 100;
        Cell<?>[][] m = new Cell[numRow][numCol];
        for(int i = 0; i < numRow; i++) 
            for(int j = 0; j < numCol; j++)
                try {
                    m[i][j] = new TextualCell(i, j, "", m);
                } catch (Exception e) {
                }
        setBackground(Color.BLACK);       
        MainTableModel dataModel = new MainTableModel(m, numRow, numCol);
        JTable mainTable = new MainJTable(dataModel);
        JScrollPane scrollPane = new JScrollPane(mainTable);
        JTable rowTable = new RowHeader(mainTable);
        scrollPane.setRowHeaderView(rowTable);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,rowTable.getTableHeader());
        InfoField f = new InfoField(m);
        mainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable= (JTable)e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    f.setText(jTable.getColumnName(column)+(row+1)+" = "+m[row][column].getInfo().toString());
                }
            }
        });
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        
        JButton save = new JButton("Save");
        JCheckBox discendent = new JCheckBox("discendente");
        discendent.setSelected(true);
        JButton importa = new JButton("import");
        JButton orderButton = new JButton("Order");
        JLabel emptyLabel = new JLabel("");
        AutoSave autosave = new AutoSave();
        JLabel orderLabel = new JLabel("Order column:");
        Character col[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        JComboBox<Character> orderlist = new JComboBox<Character>(col);
        JPanel firstGrid = new JPanel();
        northPanel.setLayout(new BorderLayout());
        firstGrid.setLayout(new GridLayout(1, 8));
        firstGrid.add(save);
        firstGrid.add(autosave);
        firstGrid.add(orderLabel);
        firstGrid.add(orderlist);
        firstGrid.add(discendent);
        firstGrid.add(orderButton);
        firstGrid.add(emptyLabel);
        firstGrid.add(importa);
        northPanel.add(firstGrid, BorderLayout.NORTH);
        northPanel.add(f, BorderLayout.SOUTH );
        
        this.add(northPanel,  BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        WriteObjectIO wrO = new WriteObjectIO();
        ActionListener saveActionListener = new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(new Filter());
                fileChooser.setSelectedFile(new File("Nuovo file.sheet"));
                int result = fileChooser.showOpenDialog(northPanel);
                if(result == 0) {
                    File f = fileChooser.getSelectedFile();
                    int ConfirmationResult = 0;
                    if(f.isFile())
                        ConfirmationResult = JOptionPane.showConfirmDialog(fileChooser,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_OPTION);
                    if(ConfirmationResult == 0) {
                        File selectedFile = fileChooser.getSelectedFile();
                        wrO.setPath(selectedFile.getAbsolutePath());
                        wrO.WriteObjectToFile(m);
                        File myObj = new File(defaultFilePath); 
                        myObj.delete();
                    }
                }
            ;
            } 
          };
        save.addActionListener(saveActionListener);
        autosave.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                if(!autosave.isSelected()) {
                    File myObj = new File(defaultFilePath); 
                    myObj.delete();
                }
            }
        });
        importa.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new Filter());
            int result = fileChooser.showOpenDialog(northPanel);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                ReadObjectIO obR = new ReadObjectIO(selectedFile.getAbsolutePath());
                String[] s = obR.ReadObjectToFile();
                ((MainTableModel) dataModel).setMatrix(s);
            }
        } 
        } );
        orderButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                dataModel.orderColumn((Character) orderlist.getSelectedItem(), discendent.isSelected());
            } 
        } );
        Timer timer = new Timer();
        WriteObjectIO wrAutoSave = new WriteObjectIO(defaultFilePath);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(autosave.isSelected()) {
                    wrAutoSave.WriteObjectToFile(m);
                }
            }
        }, 0, 2000);
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
            if(autosave.isSelected()) {
                File myObj =  new File(defaultFilePath); 
                ReadObjectIO obR = new ReadObjectIO(myObj.getAbsolutePath());
                String[] s = obR.ReadObjectToFile();
                ((MainTableModel) dataModel).setMatrix(s);
                if(new File(wrO.getPath()).isFile()){
                    new File(wrO.getPath()).delete();
                    WriteObjectIO wrAutoSave = new WriteObjectIO(wrO.getPath());
                    wrAutoSave.WriteObjectToFile(m);
                }
            }
            File myObj = new File(defaultFilePath); 
            myObj.delete();
            }
        });
    }
}   