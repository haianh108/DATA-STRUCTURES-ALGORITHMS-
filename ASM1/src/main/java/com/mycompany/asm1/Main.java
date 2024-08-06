package com.mycompany.asm1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentInformationSystem sis = new StudentInformationSystem();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Sort Students by ID");
            System.out.println("4. Search Student by ID");
            System.out.println("5. Edit Student Info");
            System.out.println("6. Delete Student Info");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                scanner.next(); // Clear the invalid input
                continue;
            }
            scanner.nextLine(); // Clear the newline

            switch (choice) {
                case 1:
                    addStudent(sis, scanner);
                    break;
                case 2:
                    viewStudents(sis);
                    break;
                case 3:
                    sortStudents(sis);
                    break;
                case 4:
                    searchStudent(sis, scanner);
                    break;
                case 5:
                    editStudent(sis, scanner);
                    break;
                case 6:
                    deleteStudent(sis, scanner);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select another option.");
            }
        }
        scanner.close();
    }
    private static void addStudent(StudentInformationSystem sis, Scanner scanner) {
        try {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Student Marks: ");
            double marks = scanner.nextDouble();
            scanner.nextLine(); // Clear the newline
            sis.addStudent(new Student(id, name, marks));
            System.out.println("Student added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for marks. Please enter a numeric value.");
            scanner.next(); // Clear the invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewStudents(StudentInformationSystem sis) {
        System.out.println("Student Details and Rankings:");
        for (Student student : sis.getStudents()) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Marks: " + student.getMarks() + ", Ranking: " + student.getRanking());
        }
    }

    private static void sortStudents(StudentInformationSystem sis) {
        sis.sortStudentsById();
        System.out.println("Students sorted by ID.");
        viewStudents(sis);
    }

    private static void searchStudent(StudentInformationSystem sis, Scanner scanner) {
        System.out.print("Enter Student ID to search: ");
        String searchId = scanner.nextLine();
        Student foundStudent = sis.searchStudentById(searchId);
        if (foundStudent != null) {
            System.out.println("Found Student - ID: " + foundStudent.getId() + ", Name: " + foundStudent.getName() + ", Marks: " + foundStudent.getMarks() + ", Ranking: " + foundStudent.getRanking());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void editStudent(StudentInformationSystem sis, Scanner scanner) {
        try {
            System.out.print("Enter Student ID to edit: ");
            String id = scanner.nextLine();
            System.out.print("Enter new Student Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new Student Marks: ");
            double marks = scanner.nextDouble();
            scanner.nextLine(); // Clear the newline
            sis.editStudent(id, name, marks);
            System.out.println("Student updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for marks. Please enter a numeric value.");
            scanner.next(); // Clear the invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteStudent(StudentInformationSystem sis, Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        if (sis.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
}