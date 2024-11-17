package CA_2;

/**
 * We need Doctors because they have specialized roles in healthcare, focusing on diagnosis,
 * treatment, and surgeries, which differ significantly from other employees.
 */
public class Doctor extends Employee {
    public Doctor(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    public void diagnosePatient(String patientName) {
        throw new UnsupportedOperationException("Unimplemented method 'diagnosePatient'");
    }

    public void prescribeMedication(String medication) {
        throw new UnsupportedOperationException("Unimplemented method 'prescribeMedication'");
    }

    public void performSurgery(String surgeryType) {
        throw new UnsupportedOperationException("Unimplemented method 'performSurgery'");
    }

    public String getSpecialization() {
        throw new UnsupportedOperationException("Unimplemented method 'getSpecialization'");
    }
}
