package CA_2_Joel;

public abstract class Manager {
    protected String name;
    protected Department department;

    public Manager(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public abstract String getManagerType();

    // Getter
    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }
}

