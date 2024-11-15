package CA_2;

public class Nurse extends Employee {
    public Nurse(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getRole() {
        return "Nurse";
    }
}
