package Tools;

import Commands.AddCommand;
import Commands.Command;
import Commands.DeleteCommand;
import Commands.UpdateCommand;
import CustomException.AppException;

import java.util.Stack;

public class Invoker {
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    public void executeCommand(Stack<Command> history) {
        for (Command cmd : cmdToExecute) {
            try {
                cmd.execute();
                if(cmd.isUndoable()) {
                    history.push(cmd);
                }
            } catch (AppException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
