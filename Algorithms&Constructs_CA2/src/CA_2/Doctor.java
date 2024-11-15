package CA_2;

public class Doctor extends Employee {
    public Doctor(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getRole() {
        return "Doctor";
    }
}
