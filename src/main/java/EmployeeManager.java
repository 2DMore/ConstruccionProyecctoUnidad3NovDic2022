import Entities.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    public static void main(String[] args) {
        //
    }


    private final List<Employee> employeesList;

    public EmployeeManager() {
        employeesList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        this.employeesList.add(employee);
    }

    public void importFromJSONArray(JSONArray employeesJSONArray) {
        for (Object employee : employeesJSONArray) {
            JSONObject employeeJSONObject = (JSONObject) employee;
            Employee tempEmployee = new Employee(
                    (String) employeeJSONObject.get("id"),
                    (String) employeeJSONObject.get("firstName"),
                    (String) employeeJSONObject.get("lastName"),
                    (String) employeeJSONObject.get("photo")
            );
            this.addEmployee(tempEmployee);
        }
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public Object[][] getEmployeesAsObjectMatrix(){
        Object[][] employeesMatrix = new Object[this.getEmployeesList().size()][];
        int i = 0;
        for (Employee employee: getEmployeesList()) {
            employeesMatrix[i] = new Object[]{employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getPhoto()};
            i++;
        }
        return employeesMatrix;
    }
}
