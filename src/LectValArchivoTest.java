package src;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

class LectValArchivoTest {

    @Test
    @DisplayName("Checar si el archivo JSON existe")
    public void checarExistenciaJSON(){
        File file=new File("src/JsonFile.json");
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("Checar si el archivo JSON tiene errores")
    public void validacionJSON(){
        JSONParser parser = new JSONParser();
        Assertions.assertThrows(RuntimeException.class, () -> {
            String txtJSON=LectValArchivo.getJSONContent();
            JSONObject obj=new JSONObject((JSONObject) parser.parse(txtJSON));
        });


    }

}