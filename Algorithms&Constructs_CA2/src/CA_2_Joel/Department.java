package CA_2_Joel;

public abstract class Department {
    protected String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    // Getter
    public String getDepartmentName() {
        return departmentName;
    }
}