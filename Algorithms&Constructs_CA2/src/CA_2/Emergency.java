package CA_2;

import java.util.List;

/**
 * The Emergency department focuses on urgent care, requiring methods for
 * triage, resource allocation, and emergency team management.
 */
public class Emergency extends Department {
    public Emergency() {
        super("Emergency");
    }

    public void handleEmergencyCase(String caseDetails) {
        throw new UnsupportedOperationException("Unimplemented method 'allocateBudget'");
    }

    public void assignTriageLevel(String patientName, int triageLevel) {
        throw new UnsupportedOperationException("Unimplemented method 'scheduleCardiacProcedure'");
    }

    public void callEmergencyResponseTeam() {
        throw new UnsupportedOperationException("Unimplemented method 'conductECGTest'");
    }

    public List<String> getEmergencyStatistics() {
        throw new UnsupportedOperationException("Unimplemented method 'manageCardiacCases'");
    }
}