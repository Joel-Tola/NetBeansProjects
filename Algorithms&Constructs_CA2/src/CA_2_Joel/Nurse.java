package CA_2_Joel;

public class Nurse extends Employee {
    public Nurse(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getRole() {
        return "Nurse";
    }
}
