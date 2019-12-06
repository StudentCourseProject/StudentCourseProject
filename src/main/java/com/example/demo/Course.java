package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    @Column(name = "teacher")
    private String teacher;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    public Set<Student> students;

    @Column(name = "description")
    private String description;

    public Course() {
    }

    public Course(String name, String teacher, Set<Student> students, String description) {
        this.name = name;
        this.teacher = teacher;
        this.students = students;
        this.description = description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
