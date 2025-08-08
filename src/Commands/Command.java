package Commands;

import CustomException.AppException;

/**
 * Command interface: defines the contract for implementing
 * commands using the Command Design Pattern.
 * <br>
 * Commands include: {@link AddCommand}, {@link DeleteCommand},
 * {@link UpdateCommand}, {@link ListCommand}, and {@link UpdateCommand}.
 */
public interface Command {
    /**
     * Executes the command after validations (if applicable).
     *
     * @throws AppException if the inputted data does not follow the specified format.
     */
    void execute() throws AppException;

    /**
     * Undoes the command if it is undoable.
     */
    void undo();

    /**
     * Indicates whether the command supports undo.
     * @return {@code true} for {@code add}, {@code delete}, {@code update} methods
     * and {@code false} for {@code list} and {@code undo} methods.
     */
    boolean isUndoable();
}