package CA_2_Joel;

public class AdministrativeStaff extends Employee {
    public AdministrativeStaff(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getRole() {
        return "Administrative Staff";
    }
}
