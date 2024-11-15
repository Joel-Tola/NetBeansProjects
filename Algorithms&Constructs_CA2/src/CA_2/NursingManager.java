package CA_2;

public class NursingManager extends Manager {
    public NursingManager(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getManagerType() {
        return "Nursing Manager";
    }
}
