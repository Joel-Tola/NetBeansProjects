package CA_2;

/**
 * Nursing managers handle operational and training aspects of nursing staff,
 * emphasizing management and leadership in their role.
 */
public class AdministrativeManager extends Manager {
    public AdministrativeManager(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getManagerType() {
        return "Administrative Manager";
    }

    public void allocateBudget(String departmentName, double amount) {
        throw new UnsupportedOperationException("Unimplemented method 'allocateBudget'");
    }

    public void manageHRRequests(String requestType) {
        throw new UnsupportedOperationException("Unimplemented method 'manageHRRequests'");
    }

    public void overseeFacilityMaintenance(String facility) {
        throw new UnsupportedOperationException("Unimplemented method 'overseeFacilityMaintenance'");
    }

    public void implementAdminPolicies(String policy) {
        throw new UnsupportedOperationException("Unimplemented method 'implementAdminPolicies'");
    }
}
