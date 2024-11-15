package CA_2;

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