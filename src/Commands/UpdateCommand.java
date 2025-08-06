package Commands;

import CustomException.AppException;
import Tools.Receiver;

import static Driver.Validators.emailValid;
import static Driver.Validators.toTitleCase;

public class UpdateCommand implements Command {
    private Receiver receiver;
    private String params;
    private String firstName;
    private String lastName;
    private String email;
    private int index;
    private int length;
    private String[] empBeforeUpdate;

    public UpdateCommand(Receiver receiver, String paramsInput) {
        this.receiver = receiver;
        this.params = paramsInput;
    }

    @Override
    public void execute() throws AppException {

        String[] data = params.split(" ");

        // check if index is given / is a valid integer
        String token = data[0];
        if (!token.matches("\\d+")) {
            throw new AppException("Unable to update, please provide a number to be updated.");
        }

        // if valid, proceed with parsing it
        index = Integer.parseInt(data[0])-1;

        // check if index exists
        if (index >= receiver.getEmployeeCount() || index < 0) {
            throw new AppException("Unable to update, invalid index.");
        }

        if (data.length < 2 || data.length > 4) {
            throw new AppException("Unable to update, wrong number of parameters.");
        }

        //need to handle validations for each variable. Capital for name, index cant be < 1
        // done - not sure if names need to be validated (brief says no)
        this.firstName = toTitleCase(data[1]);
        this.lastName  = data.length > 2 ? toTitleCase(data[2]) : null;
        this.email     = data.length > 3 ? data[3] : null;
        this.length = data.length;

        if (length == 4 && !emailValid(email)) {
            throw new AppException("Update failed: Invalid email format for " + email);
        }

//        receiver.update(index, firstName, lastName, email, length);
        empBeforeUpdate = receiver.update(index, firstName, lastName, email, length);
        System.out.println("update");
    }

    @Override
    public void undo() {
//        receiver.undoUpdate(index, empBeforeUpdate);
//        receiver.update(index, firstName, lastName, email, length);

        String[] prevEmployeeData = empBeforeUpdate;

        String prevFirstName = prevEmployeeData[0];
        String prevLastName = prevEmployeeData[1];
        String prevEmail = prevEmployeeData[2];;

        receiver.update(index, prevFirstName, prevLastName, prevEmail, 4);

        System.out.println("Undo");
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
