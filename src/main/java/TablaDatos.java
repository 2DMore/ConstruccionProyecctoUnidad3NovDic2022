import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class TablaDatos {
    private JTable table1;
    private JPanel panel;
    private JButton modButton;
    private JButton deleteEmployeeButton;
    String[] encabezado={"ID","First name", "Last name","Photo"};

    private EmployeeManager employeeManager;

    public TablaDatos(JFrame jf){
        modButton.addActionListener(e -> {
            JFrame modJF= new JFrame("Modify Employees");
            modJF.setSize(600,500);
            modJF.getContentPane().add(new ModificacionReg(modJF).getPanelMod());
            modJF.setVisible(true);
            modJF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //Close current window
            jf.setVisible(false);
            jf.dispose();
        });
        deleteEmployeeButton.addActionListener(e -> {
            JFrame delJF= new JFrame("Delete Employees");
            delJF.setSize(600,500);
            delJF.getContentPane().add(new EliminacionReg(delJF).getPanelMod());
            delJF.setVisible(true);
            delJF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //Close current window
            jf.setVisible(false);
            jf.dispose();
        });
    }

    public static void main(String[] args) {
        JFrame jf= new JFrame("No Dummies anymore");
        jf.setSize(600,500);
        jf.add(new TablaDatos(jf).panel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        employeeManager = new EmployeeManager();
        populateEmployeeManager();
        Object[][] dummyData= employeeManager.getEmployeesAsObjectMatrix();
        changeURLtoImages(dummyData);
        DefaultTableModel model=new DefaultTableModel(dummyData,encabezado){
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        model.setColumnIdentifiers(encabezado);
        table1=new JTable(model);
    }


    private void changeURLtoImages(Object[][] employeesMatrix){
        int i = 0;
        for (Object[] employee: employeesMatrix) {

            try {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(new URL((String) employee[3])));
                Image image = getScaledImage(imageIcon.getImage(), 50, 50);
                imageIcon = new ImageIcon(image);
                Icon icon = (Icon) imageIcon ;
                employee[3] = icon ;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    private void populateEmployeeManager(){
        try {
            this.employeeManager.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(LectValArchivo.getJSONContent())).get("employees"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public JPanel getPanel(){
        return panel;
    }
}
