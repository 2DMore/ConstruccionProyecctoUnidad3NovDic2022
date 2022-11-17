import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TablaDatos {
    private JTable table1;
    private JPanel panel;
    String[] encabezado={"ID","First name", "Last name","Photo"};

    private EmployeeManager employeeManager;


    public static void main(String[] args) {
        JFrame jf= new JFrame("No Dummies anymore");
        jf.setSize(600,500);
        jf.add(new TablaDatos().panel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        employeeManager = new EmployeeManager();
        populateEmployeeManager();
        Object[][] dummyData= employeeManager.getEmployeesAsObjectMatrix();
        DefaultTableModel model=new DefaultTableModel(dummyData,encabezado);
        model.setColumnIdentifiers(encabezado);
        table1=new JTable(model);
    }

    private void populateEmployeeManager(){
        try {
            this.employeeManager.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(LectValArchivo.getJSONContent())).get("employees"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
