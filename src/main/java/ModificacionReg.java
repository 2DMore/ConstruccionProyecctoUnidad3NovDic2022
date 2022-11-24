import Entities.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class ModificacionReg {
    private JTextField photoField;
    private JTextField LNameField;
    private JTextField FNameField;
    private JPanel panelMod;
    private JButton modifyButton;
    private JButton cancelButton;
    private JComboBox employBox;
    private EmployeeManager employeeManager;

    public ModificacionReg(JFrame jf) {
        cancelButton.addActionListener(e -> {
            openEmployTable();
            jf.setVisible(false);
            jf.dispose();
        });
        employeeManager=new EmployeeManager();
        try {
            this.employeeManager.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(LectValArchivo.getJSONContent())).get("employees"));
            for(Employee empleado: this.employeeManager.getEmployeesList()){
                employBox.addItem(empleado.getId());
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        modifyButton.addActionListener(e -> {
            ModifyJSON.ModifyEmployee(String.valueOf(employBox.getSelectedItem()),FNameField.getText(),LNameField.getText(),photoField.getText());
            openEmployTable();
            jf.setVisible(false);
            jf.dispose();
        });
    }
    public void openEmployTable(){
        JFrame tableJF= new JFrame("EmployeeTable");
        tableJF.setSize(600,500);
        tableJF.getContentPane().add(new TablaDatos(tableJF).getPanel());
        tableJF.setVisible(true);
        tableJF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame jf= new JFrame("Modify Employees");
        jf.setSize(600,500);
        jf.add(new ModificacionReg(jf).panelMod);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public JPanel getPanelMod(){
        return panelMod;
    }
}
