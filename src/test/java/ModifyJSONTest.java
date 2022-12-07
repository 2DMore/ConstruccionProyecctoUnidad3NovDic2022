import Entities.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ModifyJSONTest {

    @Test
    public void modifyEmployee() {
        try{
            boolean exists=false;
            String newID="3";
            String newFName="Bruce";
            String newLName="Lee";
            String newPhoto="https://jsonformatter.org/img/tom-cruise.jpg";
            //System.out.println(newPhoto);
            EmployeeManager em=new EmployeeManager();
            em.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(LectValArchivo.getJSONContent())).get("employees"));
            for(Employee employee:em.getEmployeesList()){
                if (employee.getId().equals(newID)) {
                    exists = true;
                    break;
                }
            }
            //It will throw an exception if the new URL is not valid.
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new URL(newPhoto)));

            assertTrue(exists);
            assertFalse(newFName.isBlank());
            assertFalse(newLName.isBlank());
            assertFalse(newPhoto.isBlank());
            ModifyJSON.ModifyEmployee(newID,newFName,newLName,newPhoto);
        }catch (Exception e){
            e.printStackTrace();
            fail("Invalid photo URL");
        }

    }

    @Test
    public void deleteEmployee() throws ParseException {
        EmployeeManager em=new EmployeeManager();
        em.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(LectValArchivo.getJSONContent())).get("employees"));
        int firstSize = em.getEmployeesList().size();

        String idOfEmployeeToDelete = em.getEmployeesList().get(0).getId();
        ModifyJSON.deleteEmployee(idOfEmployeeToDelete);

        em.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(LectValArchivo.getJSONContent())).get("employees"));
        int newSize = em.getEmployeesList().size();

        assertNotEquals(newSize, firstSize);
        assertEquals(++newSize, firstSize);
    }
    @Test
    public void addEmployee() throws ParseException {
        EmployeeManager em=new EmployeeManager();
        em.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(LectValArchivo.getJSONContent())).get("employees"));
        int firstSize = em.getEmployeesList().size();
        try{
            String newFName="Bruce";
            String newLName="Lee";
            String newPhoto="https://jsonformatter.org/img/tom-cruise.jpg";
            //It will throw an exception if the new URL is not valid.
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new URL(newPhoto)));
            assertFalse(newFName.isBlank());
            assertFalse(newLName.isBlank());
            assertFalse(newPhoto.isBlank());
            ModifyJSON.addEmployee(newFName,newLName,newPhoto);
        }catch(Exception e){
            e.printStackTrace();
            fail("Invalid photo URL");
        }
        em.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(LectValArchivo.getJSONContent())).get("employees"));
        int newSize = em.getEmployeesList().size();

        assertNotEquals(newSize, firstSize);
        assertEquals(newSize, ++firstSize);
    }
}