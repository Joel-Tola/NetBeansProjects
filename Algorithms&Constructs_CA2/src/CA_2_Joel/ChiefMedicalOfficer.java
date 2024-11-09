package CA_2_Joel;

public class ChiefMedicalOfficer extends Manager {
    public ChiefMedicalOfficer(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getManagerType() {
        return "Chief Medical Officer";
    }
}
