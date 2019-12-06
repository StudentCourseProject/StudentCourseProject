package com.example.demo;

import javax.persistence.*;
import java.util.Collection;

@Entity

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String firstName;

    private String lastName;

    private int DOB;

    private String studentImage;


    @ManyToMany(mappedBy = "Student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Coarse> coarses;

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

    public int getDOB() {
        return DOB;
    }

    public void setDOB(int DOB) {
        this.DOB = DOB;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    public Collection<Coarse> getCoarses() {
        return coarses;
    }

    public void setCoarses(Collection<Coarse> coarses) {
        this.coarses = coarses;
    }
}
