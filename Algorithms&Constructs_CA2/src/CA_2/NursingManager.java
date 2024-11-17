package CA_2;

/**
 * Nursing managers handle operational and training aspects of nursing staff,
 * emphasizing management and leadership in their role.
 */
public class NursingManager extends Manager {
    public NursingManager(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getManagerType() {
        return "Nursing Manager";
    }

    public void assignShifts(String nurseName, String shiftTime) {
        throw new UnsupportedOperationException("Unimplemented method 'assignShifts'");
    }

    public void trainNurses(String trainingProgram) {
        throw new UnsupportedOperationException("Unimplemented method 'trainNurses'");
    }

    public void evaluateNursePerformance(String nurseName) {
        throw new UnsupportedOperationException("Unimplemented method 'evaluateNursePerformance'");
    }

    public void manageEmergencyResponseTeam() {
        throw new UnsupportedOperationException("Unimplemented method 'manageEmergencyResponseTeam'");
    }
}
