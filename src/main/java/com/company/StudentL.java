package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class StudentL {
    private static final Logger logger = LoggerFactory.getLogger(StudentL.class);
    private static final String INSERT_STUDENT_SQL = "INSERT INTO studentsss (id, name, age, grade) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID = "SELECT id, name, age, grade FROM studentsss WHERE id = ?";
    private static final String SELECT_ALL_STUDENTS = "SELECT id, name, age, grade FROM studentsss WHERE name LIKE ? OR grade LIKE ?";
    private static final String DELETE_STUDENT_SQL = "DELETE FROM studentsss WHERE id = ?";
    private static final String UPDATE_STUDENT_SQL = "UPDATE studentsss SET name = ?, age = ?, grade = ? WHERE id = ?";

    public void insertStudent(Student student) {
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGrade());
            preparedStatement.executeUpdate();
            logger.info("Inserted student: " + student);
        } catch (SQLException e) {
            logger.error("Failed to insert student");
        }
    }

    public Student selectStudent(int id) {
        Student student = null;
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String grade = rs.getString("grade");
                student = new Student(id, name, age, grade);
            }
            logger.info("Selected student: " + student);
        } catch (SQLException e) {
            logger.error("Failed to select student");
        }
        return student;
    }

    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String grade = rs.getString("grade");
                students.add(new Student(id, name, age, grade));
            }
            logger.info("Selected all students");
        } catch (SQLException e) {
            logger.error("Failed to select all students");
        }
        return students;
    }

    public boolean deleteStudent(int id) {
        boolean rowDeleted = false;
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            logger.info("Deleted student with ID: " + id);
        } catch (SQLException e) {
            logger.error("Failed to delete student");
        }
        return rowDeleted;
    }

    public void updateStudent(Student student) {
        boolean rowUpdated = false;
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL)) {
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getGrade());

            rowUpdated = statement.executeUpdate() > 0;
            logger.info("Updated student: " + student);
        } catch (SQLException e) {
            logger.error("Failed to update student");
        }
    }
    public List<Student> searchStudentsByNameOrGrade(String query) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = Database_Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String grade = rs.getString("grade");
                students.add(new Student(id, name, age, grade));
            }
            logger.info("Searched students with query: " + query);
        } catch (SQLException e) {
            logger.error("Failed to search students: " + e.getMessage());
        }
        return students;
    }
}