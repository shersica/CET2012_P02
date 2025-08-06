package Commands;

import Tools.Receiver;

import java.util.Stack;

public class ListCommand implements Command {

    private Receiver receiver;
    private Stack<Command> history;

    public ListCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    @Override
    public void execute() {
        System.out.println("List");
        receiver.list();
    }

    @Override
    public void undo() {
//        System.out.println("Can't undo for ListCommand, undoing previous command");
        Command cmd = history.pop();
        cmd.undo();
    }
}
