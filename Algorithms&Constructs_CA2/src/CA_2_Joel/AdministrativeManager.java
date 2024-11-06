package CA_2_Joel;

public class AdministrativeManager extends Manager {
    public AdministrativeManager(String name) {
        super(name);
    }

    @Override
    public String getManagerType() {
        return "Administrative Manager";
    }
}