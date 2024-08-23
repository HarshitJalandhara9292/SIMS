package com.company;

import java.util.List;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;
    private String grade;

    public Student(int id, String name, int age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + grade;
    }
}


public class Assignment1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        System.out.println("How many Students do you want to enter: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter student id: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter student name: ");
            String name = sc.nextLine();

            System.out.println("Enter student age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter student grade: ");
            String grade = sc.nextLine();
            sms.addStudent(new Student(id, name, age, grade));
        }

        while (true) {
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Search for a student by ID");
            System.out.println("4. Search for a student by name");
            System.out.println("5. Search for a student by age");
            System.out.println("6. Search for a student by grade");
            System.out.println("7. Sort student by id");
            System.out.println("8. Sort student by name");
            System.out.println("9. Sort student by age");
            System.out.println("10. Sort student by grade");
            System.out.println("11. Update student information");
            System.out.println("12. Delete a student by ID");
            System.out.println("13. Export from CSV");
            System.out.println("14. Import from CSV");
            System.out.println("15. Save data to file");
            System.out.println("16. Exit");
            System.out.println("Enter your choice:");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter student id: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.println("Enter student age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter student grade: ");
                    String grade = sc.nextLine();

                    sms.addStudent(new Student(id, name, age, grade));
                    break;

                case 2:
                    List<Student> students = sms.viewAllStudents();
                    for (Student student : students) {
                        System.out.println(student);
                    }
                    break;

                case 3:
                    System.out.println("Enter student id to search: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    Student student = sms.searchStudentById(id);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.println("Enter student name to search: ");
                    name = sc.nextLine();
                    Student studentByName = sms.searchStudentByName(name);
                    if (studentByName != null) {
                        System.out.println(studentByName);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.println("Enter student age to search: ");
                    age = sc.nextInt();
                    sc.nextLine();
                    Student studentByAge = sms.searchStudentByAge(age);
                    if (studentByAge != null) {
                        System.out.println(studentByAge);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    System.out.println("Enter student grade to search: ");
                    grade = sc.nextLine();
                    Student studentByGrade = sms.searchStudentByGrade(grade);
                    if (studentByGrade != null) {
                        System.out.println(studentByGrade);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 7:
                    sms.sortStudentsById();
                    System.out.println("Students sorted by ID.");
                    break;

                case 8:
                    sms.sortStudentsByName();
                    System.out.println("Students sorted by name.");
                    break;

                case 9:
                    sms.sortStudentsByAge();
                    System.out.println("Students sorted by age.");
                    break;

                case 10:
                    sms.sortStudentsByGrade();
                    System.out.println("Students sorted by grade.");
                    break;

                case 11:
                    System.out.println("Enter student id to update: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter new name (or press Enter to skip): ");
                    name = sc.nextLine();

                    System.out.println("Enter new age (or 0 to skip): ");
                    age = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter new grade (or press Enter to skip): ");
                    grade = sc.nextLine();

                    if (sms.updateStudentInformation(id, name, age, grade)) {
                        System.out.println("Student updated.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 12:
                    System.out.println("Enter student id to delete: ");
                    id = sc.nextInt();

                    sc.nextLine();
                    if (sms.deleteStudentById(id)) {
                        System.out.println("Student deleted.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;


                case 13:
                    String exportFilename = sc.nextLine();
                    sms.exportToCSV(exportFilename);
                    System.out.println("Data exported" + exportFilename);

                case 14:
                    String ImportFilename = sc.nextLine();
                    sms.ImportFromCSV();
                    System.out.println("Data Imported" + ImportFilename);

                case 15:
                    sms.saveToFile();
                    System.out.println("Data saved to file.");
                    break;

                case 16:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}