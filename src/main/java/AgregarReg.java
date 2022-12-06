import Entities.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.URL;

public class AgregarReg {
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField firstNField;
    private JTextField lastNField;
    private JTextField photoField;
    private JPanel panelAdd;

    public AgregarReg(JFrame jf) {
        cancelButton.addActionListener(e -> {
            openEmployTable();
            jf.setVisible(false);
            jf.dispose();
        });


        saveButton.addActionListener(e->{
            if(!firstNField.getText().isBlank() && !lastNField.getText().isBlank() && !photoField.getText().isBlank()){
                try{
                    ImageIcon imageIcon = new ImageIcon(ImageIO.read(new URL(photoField.getText())));
                    ModifyJSON.addEmployee(firstNField.getText(),lastNField.getText(),photoField.getText());
                    openEmployTable();
                    jf.setVisible(false);
                    jf.dispose();
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Wrong photo URL, try again");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Please fill all the text fields");
            }


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
        JFrame jf= new JFrame("Add Employee");
        jf.setSize(600,500);
        jf.add(new AgregarReg(jf).panelAdd);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public JPanel getPanelMod(){
        return panelAdd;
    }
}
