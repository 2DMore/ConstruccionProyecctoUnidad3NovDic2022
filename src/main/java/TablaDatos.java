import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TablaDatos {
    private JTable table1;
    private JPanel panel;
    String[] encabezado={"ID","First name", "Last name","Photo"};

    public static void main(String[] args) {
        JFrame jf= new JFrame("Dummies");
        jf.setSize(600,500);
        jf.add(new TablaDatos().panel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        Object[][] dummyData={
                {"1","Tom","Cruise","https://jsonformatter.org/img/tom-cruise.jpg"},
                {"2", "Maria", "Sharapova", "https://jsonformatter.org/img/Maria-Sharapova.jpg"},
                {"3", "Robert", "Downey Jr.","https://jsonformatter.org/img/Robert-Downey-Jr.jpg"}
        };
        DefaultTableModel model=new DefaultTableModel(dummyData,encabezado);
        model.setColumnIdentifiers(encabezado);
        table1=new JTable(model);
    }
}
