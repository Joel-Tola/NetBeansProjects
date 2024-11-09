package CA_2_Joel;

public class AdministrativeManager extends Manager {
    public AdministrativeManager(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getManagerType() {
        return "Administrative Manager";
    }
}