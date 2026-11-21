import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
class Employee {
    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    String toFileString() {
        return id + "," + name + "," + department + "," + salary;
    }

    void display() {
        System.out.println(id + " | " + name + " | " + department + " | " + salary);
    }
}

public class EmployeeManagement {
    static ArrayList<Employee> employees = new ArrayList<>();
    static final String FILE_NAME = "employees.txt";

    static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee e : employees) {
                writer.println(e.toFileString());
            }
            System.out.println("Saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // nothing saved yet
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");
                employees.add(new Employee(Integer.parseInt(p[0]), p[1], p[2], Double.parseDouble(p[3])));
            }
            System.out.println("Loaded " + employees.size() + " employee(s).");
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    static void addEmployee(Scanner sc) {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Department: ");
            String dept = sc.nextLine();
            System.out.print("Salary: ");
            double salary = Double.parseDouble(sc.nextLine());

            employees.add(new Employee(id, name, dept, salary));
            System.out.println("Employee added.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! ID and Salary must be numbers.");
        }
    }

    static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee e : employees) {
            e.display();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadFromFile();

        while (true) {
            System.out.println("\n1. Add  2. View  3. Save  4. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": addEmployee(sc); break;
                case "2": viewEmployees(); break;
                case "3": saveToFile(); break;
                case "4": System.out.println("Bye!"); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}