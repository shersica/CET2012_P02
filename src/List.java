public class List implements Command {

    private Receiver receiver;

    public List(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.list();
    }
}
