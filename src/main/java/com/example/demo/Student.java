package com.example.demo;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    @Column(name = "student_id")

    private long id;

    private String firstName;

    private String lastName;

    private String DOB;

    private String url;


    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    public Student() {
    }

    public Student(String firstName, String lastName, String DOB, String url) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.url = url;
        this.courses = new HashSet<>();
    }
    public Student(String firstName, String lastName, String DOB, String url, Set<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.url = url;
        this.courses = courses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String studentImage) {
        this.url = studentImage;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
