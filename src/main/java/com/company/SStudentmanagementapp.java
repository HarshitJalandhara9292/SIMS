package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SStudentmanagementapp {
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
                SStudentmanagementapp window = new SStudentmanagementapp();
                window.getFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SStudentmanagementapp() {
        initialize();
    }

    private void initialize() {
        setStudentL(new StudentL());
        setFrame(new JFrame());
        getFrame().setBounds(100, 100, 600, 400);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 10, 80, 25);
        getFrame().getContentPane().add(lblName);

        setNameField(new JTextField());
        getNameField().setBounds(100, 10, 150, 25);
        getFrame().getContentPane().add(getNameField());
        getNameField().setColumns(10);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(10, 40, 80, 25);
        getFrame().getContentPane().add(lblAge);

        setAgeField(new JTextField());
        getAgeField().setBounds(100, 40, 150, 25);
        getFrame().getContentPane().add(getAgeField());
        getAgeField().setColumns(10);

        JLabel lblGrade = new JLabel("Grade:");
        lblGrade.setBounds(10, 70, 80, 25);
        getFrame().getContentPane().add(lblGrade);

        setGradeField(new JTextField());
        getGradeField().setBounds(100, 70, 150, 25);
        getFrame().getContentPane().add(getGradeField());
        getGradeField().setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(10, 100, 80, 25);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        getFrame().getContentPane().add(btnAdd);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(100, 100, 80, 25);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        getFrame().getContentPane().add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(190, 100, 80, 25);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        getFrame().getContentPane().add(btnDelete);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(10, 130, 80, 25);
        getFrame().getContentPane().add(lblSearch);

        setSearchField(new JTextField());
        getSearchField().setBounds(100, 130, 150, 25);
        getFrame().getContentPane().add(getSearchField());
        getSearchField().setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(260, 130, 80, 25);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudents();
            }
        });
        getFrame().getContentPane().add(btnSearch);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 170, 565, 180);
        getFrame().getContentPane().add(scrollPane);

        setTable(new JTable());
        scrollPane.setViewportView(getTable());

        loadStudentData();
    }

    private void addStudent() {
        int id = Integer.parseInt(getAgeField().getText());
        String name = getNameField().getText();
        int age = Integer.parseInt(getAgeField().getText());
        String grade = getGradeField().getText();
        Student newStudent = new Student(id, name, age, grade);
        getStudentL().insertStudent(newStudent);
        loadStudentData();
        clearFields();
    }

    private void updateStudent() {
        int selectedRow = getTable().getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) getTable().getValueAt(selectedRow, 0);
            String name = getNameField().getText();
            int age = Integer.parseInt(getAgeField().getText());
            String grade = getGradeField().getText();
            Student updatedStudent = new Student(id, name, age, grade);
            getStudentL().updateStudent(updatedStudent);
            loadStudentData();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(getFrame(), "Select a student to update");
        }
    }

    private void deleteStudent() {
        int selectedRow = getTable().getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) getTable().getValueAt(selectedRow, 0);
            getStudentL().deleteStudent(id);
            loadStudentData();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(getFrame(), "Select a student to delete");
        }
    }

    private void searchStudents() {
        String query = getSearchField().getText();
        List<Student> students = getStudentL().searchStudentsByNameOrGrade(query);
        populateTable(students);
    }

    private void loadStudentData() {
        List<Student> students = getStudentL().selectAllStudents();
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
//        table.setModel(new DefaultTableModel(data, columnNames));
    }

    private void clearFields() {
        getNameField().setText("");
        getAgeField().setText("");
        getGradeField().setText("");
        getSearchField().setText("");
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public StudentL getStudentL() {
        return studentL;
    }

    public void setStudentL(StudentL studentL) {
        this.studentL = studentL;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public void setAgeField(JTextField ageField) {
        this.ageField = ageField;
    }

    public JTextField getGradeField() {
        return gradeField;
    }

    public void setGradeField(JTextField gradeField) {
        this.gradeField = gradeField;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }
}