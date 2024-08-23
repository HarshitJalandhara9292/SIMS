
package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class StudentManagementApp {
    private JFrame frame;
    private JTable table;
    private StudentL studentL;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField gradeField;
    private JTextField searchField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentManagementApp window = new StudentManagementApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentManagementApp() {
        initialize();
    }

    private void initialize() {
        studentL = new StudentL();
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 10, 80, 25);
        frame.getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setBounds(100, 10, 150, 25);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(10, 40, 80, 25);
        frame.getContentPane().add(lblAge);

        ageField = new JTextField();
        ageField.setBounds(100, 40, 150, 25);
        frame.getContentPane().add(ageField);
        ageField.setColumns(10);

        JLabel lblGrade = new JLabel("Grade:");
        lblGrade.setBounds(10, 70, 80, 25);
        frame.getContentPane().add(lblGrade);

        gradeField = new JTextField();
        gradeField.setBounds(100, 70, 150, 25);
        frame.getContentPane().add(gradeField);
        gradeField.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(10, 100, 80, 25);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        frame.getContentPane().add(btnAdd);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(100, 100, 80, 25);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        frame.getContentPane().add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(190, 100, 80, 25);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        frame.getContentPane().add(btnDelete);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(10, 130, 80, 25);
        frame.getContentPane().add(lblSearch);

        searchField = new JTextField();
        searchField.setBounds(100, 130, 150, 25);
        frame.getContentPane().add(searchField);
        searchField.setColumns(10);


        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(260, 130, 80, 25);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudents();
            }
        });
        frame.getContentPane().add(btnSearch);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 170, 565, 180);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        loadStudentData();
    }

    private void addStudent() {
        int id = Integer.parseInt(ageField.getText());
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String grade = gradeField.getText();
        Student newStudent = new Student(id, name, age, grade);
        studentL.insertStudent(newStudent);
        loadStudentData();
        clearFields();
    }

    private void updateStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) table.getValueAt(selectedRow, 0);
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String grade = gradeField.getText();
            Student updatedStudent = new Student(id, name, age, grade);
            studentL.updateStudent(updatedStudent);
            loadStudentData();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Select a student to update");
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) table.getValueAt(selectedRow, 0);
            studentL.deleteStudent(id);
            loadStudentData();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Select a student to delete");
        }
    }

    private void searchStudents() {
        String query = searchField.getText();
        List<Student> students = studentL.searchStudentsByNameOrGrade(query);
        populateTable(students);
    }

    private void loadStudentData() {
        List<Student> students = studentL.selectAllStudents();
        populateTable(students);
    }

    private void populateTable(List<Student> students) {
        String[] columnNames = {"ID", "Name", "Age", "Grade"};
        Object[][] data = new Object[students.size()][4];
        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getId();
            data[i][1] = students.get(i).getName();
            data[i][2] = students.get(i).getAge();
            data[i][3] = students.get(i).getGrade();
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        gradeField.setText("");
        searchField.setText("");
    }
}
