package Tools;

import Commands.Command;
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
            } catch (AppException e) {
                System.err.println(e.getMessage());
            }
            history.push(cmd);
        }
    }
}
