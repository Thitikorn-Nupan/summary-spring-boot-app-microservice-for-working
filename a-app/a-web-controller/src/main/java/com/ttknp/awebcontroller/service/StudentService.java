package com.ttknp.awebcontroller.service;

import com.ttknp.awebcontroller.entity.Student;

import java.util.HashSet;
import java.util.Set;

public class StudentService {

    private Set<Student> students;

    public StudentService() {
        students = new HashSet<>();
        students.add(new Student(503230,"Alex Slider",25));
        students.add(new Student(903234,"Kevin Owner",27));
        students.add(new Student(333204,"Adam Sandler",21));
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Student getStudentById(int id) {
        for (Student student : students) if (student.getId() == id) return student;
        return null;
    }
}
