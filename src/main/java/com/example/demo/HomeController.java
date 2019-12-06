package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {

//    @Autowired
//    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CloudinaryConfig cloudc;

    /// SECURE LOGIN
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/secure")
    public String secure() {
        return "secure";
    }

/// ADDING A COURSE

    @GetMapping("/addCourse")
    public String courseForm(Model model) {
        model.addAttribute("course", new Course());
        return "courseform";
    }

/// PROCESSING A COURSE

//    @PostMapping("/processCourse")
//    public String processCourse(@Valid @ModelAttribute Course course, BindingResult result) {
//        if (result.hasErrors()) {
//            return "courseform";
//        }
//        courseRepository.save(course);
//        return "index";
//    }

/// ADDING A STUDENT

    @GetMapping("/addStudent")
    public String studentForm(Model model) {
        model.addAttribute("student", new Student());
//        model.addAttribute("courses", courseRepository.findAll());
        return "studentform";
    }

//    @PostMapping("/processStudent")
//    public String processStudent(@Valid @ModelAttribute("student") Student student,  BindingResult result,
//                                 Model model, @RequestParam("courseId") long id, @RequestParam("file") MultipartFile file) {
//
//        model.addAttribute("students", userRepository.findAll());
//        try {
//            Map uploadResult = cloudc.upload(file.getBytes(),
//                    ObjectUtils.asMap("resourcetype", "auto"));
//            student.setUrl(uploadResult.get("url").toString());
//            studentRepository.save(student);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "redirect:/add";
//        }
//        studentRepository.save(student);
//        return "index";
//    }
    @RequestMapping("/addlink")
        public String addLink(Model model){
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        return "link";

        }

        @PostMapping("/processlink")
        public String processLink(@RequestParam("courseId") long id, @RequestParam("studentId") long stdid){
            Course course = courseRepository.findById(id).get();
            Student student = studentRepository.findById(stdid).get();
            Set<Student> students;
            Set<Course> courses;
            if(course.getStudents().size() > 0) {
                students = new HashSet<>(course.getStudents());
            }
            else{
                students = new HashSet<>();
            }
            if(student.getCourses().size() > 0) {
                courses = new HashSet<>(student.getCourses());
            }
            else {
                courses = new HashSet<>();
            }
            students.add(student);
            courses.add(course);
            course.setStudents(students);
            courseRepository.save(course);
            student.setCourses(courses);
            studentRepository.save(student);
            return "redirect:/";
        }


    @PostMapping("/processStudent")
    public String processStudent(@Valid @ModelAttribute Student student) {
        studentRepository.save(student);
        Set<Course> courses;
        if (student.getCourses() == null) {
            courses = new HashSet<>();
        } else {
            courses = new HashSet<>(student.getCourses());
        }
        student.setCourses(courses);
        studentRepository.save(student);
        return "redirect:/";
    }

    @PostMapping("/processCourse")
    public String processCourse(@Valid @ModelAttribute Course course){
        Set<Student> students = new HashSet<>();
        course.setStudents(students);
        courseRepository.save(course);
        return "redirect:/";
    }
    @RequestMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable("id") long id, Model model){
        model.addAttribute("student",studentRepository.findById(id).get());
        return "studentform";
    }

    @RequestMapping("/updateCourse/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepository.findById(id).get());
        return "courseform";
    }

    @RequestMapping("/deleteCourse/{id}")
    public String delCourse(@PathVariable("id") long id){
        courseRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/deleteStudent/{id}")
    public String delStudent(@PathVariable("id") long id){
        studentRepository.deleteById(id);
        return "redirect:/";
    }
}
