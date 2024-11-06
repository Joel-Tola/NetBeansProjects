package CA_2_Joel;

public abstract class Employee {
    protected String name;
    protected Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public abstract String getRole();

    // Getters and setters
    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }
}
