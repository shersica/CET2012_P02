package Commands;

import CustomException.AppException;
import Tools.Receiver;

public class DeleteCommand implements Command {

    private Receiver receiver;
    private String indexStr;
    private String[] deletedEmployee;

    private int index;

    public DeleteCommand(Receiver receiver, String indexInput) {
        this.receiver = receiver;
        this.indexStr = indexInput;
    }

    @Override
    public void execute() throws AppException {
        if (!indexStr.matches("\\d+")) {
            throw new AppException("Unable to delete, please provide a number to be deleted.");
        }

        // if valid, proceed with parsing it
        index = Integer.parseInt(indexStr);

        if (index > receiver.getEmployeeCount() || index < 0) {
            throw new AppException("Unable to delete, invalid index.");
        }

        deletedEmployee = receiver.delete(index-1);
        System.out.println("Delete");
    }

    @Override
    public void undo() {
        receiver.add(index, deletedEmployee);
//        System.out.println("Undo");
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
