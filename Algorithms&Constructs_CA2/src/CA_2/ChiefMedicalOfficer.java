package CA_2;

public class ChiefMedicalOfficer extends Manager {
    public ChiefMedicalOfficer(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getManagerType() {
        return "Chief Medical Officer";
    }
}
