package CA_2;

/**
 * Nurses focus on patient care, medication, and assisting doctors, which
 * requires specialized methods distinct from other roles.
 */
public class Nurse extends Employee {
    public Nurse(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    public void assistDoctor(String doctorName) {
        throw new UnsupportedOperationException("Unimplemented method 'assistDoctor'");
    }

    public void administerMedication(String medication) {
        throw new UnsupportedOperationException("Unimplemented method 'administerMedication'");
    }

    public void checkVitals(String patientName) {
        throw new UnsupportedOperationException("Unimplemented method 'checkVitals'");
    }

    public void managePatientRecords() {
        throw new UnsupportedOperationException("Unimplemented method 'managePatientRecords'");
    }
}
