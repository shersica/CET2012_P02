package Tools;

import Commands.Command;
import CustomException.AppException;

import java.util.Stack;

/**
 * Invoker class: responsible for initiating the commands. It must have a
 * field to store the reference to a command object. The Invoker triggers the commands
 * instead of sending the request directly to the receiver.
 */
public class Invoker {
    /**
     * Stack that stores the commands for execution
     */
    private Command[] cmdToExecute;

    /**
     * Sets the commands to be executed by the invoker.
     * @param cmdToExecute a stack that stores {@link Command} objects to be executed
     */
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    /**
     * Executes all commands in the {@code cmdToExecute} stack.
     * @param history a stack of previously executed commands (required for {@code undo} command)
     */
    public void executeCommand(Stack<Command> history) {
        for (Command cmd : cmdToExecute) {
            try {
                cmd.execute();
                if(cmd.isUndoable()) {
                    history.push(cmd);
                }
            } catch (AppException e) {
//                System.err.println(e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }
}
