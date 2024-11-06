package CA_2;

public class AdministrativeStaff extends Employee {
    public AdministrativeStaff(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getRole() {
        return "Administrative Staff";
    }
}
