package CA_2;

/**
 * Managers have oversight and supervisory roles, requiring methods for
 * delegation, review, and conflict resolution.
 */
public abstract class Manager extends Employee {
    public Manager(String name, Department department) {
        super(name, department);
    }

    public abstract String getManagerType();

    @Override
    public String getRole() {
        return getManagerType();
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
