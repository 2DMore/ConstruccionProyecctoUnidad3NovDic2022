import Entities.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;

public class ModifyJSON {
    public static void ModifyEmployee(String newID,String newFName,String newLName,String newPhoto){
        EmployeeManager em=new EmployeeManager();
        try {
            em.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(LectValArchivo.getJSONContent())).get("employees"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        };
        JSONObject employees=new JSONObject();
        JSONArray employArray=new JSONArray();
        for(Employee employee:em.getEmployeesList()){
            JSONObject employArrayElement=new JSONObject();
            if(employee.getId().equals(newID)){
                employee.setFirstName(newFName);
                employee.setLastName(newLName);
                employee.setPhoto(newPhoto);
            }
            employArrayElement.put("id",employee.getId());
            employArrayElement.put("firstName",employee.getFirstName());
            employArrayElement.put("lastName",employee.getLastName());
            employArrayElement.put("photo",employee.getPhoto());
            employArray.add(employArrayElement);
        }
        employees.put("employees",employArray);

        try {
            FileWriter fw=new FileWriter("src/JsonFile.json");
            fw.write(employees.toString());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ModifyEmployee("2","Pancho","Villa","https://jsonformatter.org/img/Maria-Sharapova.jpg");
    }
}