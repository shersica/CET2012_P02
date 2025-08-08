package Commands;

import Tools.Receiver;

/**
 * ListCommand: a concrete implementation of the {@link Command} interface.
 * It prints all currently stored employee data in the {@code employees} ArrayList
 * by invoking the {@code list} method in the {@link Receiver}.
 */
public class ListCommand implements Command {
    /**
     * The {@link Receiver} instance responsible for executing the command.
     */
    private Receiver receiver;

    /**
     * Constructor for the ListCommand class.
     * @param receiver_ the receiver instance responsible for executing the command
     */
    public ListCommand(Receiver receiver_) {
        this.receiver = receiver_;
    }

    /**
     * Executes the {@code list} command.
     */
    @Override
    public void execute() {
        System.out.println("List");
        receiver.list();
    }

    /**
     * Not applicable for this command.
     */
    @Override
    public void undo() {

    }

    /**
     * {@inheritDoc}
     * @return {@code false} always
     */
    @Override
    public boolean isUndoable() {
        return false;
    }
}
