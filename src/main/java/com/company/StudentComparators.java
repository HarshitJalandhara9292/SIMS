//package com.company;
//
//import java.util.Comparator;
//
//public class StudentComparators {
//    public static Comparator<Student> byId() {
//        return Comparator.comparingInt(Student::getId);
//    }
//
//    public static Comparator<Student> byName() {
//        return Comparator.comparing(Student::getName);
//    }
//
//    public static Comparator<Student> byAge() {
//        return Comparator.comparingInt(Student::getAge);
//    }
//
//    public static Comparator<Student> byGrade() {
//        return Comparator.comparing(Student::getGrade);
//    }
//
//
//    public static Comparator<Student> comparebyName = Comparator.comparing(Student::getName);
//    public static Comparator<Student> comparebyAge = Comparator.comparingInt(Student::getAge);
//    public static Comparator<Student> comparebyGrade = Comparator.comparing(Student::getGrade);
//}
