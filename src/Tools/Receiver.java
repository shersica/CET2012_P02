package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Receiver {
    private ArrayList<String[]> employees = new ArrayList<>();

    public void add(String[] employee) {
        employees.add(employee);
//        System.out.println("Added");
    }

    // Method Overloading for undo function
    public void add(int indx, String[] employee) {
        employees.add(indx-1, employee);
//        System.out.println("Undo (delete)");
    }

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

    public String[] delete(int index) {
        String[] employeeToDel = employees.get(index).clone();
//        System.out.println("employeeToDel is: " + Arrays.toString(employeeToDel));
        employees.remove(index);
//        System.out.println("Delete");
        return employeeToDel;
    }

    public void list(){
        int index = 1;
        for (String[] emp : employees) {
            System.out.printf("%02d. %s %s %s\n", index++, emp[0], emp[1], emp[2]);
        }
    }

//    public void undoAdd(){
//        int lastIndex = employees.size() - 1;
//        employees.remove(lastIndex);
//        System.out.println("Undo Add");
//    }

//    public void undoUpdate(int index, String[] employee){
//        employees.set(index, employee);
//        System.out.println("Undo Update");
//    }

//    public void undoDelete(int index, String[] employee){
//        employees.add(index, employee);
//        System.out.println("Undo Delete");
//    }


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

    public void loadFile() {
        Path filepath = Paths.get("./src/dataStore.txt");

        if (Files.notExists(filepath)) {
            System.out.println("No file to load");
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

    public int getEmployeeCount() {
        return employees.size();
    }

}
