package com.company;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Studentmanagementapp {
    public JFrame frame;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField gradeField;
    private JTable table;
    private DefaultTableModel tableModel;

    public Studentmanagementapp() {
        this.idField = idField;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Student Management System");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();
        frame.getContentPane().add(formPanel, BorderLayout.NORTH);

        JScrollPane tableScrollPane = createTableScrollPane();
        frame.getContentPane().add(tableScrollPane, BorderLayout.CENTER);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        JLabel idLabel = new JLabel("id:");
        idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(5);
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(5);
        JButton addButton = new JButton("Add Student");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(ageLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(gradeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(gradeField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(addButton, gbc);

        return panel;
    }

    private JScrollPane createTableScrollPane() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Grade");

        table = new JTable(tableModel);

        return new JScrollPane(table);
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String grade = gradeField.getText();

            tableModel.addRow(new Object[]{id, name, age, grade});

            idField.setText("");
            nameField.setText("");
            ageField.setText("");
            gradeField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(frame, "Invalid input");
        }
    }

    public void showWindow() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            Studentmanagementapp window = new Studentmanagementapp();
            window.showWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
