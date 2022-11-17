import Entities.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeManagerTest {

    private String employeesJSONContent;
    private EmployeeManager employeeManager;

    @BeforeAll
    public void readEmployeesFromJSON(){
        this.employeesJSONContent = LectValArchivo.getJSONContent();
    }

    @BeforeEach
    public void createEmployeesManager(){
        this.employeeManager = new EmployeeManager();
    }

    @Test
    @DisplayName("Conversión de JSON a lista de objetos.")
    public void shouldConvertToObjectList() {
        try {
            this.employeeManager.importFromJSONArray((JSONArray) ((JSONObject) new JSONParser().parse(employeesJSONContent)).get("employees"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Entities.Employee", this.employeeManager.getEmployeesList().get(0).getClass().getName());
    }

    @Test
    @DisplayName("Adición de 1 employee.")
    public void shouldAddOneEmployee() {
        Employee testEmployee = new Employee("6", "Daniel", "Moreno", "justAPhotoURL");
        this.employeeManager.addEmployee(testEmployee);
        assertFalse(this.employeeManager.getEmployeesList().isEmpty());
        assertEquals(1, this.employeeManager.getEmployeesList().size());
    }
}
