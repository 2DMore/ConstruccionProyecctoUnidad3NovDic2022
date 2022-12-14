import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Exceptions.LectValArchivoException;

import java.io.FileReader;
import java.io.IOException;

public class LectValArchivo {
    public static void main(String[] args) throws ParseException {
        System.out.println(getJSONContent());
    }
    public static String getJSONContent(){
        Object ob;
        {
            try {
                ob = new JSONParser().parse(new FileReader("src/JsonFile.json"));
            } catch (IOException | ParseException e) {
                throw new LectValArchivoException(e);
            }
        }
        JSONObject jsonArch=(JSONObject)ob;
        return jsonArch.toString();
    }

}