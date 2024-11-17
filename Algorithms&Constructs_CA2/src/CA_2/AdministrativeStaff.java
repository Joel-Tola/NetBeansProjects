package CA_2;

/**
 * Administrative staff deal with operational logistics such as appointments,
 * billing, and inventory management, distinct from clinical tasks.
 */
public class AdministrativeStaff extends Employee {
    public AdministrativeStaff(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getRole() {
        return "Administrative Staff";
    }

    public void scheduleAppointments(String patientName, String dateTime) {
        throw new UnsupportedOperationException("Unimplemented method 'scheduleAppointments'");
    }

    public void handleBilling(String patientName) {
        throw new UnsupportedOperationException("Unimplemented method 'handleBilling'");
    }

    public void manageInventory(String itemName, int quantity) {
        throw new UnsupportedOperationException("Unimplemented method 'manageInventory'");
    }

    public void generateReports(String reportType) {
        throw new UnsupportedOperationException("Unimplemented method 'generateReports'");
    }
}
