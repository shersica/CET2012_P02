public class UpdateCommand implements Command {
    private Receiver receiver;
    private Employee employee;
    private int index;

    public UpdateCommand(Receiver receiver, int index, Employee employee) {
        this.receiver = receiver;
        this.index = index;
        this.employee = employee;
    }

    @Override
    public void execute() {
        receiver.update(index, employee);
    }
}
