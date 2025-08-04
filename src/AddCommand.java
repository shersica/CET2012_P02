public class AddCommand implements Command {

    private Employee employee;
    private Receiver receiver;

//    private String firstName;
//    private String lastName;
//    private String email;

    public AddCommand(Receiver receiver, Employee employee) {
        this.receiver = receiver;
        this.employee = employee;
    }

    @Override
    public void execute() {
        receiver.add(employee);
    }

//    public Add(Receiver receiver, String firstName, String lastName, String email) {
//        this.receiver = receiver;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }
}
