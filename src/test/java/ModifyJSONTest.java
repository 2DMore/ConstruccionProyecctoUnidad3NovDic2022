import Entities.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
            String newID="2";
            String newFName="Bruce";
            String newLName="Lee";
            String newPhoto="https://jsonformatter.org/img/tom-cruise.jpg";
            System.out.println(newPhoto);
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
}