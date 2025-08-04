package Commands;

import Tools.Receiver;

public class AddCommand implements Command {

    private Receiver receiver;
    private String params;


    @Override
    public void execute() {
        receiver.add(params);

    }

    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;

        //handle validations
//        String[] data = params.split(" ");
//        String firstName = data[0];
//        String lastName = data[1];
//        String email = data[2];

    }
}
