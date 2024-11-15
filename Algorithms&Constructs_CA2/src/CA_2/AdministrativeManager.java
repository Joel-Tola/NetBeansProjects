package CA_2;

public class AdministrativeManager extends Manager {
    public AdministrativeManager(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getManagerType() {
        return "Administrative Manager";
    }
}
