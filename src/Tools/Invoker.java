package Tools;

import Commands.Command;
import Commands.UndoCommand;

import java.util.List;
import java.util.Stack;

public class Invoker {
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    public void executeCommand(Stack<Command> history) {
        for (Command cmd : cmdToExecute) {
            cmd.execute();
            if (!(cmd instanceof UndoCommand || cmd instanceof List)) {
                history.push(cmd);
            }
        }
    }
}
