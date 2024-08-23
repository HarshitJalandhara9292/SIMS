package com.company;
import java.sql.SQLException;
import java.util.Scanner;

import com.company.StudentC;

public class StudentCC {
    public static void main(String[] args) {
        StudentC studentC = new StudentC();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter student id: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    System.out.println("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.println("Enter student age: ");
                    int age = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    System.out.println("Enter student grade: ");
                    String grade = sc.nextLine();

                    studentC.addStudent(id, name, age, grade);
                    System.out.println("Student added.");
                    break;

                case 2:
                    System.out.println("Enter student id to delete: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    studentC.deleteStudent(id);
                    System.out.println("Student deleted.");
                    break;


                case 3:
                    System.out.println("Enter student id to update: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter new student name: ");
                    name = sc.nextLine();

                    System.out.println("Enter new student age: ");
                    age = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter new student grade: ");
                    grade = sc.nextLine();

                    studentC.updateStudent(id, name, age, grade);
                    System.out.println("Student updated.");
                    break;

                case 4:
                    studentC.getAllStudents().forEach(System.out::println);
                    break;

//                case 5:
//                    System.out.println("Exiting...");
//                    sc.close();
//                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

