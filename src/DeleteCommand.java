public class DeleteCommand implements Command {

    private Receiver receiver;
    private int index;

    public DeleteCommand(Receiver receiver, int index) {
        this.receiver = receiver;
        this.index = index;
    }

    @Override
    public void execute() {
        receiver.delete(index);
    }
}
