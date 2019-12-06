package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception{
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("pierz@pierz.com", "password", "pierz", "barry", true, "user");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        Course course = new Course("Java", "Victor", "Basics of web development using java");
        courseRepository.save(course);

        Set<Student> students = new HashSet<>();
        //courses = student.courses
        Set<Course> courses = new HashSet<>();
        courses.add(course);

        Student student = new Student("Tony", "Franks","01/01/1990","http://img.fixthephoto.com/blog/UserFiles/Image/222/40-best-headshot-poses_1242x1242.jpg");
        student.setCourses(courses);
        students.add(student);
        studentRepository.save(student);

        student = new Student("Pierz","Barry","10/25/97", "https://static.livebooks.com/b18c364831dd4d4ca0794ece1769bb78/i/d2685474f6f84b8988dd5123cb4e67ce/1/4SoifmQp45JMgBnHp7ed2/Maryland-Headshot-Photographer_004%20copy.jpg");
        student.setCourses(courses);
        students.add(student);
        studentRepository.save(student);
        course.setStudents(students);
        courseRepository.save(course);

        course = new Course("Bootstrap", "Betty", "Essential front-end library for any developer");
        courseRepository.save(course);
        students = new HashSet<>();
        course.setStudents(students);
        courseRepository.save(course);
    }

    }
