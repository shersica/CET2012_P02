package Commands;

import Tools.Receiver;

import java.util.Stack;

/**
 * UndoCommand: a concrete implementation of the {@link Command} interface
 * that reverses the most recent command in the history stack.
 *
 *  It retrieves the last executed command from history and invokes its {@link #undo()} method.
 */
public class UndoCommand implements Command {

    /**
     * Stores the command history
     */
    private Stack<Command> history;

    /**
     * Constructor for the UndoCommand class.
     * @param receiver the receiver instance responsible for executing the command
     * @param history_ commands previously executed
     */
    public UndoCommand(Receiver receiver, Stack<Command> history_) {
        this.history = history_;
    }

    /**
     * Executes the undo operation.
     * <p>
     * If the command history is not empty, this method retrieves and removes
     * the most recently executed command from the history stack and calls its
     * {@code undo} method to reverse its effect.
     * If the history is empty, a message is printed indicating that there is
     * nothing to undo.
     * </p>
     */
    @Override
    public void execute() {
        if (!history.isEmpty()) {
            Command lastCmd = history.pop();
            lastCmd.undo();
            System.out.println("Undo");
        } else {
            System.out.println("Nothing to undo");
        }
    }

    /**
     * Not applicable for this command.
     */
    @Override
    public void undo() {

    }

    /**
     * {@inheritDoc}
     * @return {@code false} always as redo is not applicable for this application.
     */
    @Override
    public boolean isUndoable() {
        return false;
    }
}
