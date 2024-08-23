package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentC {
    public void addStudent(int id, String name, int age, String grade) {
        String query = "INSERT INTO Studentsss (id, name, age, grade) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, grade);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Studentsss";
        try (Connection connection = Database_Connection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String grade = resultSet.getString("grade");
                students.add(new Student(id, name, age, grade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void updateStudent(int id, String name, int age, String grade) {
        String query = "UPDATE Studentsss SET name = ?, age = ?, grade = ? WHERE id = ?";
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, grade);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM Studentsss WHERE id = ?";
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}