package CA_2;

/**
 * The CMO focuses on high-level decision-making, policy updates, and quality
 * assurance, justifying a distinct role from other managers.
 */
public class ChiefMedicalOfficer extends Manager {
    public ChiefMedicalOfficer(String name, Department department) {
        super(name, department);
    }

    @Override
    public String getManagerType() {
        return "Chief Medical Officer";
    }

    public void delegateTask(String employeeName, String taskDescription) {
        throw new UnsupportedOperationException("Unimplemented method 'delegateTask'");
    }

    public void reviewPerformance(String employeeName) {
        throw new UnsupportedOperationException("Unimplemented method 'reviewPerformance'");
    }

    public void conductMeeting(String meetingAgenda) {
        throw new UnsupportedOperationException("Unimplemented method 'conductMeeting'");
    }

    public void handleComplaint(String complaintDetails) {
        throw new UnsupportedOperationException("Unimplemented method 'handleComplaint'");
    }
}
