package Commands;

import Tools.Receiver;

import java.util.Stack;

public class UndoCommand implements Command {

    private Stack<Command> history;

    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.history = history;
    }

    @Override
    public void execute() {
        if (!history.isEmpty()) {
            Command lastCmd = history.pop();
            lastCmd.undo();
        } else {
            System.out.println("Nothing to undo");
        }
    }

    @Override
    public void undo() {

    }
}
