import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class Receiver {
    private Stack<Command> history;
    private ArrayList<Employee> employees = new ArrayList<>();
//    private ArrayList<String> employeeString;

//    public Receiver(Stack<Command> history) {
//        this.history = history;
//    }

    public void add(Employee employee) {
        employees.add(employee);
        System.out.println("Add");
    }

    public void update(int index, Employee employee) {
        employees.set(index, employee);
        System.out.println("Update");
    }

    public void delete(int index) {
        employees.remove(index);
        System.out.println("Delete");
    }

    public void list(){
//        for(int i = 0; i < employees.size(); i++){
//            System.out.printf("0%d. %s %s %s\n", i+1,employees.get(i).getFirstName(),employees.get(i).getLastName(),employees.get(i).getEmail());
//        }

        int index = 1;
        for (Employee emp : employees) {
            System.out.printf("%02d. %s\n", index++, emp);
        }
    }

    public void undo() {

        System.out.println("Undo");
    }

    public void storeToFile() {
        Path filepath = Paths.get("dataStore.txt");
        try (BufferedWriter buff_writer = Files.newBufferedWriter(filepath)) {

            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
//                String line = String.format("%02d. %s %s %s\n", i+1, employee.getFirstName(), employee.getLastName(), employee.getEmail());
                String line = String.format("%02d. %s\n", i+1, employee);
                buff_writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
        System.out.println("Stored to file");

    }

    public void loadFile() {
        Path filepath = Paths.get("dataStore.txt");

//        if (Files.notExists(filepath)) {
//            System.out.println("No file to load");
//            return;
//        }
//
//        try (BufferedReader buff_reader = Files.newBufferedReader(filepath)) {
//
//        } catch (IOException e) {
//            System.err.println("Error loading file: " + e.getMessage());
//        } ;
    }
}
