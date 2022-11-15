import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class LectValArchivoTest {

    @Test
    @DisplayName("Checar si el archivo JSON existe")
    public void checarExistenciaJSON(){
        File file=new File("src/JsonFile.json");
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("Checar si el archivo JSON es válido.")
    public void validacionJSON(){
        JSONParser parser = new JSONParser();
        String txtJSON=LectValArchivo.getJSONContent();
        try {
            JSONObject obj=new JSONObject((JSONObject) parser.parse(txtJSON));
            assertTrue(obj.containsKey("employees"));
            JSONArray jsonArray=(JSONArray) obj.get("employees");
            if (jsonArray.size()==0){
                assertEquals(0, jsonArray.size());
            }else{
                for (Object empleado : jsonArray ) {
                    JSONObject testObj = (JSONObject) empleado;
                    assertTrue(testObj.containsKey("id"));
                    assertTrue(testObj.containsKey("firstName"));
                    assertTrue(testObj.containsKey("lastName"));
                    assertTrue(testObj.containsKey("photo"));
                }
            }
        } catch (ParseException e) {
            fail("Validation failed with: " + e.getMessage());
        }


    }

}