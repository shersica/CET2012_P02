package Commands;

import CustomException.AppException;
import Tools.Receiver;

import static Driver.Validators.*;

/**
 * UpdateCommand: a concrete implementation of the {@link Command} interface.
 * Updates the employee based on number of parameters given.
 */
public class UpdateCommand implements Command {
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
     * Parsed index of the employee data to be updated, stored as an {@code int}.
     */
    private int index;
    /**
     * Employee data before updated, stored in {@code String[]} format.
     * Required for {@code undo} command.
     */
    private String[] empBeforeUpdate;

    /**
     * The number of fields provided.
     * TODO: CHECK - i deleted this
     */
//        int length = data.length;

    /**
     * Constructor for the UpdateCommand class.
     * @param receiver_ the receiver instance responsible for executing the command
     * @param params_ raw parameters in {@code String} format
     */
    public UpdateCommand(Receiver receiver_, String params_) {
        this.receiver = receiver_;
        this.params = params_;
    }

    /**
     * Executes the {@code update} command.
     * <br>
     * The following validations are performed:
     * <ul>
     *      <li> Checks if raw index provided is a non-negative digit.
     *      If valid, parses the index as an integer, then </li>
     *      <li> Checks if the index exceeds number of entries and/or is zero. </li>
     *      <li> Checks if inputs have the correct number of parameters (i.e. between 2 and 4) </li>
     *      <li> If inputs have 4 parameters, check that data3 meets the requirements </li>
     * </ul>
     * <br>
     * Once checked, sends the parsed index to the
     * receiver for updating of the specified index.
     * @throws AppException if any of the above validations return false
     */
    @Override
    public void execute() throws AppException {

        String[] data = params.split(" ");

        // check if index is given is a non-negative integer
        String indexStr = data[0];
        if (!indexStr.matches("\\d+")) {
            throw new AppException("Unable to update, please provide a number to be updated.");
        }

        // if valid, proceed with parsing it
        index = Integer.parseInt(data[0])-1;

        // check if index exists
        if (index >= receiver.getEmployeeCount() || index < 0) {
            throw new AppException("Unable to update, invalid index.");
        }

        // check if number of inputs are correct
        if (data.length < 2 || data.length > 4) {
            throw new AppException("Unable to update, wrong number of parameters.");
        }

        // handle validations for each variable - Title Case for name, handle validations for data3
        this.firstName = toTitleCase(data[1]);
        this.lastName  = data.length > 2 ? toTitleCase(data[2]) : null;
        this.email     = data.length > 3 ? formatData3(data[3]) : null;

        // check if data3 is valid if 4 fields are given
        if (data.length == 4 && !data3Valid(email)) {
            throw new AppException("Update failed: Invalid email format for " + email);
        }

        empBeforeUpdate = receiver.update(index, firstName, lastName, email, data.length);
        System.out.println("update");
    }

    /**
     * Undoes the update command by putting the previously edited employee back into the specified index.
     */
    @Override
    public void undo() {
        String[] prevEmployeeData = empBeforeUpdate;

        String prevFirstName = prevEmployeeData[0];
        String prevLastName = prevEmployeeData[1];
        String prevEmail = prevEmployeeData[2];;

        receiver.update(index, prevFirstName, prevLastName, prevEmail, 4);
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
