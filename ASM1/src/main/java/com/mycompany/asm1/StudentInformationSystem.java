package com.mycompany.asm1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StudentInformationSystem {
    private List<Student> students;

    public StudentInformationSystem() {
        this.students = new ArrayList<>();
    }

    // Method to add a student with error handling
    public void addStudent(Student student) throws IllegalArgumentException {
        if (searchStudentById(student.getId()) != null) {
            throw new IllegalArgumentException("Student with ID " + student.getId() + " already exists.");
        }
        students.add(student);
    }

    // Method to get the list of students
    public List<Student> getStudents() {
        return students;
    }

    // Method to sort students by ID using Collections.sort()
    public void sortStudentsById() {
        Collections.sort(students, Comparator.comparing(Student::getId));
    }

    // Method to search for a student by ID
    public Student searchStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Method to edit a student's details with error handling
    public void editStudent(String id, String name, double marks) throws IllegalArgumentException {
        Student student = searchStudentById(id);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
        student.setName(name);
        student.setMarks(marks);
    }

    // Method to delete a student by ID with error handling
    public boolean deleteStudent(String id) {
        Student student = searchStudentById(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }
}