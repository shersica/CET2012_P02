package Commands;

import Tools.Receiver;

public class UpdateCommand implements Command {
    private Receiver receiver;
    private int index;
    private String params;

    public UpdateCommand(Receiver receiver, int index, String params) {
        this.receiver = receiver;
        this.index = index;
        this.params = params;
    }

    @Override
    public void execute() {
        receiver.update(index, params);
    }
}
