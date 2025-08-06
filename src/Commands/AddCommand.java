package Commands;

import CustomException.AppException;
import Tools.Receiver;

import static Driver.Validators.data3Valid;
import static Driver.Validators.toTitleCase;

public class AddCommand implements Command {

    private Receiver receiver;
    private String params;
    private String firstName;
    private String lastName;
    private String email;


//    public void execute() {
//        receiver.add(new String[]{firstName, lastName, email});
//    }
    @Override
    public void execute() throws AppException {
        String[] data = params.split(" ");

        // validation needs to be done before execution
        if (data.length != 3) {
            throw new AppException("Error adding employee: expected 3 fields but got " + data.length);
        }

        String employeeEmail = data[2];
        if (!data3Valid(employeeEmail)) {
            throw new AppException("Invalid email format");
        }

        firstName = toTitleCase(data[0]);
        lastName = toTitleCase(data[1]);
        email = data[2];

        receiver.add(new String[]{firstName, lastName, email});
        System.out.println("add");
    }

    @Override
    public void undo() {
        int lastIndex = receiver.getEmployeeCount() - 1;
        receiver.delete(lastIndex);
//        System.out.println("Ignore prev line, not Delete but Undo");
//        System.out.println("Undo");

//        receiver.undoAdd();
    }

    public AddCommand(Receiver receiverInput, String paramsInput)  {
        this.receiver = receiverInput;
        this.params = paramsInput;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    //    public AddCommand(Receiver receiver, String params) {
//        this.receiver = receiver;
//        String[] data = params.split(" ");
//        if (data.length != 3) {
//            System.out.println("Wrong number of parameters");
//            throw new IllegalArgumentException("Wrong number of parameters");
//        }
//        //still need to handle each param validations
//        else {
//            this.firstName = data[0];
//            this.lastName = data[1];
//            this.email = data[2];
//        }
//    }

}
