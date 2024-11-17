package CA_2;

import java.util.List;

/**
 * The Pediatrics department caters to children's health, involving specialized
 * tasks such as vaccinations, growth monitoring, and parental education.
 */
public class Pediatrics extends Department {
    public Pediatrics() {
        super("Pediatrics");
    }

    public void scheduleVaccination(String childName, String vaccineName) {
        throw new UnsupportedOperationException("Unimplemented method 'allocateBudget'");
    }

    public void monitorGrowth(String childName) {
        throw new UnsupportedOperationException("Unimplemented method 'scheduleCardiacProcedure'");
    }

    public void conductPediatricCheckup(String childName) {
        throw new UnsupportedOperationException("Unimplemented method 'conductECGTest'");
    }

    public List<String> provideParentalGuidance(String guidanceTopic) {
        throw new UnsupportedOperationException("Unimplemented method 'manageCardiacCases'");
    }
}