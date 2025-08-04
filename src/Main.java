import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Command> history = new Stack<>();
        Receiver receiver = new Receiver();
        receiver.loadFile();

//        Scanner input = new Scanner(System.in);
//        while (input.hasNextLine()) {
//            String line = input.nextLine();
//            String[] inputArray  = line.split(" ");
//            if(inputArray.length == 3){
//                Add employee = new Add(receiver, new Employee(inputArray[0], inputArray[1], inputArray[2]) );
//            }
//        }

        AddCommand addCommand = new AddCommand(receiver, new Employee("Sher", "Tan", "shersica1998@gmail.com"));
        AddCommand addCommand2 = new AddCommand(receiver, new Employee("Sheryl", "Tang", "shertang@gmail.com"));
        AddCommand addCommand3 = new AddCommand(receiver, new Employee("Sica", "Jung", "sica@gmail.com"));
//        Delete delete = new Delete(receiver,1);
        UpdateCommand updateCommand = new UpdateCommand(receiver, 1, new Employee("Test", "Ting", "test123@hotmail.com"));
        List list = new List(receiver);
        Command[] commands = {addCommand, addCommand2, addCommand3, updateCommand, list};
        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(commands);
        invoker.executeCommand(history);

        //Save data to file
        receiver.storeToFile();


    }
}