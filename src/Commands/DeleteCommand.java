package Commands;

import CustomException.AppException;
import Tools.Receiver;

/**
 * DeleteCommand: a concrete implementation of the {@link Command} interface.
 * Removes the employee from the list based on the index provided.
 */
public class DeleteCommand implements Command {
    /**
     * The {@link Receiver} instance responsible for executing the command.
     */
    private Receiver receiver;
    /**
     * Raw index in {@code String} format.
     */
    private String indexStr;
    /**
     * Parsed index of the employee data to be deleted, stored as an {@code int}.
     */
    private int index;
    /**
     * Deleted employee, stored in {@code String[]} format.
     * Required for  {@code undo} command.
     */
    private String[] deletedEmployee;

    /**
     * Constructor for the DeleteCommand class.
     * @param receiver_ the receiver instance responsible for executing the command
     * @param indexStr_ raw index in {@code String} format
     */
    public DeleteCommand(Receiver receiver_, String indexStr_) {
        this.receiver = receiver_;
        this.indexStr = indexStr_;
    }

    /**
     * Executes the {@code delete} command.
     * <br>
     * The following validations are performed:
     * <ul>
     *      <li> Checks if raw index provided is a non-negative digit.
     *      If valid, parses the index as an integer, then </li>
     *      <li> Checks if the index exceeds number of entries and/or is zero. </li>
     * </ul>
     * <br>
     * Once checked, sends the parsed index to the
     * receiver for deletion of the specified index.
     * @throws AppException if any of the above validations return false
     */
    @Override
    public void execute() throws AppException {
        if (!indexStr.matches("\\d+")) {
            throw new AppException("Unable to delete, please provide a valid number to be deleted.");
        }

//        // if valid, proceed with parsing it
//        index = Integer.parseInt(indexStr);
//
//        // latter validation was "index < 0" previously
//        // TODO: CHECK THIS - I have no idea why index = 0 was not caught
//        if (index > receiver.getEmployeeCount() || index < 0) {
//            throw new AppException("Unable to delete, invalid index.");
//        }
//
//        deletedEmployee = receiver.delete(index-1);
//        System.out.println("Delete");

        // if valid, proceed with parsing it
        index = Integer.parseInt(indexStr) - 1;

        if (index >= receiver.getEmployeeCount() || index < 0) {
            throw new AppException("Unable to delete, invalid index.");
        }

        deletedEmployee = receiver.delete(index); // (index-1) -> (index)
        System.out.println("Delete");


    }

    /**
     * Undoes the delete command by inserting the deleted employee back into the previous index.
     */
    @Override
    public void undo() {
        receiver.add(index, deletedEmployee);
//        System.out.println("Undo");
    }

    /**
     * {@inheritDoc}
     * @return {@code true} always
     */
    @Override
    public boolean isUndoable() {
        return true;
    }
}