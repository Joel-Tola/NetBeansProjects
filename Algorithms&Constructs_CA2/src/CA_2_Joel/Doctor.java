package CA_2_Joel;

public class Doctor extends Employee {
    public Doctor(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getRole() {
        return "Doctor";
    }
}
