import Commands.*;
import Tools.Invoker;
import Tools.Receiver;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Command> history = new Stack<>();
        Receiver receiver = new Receiver();
        receiver.loadFile();

        String params1 = "Test Ting test123@gmail.com";
        String params2 = "Sher Tan shertan@gmail.com";
        String params3 = "Abc Loo 123abc@gmail.com";
        String params4 = "Hello World helloworld@gmail.com";
        String params5 = "Updating";

        AddCommand addCommand = new AddCommand(receiver, params1);
        AddCommand addCommand2 = new AddCommand(receiver, params2);
        UpdateCommand updateCommand = new UpdateCommand(receiver,1, params3);
        DeleteCommand delete = new DeleteCommand(receiver,1);
        AddCommand addCommand3 = new AddCommand(receiver, params4);
        UpdateCommand updateCommand2 = new UpdateCommand(receiver,1, params5);
        ListCommand listCommand = new ListCommand(receiver);
        Command[] commands = {addCommand, addCommand2, updateCommand, delete, addCommand3, updateCommand2, listCommand};

        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(commands);
        invoker.executeCommand(history);

        //Save data to file
        receiver.storeToFile();


    }
}