package Commands;

import Tools.Receiver;

public class UpdateCommand implements Command {
    private Receiver receiver;
    private String firstName;
    private String lastName;
    private String email;
    private int index;
    private int length;
    private String[] empBeforeUpdate;

    public UpdateCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        String[] data = params.split(" ");
        if (data.length < 2 || data.length > 4) {
            System.out.println("Wrong number of parameters");
        }

        try {
            this.index = Integer.parseInt(data[0])-1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid index format");
        }

        //need to handle validations for each variable. Capital for name, index cant be < 1
        this.firstName = data.length > 1 ? data[1] : null;
        this.lastName  = data.length > 2 ? data[2] : null;
        this.email     = data.length > 3 ? data[3] : null;
        this.length = data.length;
    }

    @Override
    public void execute() {
        empBeforeUpdate = receiver.update(index, firstName, lastName, email, length);
    }

    @Override
    public void undo() {
        receiver.undoUpdate(index, empBeforeUpdate);
    }
}
