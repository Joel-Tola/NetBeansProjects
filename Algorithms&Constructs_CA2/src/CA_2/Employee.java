package CA_2;

/**
 * Employee is the base class for all the type of employees: Doctor, Nurse,
 * Administrative Staff, and the distinct types of Manages: ChiefMedicalOfficer,
 * NursingManager, AdministrativeManager
 */
public class Employee {
    protected String name;
    protected Department department;
    protected String role;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public String getRole() {
        return "No Role";
    };

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}