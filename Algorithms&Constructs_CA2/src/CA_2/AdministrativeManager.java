package CA_2;

public class AdministrativeManager extends Manager {
    public AdministrativeManager(String name) {
        super(name);
    }

    @Override
    public String getManagerType() {
        return "Administrative Manager";
    }
}