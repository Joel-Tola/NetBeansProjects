package CA_2_Joel;

public class NursingManager extends Manager {
    public NursingManager(String name) {
        super(name);
    }

    @Override
    public String getManagerType() {
        return "Nursing Manager";
    }
}
