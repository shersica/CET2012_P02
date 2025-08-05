package Commands;

import Tools.Receiver;

public class AddCommand implements Command {

    private Receiver receiver;
    private String firstName;
    private String lastName;
    private String email;


    @Override
    public void execute() {
        receiver.add(new String[]{firstName, lastName, email});
    }

    @Override
    public void undo() {
        receiver.undoAdd();
    }

    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        String[] data = params.split(" ");
        if (data.length != 3) {
            System.out.println("Wrong number of parameters");
            throw new IllegalArgumentException("Wrong number of parameters");
        }
        //still need to handle each param validations
        else {
            this.firstName = data[0];
            this.lastName = data[1];
            this.email = data[2];
        }
    }
}
