package Driver;

import Commands.*;
import Tools.Invoker;
import Tools.Receiver;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
//        Stack<Command> history = new Stack<>();
//        Receiver receiver = new Receiver();
//        receiver.loadFile();
//
//        String params1 = "Test Ting test123@gmail.com";
//        String params2 = "Sher Tan shertan@gmail.com";
//        String params3 = "2 Abc Loo 123abc@gmail.com";
//        String params4 = "Hello World helloworld@gmail.com";
//        String params5 = "1 Updating one";
//
//        AddCommand addCommand = new AddCommand(receiver, params1);
//        AddCommand addCommand2 = new AddCommand(receiver, params2);
//        UpdateCommand updateCommand = new UpdateCommand(receiver,params3);
//        DeleteCommand delete = new DeleteCommand(receiver,"1");
//        AddCommand addCommand3 = new AddCommand(receiver, params4);
//        UpdateCommand updateCommand2 = new UpdateCommand(receiver,params5);
//        UndoCommand undoCommand = new UndoCommand(receiver, history);
//        ListCommand listCommand = new ListCommand(receiver, history);
////        Command[] commands = {addCommand, listCommand};
//        Command[] commands = {addCommand,addCommand2, addCommand3, listCommand, updateCommand, listCommand, updateCommand2, listCommand,undoCommand, listCommand, delete, listCommand, undoCommand, listCommand};
//        Invoker invoker = new Invoker();
//        invoker.setCommandsForExecution(commands);
//        invoker.executeCommand(history);

        //Save data to file
//        receiver.storeToFile();

        Stack<Command> history = new Stack<>();
        Receiver receiver = new Receiver();
//        receiver.loadFile();
        String addinput1 = "First_name Last_name Email";
        String addinput2 = "John Doe simple@example.com";
//        String addinput3 = "Hanna Moon tetter.tots@potatoesarelife.com";
//        String addinput4 = "Ah Boon green-tea@teaforlife.com";
//
        AddCommand add1 = new AddCommand(receiver, addinput1);
        AddCommand add2 = new AddCommand(receiver, addinput2);
//        AddCommand add3 = new AddCommand(receiver, addinput3);
//        AddCommand add4 = new AddCommand(receiver, addinput4);
//
//        String updateinput5 = "3 Adam";
//        String updateinput6 = "1 Blue Bell ice-cream@alaskaFields.org";
//        UpdateCommand update5 = new UpdateCommand(receiver, updateinput5);
//        UpdateCommand update6 = new UpdateCommand(receiver, updateinput6);
//
//        String deleteinput7 = "1";
//        DeleteCommand delete7 = new DeleteCommand(receiver, deleteinput7);
//
        ListCommand list = new ListCommand(receiver);
//        UndoCommand undo = new UndoCommand(receiver, history);
//
//
//        Command[] command = {add1, add2, add3, add4, list, update5,
//                list, update6, list, delete7, list, undo, list};

        Command[] command = {add1,add2, list};


        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(command);
        invoker.executeCommand(history);
        receiver.storeToFile();
    }
}
