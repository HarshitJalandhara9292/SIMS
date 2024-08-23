package com.company;

import java.io.*;
import java.util.*;
import java.util.List;

public class StudentManagementSystem {
    private final List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> viewAllStudents() {
        return students;
    }

    public Student searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Student searchStudentByAge(int age) {
        for (Student student : students) {
            if (student.getAge() == age) {
                return student;
            }
        }
        return null;
    }

    public Student searchStudentByName(String name) {
        for (Student student : students) {
            if (Objects.equals(student.getName(), name)) {
                return student;
            }
        }
        return null;
    }

    public Student searchStudentByGrade(String grade) {
        for (Student student : students) {
            if (Objects.equals(student.getGrade(), grade)) {
                return student;
            }
        }
        return null;
    }

    public boolean updateStudentInformation(int id, String name, int age, String grade) {
        Student student = searchStudentById(id);
        if (student != null) {
            if (name != null && !name.isEmpty()) student.setName(name);
            if (age > 0) student.setAge(age);
            if (grade != null && !grade.isEmpty()) student.setGrade(grade);
            return true;
        }
        return false;
    }

    public boolean deleteStudentById(int id) {
        Student student = searchStudentById(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("result.txt"))) {
            for (Student student : students) {
                bw.write(student.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred when saving to file: " + e.getMessage());
        }
    }

    public void sortStudents(Comparator<Student> comparator) {
        Collections.sort(students, comparator);
    }

    public void exportToCSV(String s) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Student.csv"))) {
            for (Student student : students) {
                bw.write(student.toString());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error encountered when Export to csv" + e.getMessage());
        }
    }
    public void ImportFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("Student.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String grade = data[3];
                      addStudent(new Student(id, name, age, grade));
                 }
            }
        } catch (IOException e) {
            System.out.println("An error occurred when importing from CSV: " + e.getMessage());
        }
    }
    public void sortStudentsById() {
        sortStudents(Comparator.comparingInt(Student::getId));
    }

    public void sortStudentsByName() {
        sortStudents(Comparator.comparing(Student::getName));
    }

    public void sortStudentsByAge() {
        sortStudents(Comparator.comparingInt(Student::getAge));
    }

    public void sortStudentsByGrade() {
        sortStudents(Comparator.comparing(Student::getGrade));
    }
}


