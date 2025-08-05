package Commands;

import Tools.Receiver;

public class DeleteCommand implements Command {

    private Receiver receiver;
    private String index;
    private String[] deletedEmployee;

    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        //validate index
        this.index = index;
    }

    @Override
    public void execute() {
        deletedEmployee = receiver.delete(Integer.parseInt(index)-1);

    }

    @Override
    public void undo() {
        receiver.undoDelete(Integer.parseInt(index)-1,deletedEmployee);
    }
}
