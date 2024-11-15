package CA_2;

public abstract class Manager extends Employee {
    public Manager(String name, Department department) {
        super(name, department);
    }

    public abstract String getManagerType();

    @Override
    public String getRole() {
        return getManagerType();
    }
}
