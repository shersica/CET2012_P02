package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Receiver class: contains business logic, and does the actual work.
 * It performs add, update, delete, and list operations on an in-memory {@code ArrayList},
 * and also reads from and writes to a {@code dataStore.txt} file.
 */
public class Receiver {
    /**
     * Stores the employee's data.
     * Each data is stored as a {@code String[]} in the form of {@code First_name Last_name Email}
     */
    private ArrayList<String[]> employees = new ArrayList<>();

    /**
     * Constructor for the Receiver class. It loads employee data from a saved file at startup (if any).
     */
    public Receiver() {
        loadFile();
    }

    /**
     * Adds a new employee to the end of the {@code employees} list.
     * @param employee a {@code String[]} representing {First_name, Last_name, Email}
     */
    public void add(String[] employee) {
        employees.add(employee);
    }

    /**
     * Adds an employee back to a specific index (used for undoing delete)
     * @param indx     the 1-based index to insert the employee back at
     * @param employee a {@code String[]} representing {First_name, Last_name, Email}
     */
    public void add(int indx, String[] employee) {
        employees.add(indx-1, employee);
    }

    /**
     * Updates the employee's information based on number of parameters given.
     * @param index     the zero-based index of the employee to update
     * @param firstName employee's first name
     * @param lastName  employee's last name (optional)
     * @param email     employee's email (optional)
     * @param length    the number of fields provided
     * @return a copy of the employee's data before update
     */
    public String[] update(int index, String firstName, String lastName, String email, int length) {
        String[] previous = employees.get(index).clone();
        String[] current = employees.get(index);

        if (length == 2) {
            current[0] = firstName;
        } else if (length == 3) {
            current[0] = firstName;
            current[1] = lastName;
        } else if (length == 4) {
            current[0] = firstName;
            current[1] = lastName;
            current[2] = email;
        }

        employees.set(index, current);
//        System.out.println("Update");
        return previous;
    }

    /**
     * Removes the employee from the list based on the index provided
     * @param index the zero-based index of the employee to delete
     * @return a copy of the employee's data that was deleted
     */
    public String[] delete(int index) {
        String[] employeeToDel = employees.get(index).clone();
        employees.remove(index);
//        System.out.println("Delete");
        return employeeToDel;
    }

    /**
     * Prints out the current items stored in the {@code employees} ArrayList.
     */
    public void list() {
        int indx = 1;
        for (String[] emp : employees) {
            System.out.printf("%02d. %s %s %s\n", indx++, emp[0], emp[1], emp[2]);
        }
    }

    /**
     * Saves the current employee data to a text file at {@code ./src/dataStore.txt}.
     */
    public void storeToFile() {
        Path filepath = Paths.get("./src/dataStore.txt");

        try (BufferedWriter buff_writer = Files.newBufferedWriter(filepath)) {
            for (int i = 0; i < employees.size(); i++) {
                String[] employee = employees.get(i);
                String line = String.format("%02d. %s %s %s\n", i+1, employee[0], employee[1], employee[2]);
                buff_writer.write(line);
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
        System.out.println("Stored to file");
    }

    /**
     * Loads the employee data from a text file, {@code ./src/dataStore.txt}, if any.
     */
    public void loadFile() {
        Path filepath = Paths.get("./src/dataStore.txt");

        if (Files.notExists(filepath)) {
            return;
        }

        try (BufferedReader buff_reader = Files.newBufferedReader(filepath)) {
            String line;
            while ( (line = buff_reader.readLine()) != null) {
                String[] employee = line.split(" ");
                employees.add(new String[]{employee[1], employee[2], employee[3]});
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Returns the number of employee records currently stored,
     * which aids in validation for {@code UpdateCommand} and {@code DeleteCommand}.
     *
     * @return the size of the employee list
     */
    public int getEmployeeCount() {
        return employees.size();
    }
}