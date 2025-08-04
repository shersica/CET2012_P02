package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Receiver {
    private ArrayList<String> employees = new ArrayList<>();

    public void add(String params) {
        employees.add(params);
        System.out.println("Add");
    }

    public void update(int index, String params) {
        employees.set(index, params);
        System.out.println("Update");
    }

    public void delete(int index) {
        employees.remove(index);
        System.out.println("Delete");
    }

    public void list(){
        int index = 1;
        for (String emp : employees) {
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
                String employee = employees.get(i);
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

        if (Files.notExists(filepath)) {
            System.out.println("No file to load");
            return;
        }

        try (BufferedReader buff_reader = Files.newBufferedReader(filepath)) {
            String line;
            while ( (line = buff_reader.readLine()) != null) {
                String delimiter = "\\. ";

                // Split the string by the delimiter
                String[] parts = line.split(delimiter);

                // Check if there are two elements
                if (parts.length == 2) {
                    String secondElement = parts[1];
                    employees.add(secondElement);
                } else {
                    System.out.println("Error with data, employee data does not fit specified format.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        } ;
    }
}
