package Driver;

import Commands.*;
import Tools.Invoker;
import Tools.Receiver;

import java.util.Stack;

/**
 * Main tester
 */
public class Main {

    /**
     * Main tester
     * @param args inputs
     */
    public static void main(String[] args) {
        Stack<Command> history = new Stack<>();
        Receiver receiver = new Receiver();

        String addinput1 = "First_name Last_name Email";
        String addinput2 = "John Doe simple@example.com";
        String addinput3 = "Hanna Moon tetter.tots@potatoesarelife.com";
        String addinput4 = "Ah Boon green-tea@teaforlife.com";

        AddCommand add1 = new AddCommand(receiver, addinput1);
        AddCommand add2 = new AddCommand(receiver, addinput2);
        AddCommand add3 = new AddCommand(receiver, addinput3);
        AddCommand add4 = new AddCommand(receiver, addinput4);

        String updateinput5 = "3 Adam";
        String updateinput6 = "1 Blue Bell ice-cream@alaskaFields.org";
        UpdateCommand update5 = new UpdateCommand(receiver, updateinput5);
        UpdateCommand update6 = new UpdateCommand(receiver, updateinput6);

        String deleteinput7 = "1";
        DeleteCommand delete7 = new DeleteCommand(receiver, deleteinput7);

        ListCommand list = new ListCommand(receiver);
        UndoCommand undo = new UndoCommand(receiver, history);


        Command[] command = {add1, add2, add3, add4, list, update5,
                list, update6, list, delete7, list, undo, list};

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(command);
        invoker.executeCommand(history);
        receiver.storeToFile();
    }
}
