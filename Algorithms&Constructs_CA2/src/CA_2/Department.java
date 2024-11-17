package CA_2;

import java.util.List;

/**
 * The Department class serves as a base class for all departments, handling
 * generic tasks like managing employees and providing a department overview.
 */
public class Department {
    private String deptName;

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void addEmployee(Employee employee) {
        throw new UnsupportedOperationException("Unimplemented method 'addEmployee'");
    }

    public void removeEmployee(Employee employee) {
        throw new UnsupportedOperationException("Unimplemented method 'removeEmployee'");
    }

    public List<Employee> listEmployees() {
        throw new UnsupportedOperationException("Unimplemented method 'listEmployees'");
    }

    public String getDepartmentOverview() {
        throw new UnsupportedOperationException("Unimplemented method 'getDepartmentOverview'");
    }
}