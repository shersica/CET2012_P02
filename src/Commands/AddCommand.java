package Commands;

import CustomException.AppException;
import Tools.Receiver;

import static Driver.Validators.*;

/**
 * AddCommand: a concrete implementation of the {@link Command} interface.
 * Adds a new employee to the end of the {@code employees} list.
 */
public class AddCommand implements Command {
    /**
     * The {@link Receiver} instance responsible for executing the command.
     */
    private Receiver receiver;
    /**
     * Raw parameters in {@code String} format.
     */
    private String params;
    /**
     * Employee first name (Title Case required).
     */
    private String firstName;
    /**
     * Employee last name (Title Case required).
     */
    private String lastName;
    /**
     * Employee email case (validations required if it is an email in
     * the form of email@address.com, else, Title case required).
     */
    private String email;

    /**
     * Constructor for the AddCommand class.
     * @param receiver_ the receiver instance responsible for executing the command
     * @param params_ raw parameters in {@code String} format
     */
    public AddCommand(Receiver receiver_, String params_)  {
        this.receiver = receiver_;
        this.params = params_;
    }

    /**
     * Executes the {@code add} command.
     * <br>
     * The following validations are performed on the raw parameters:
     * <ul>
     *      <li> Check if 3 parameters are given </li>
     *      <li> Check if data3 follows the required format</li>
     * </ul>
     * <br>
     * Once checked, sends the formatted and parsed employee data to the
     * receiver for insertion into the employees list.
     * @throws AppException if any of the above validations return false
     */
    @Override
    public void execute() throws AppException {
        String[] data = params.split(" ");

        if (data.length != 3) {
            throw new AppException("Error adding employee: expected 3 fields but got " + data.length);
        }

        String employeeEmail = data[2];
        if (!data3Valid(employeeEmail)) {
            throw new AppException("Invalid email format");
        }

        firstName = toTitleCase(data[0]);
        lastName = toTitleCase(data[1]);
        email = formatData3(data[2]);

        receiver.add(new String[]{firstName, lastName, email});
        System.out.println("add");
    }

    /**
     * Undoes the add command by deleting the last item in the list
     */
    @Override
    public void undo() {
        int lastIndex = receiver.getEmployeeCount() - 1;
        receiver.delete(lastIndex);
//        System.out.println("Undo");
    }

    /**
     * {@inheritDoc}
     * @return {@code true} always
     */
    @Override
    public boolean isUndoable() {
        return true;
    }
}