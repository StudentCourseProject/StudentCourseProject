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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

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

    @PostMapping("/processCourse")
    public String processCourse(@Valid @ModelAttribute Course course, BindingResult result) {
        if (result.hasErrors()) {
            return "courseform";
        }
        courseRepository.save(course);
        return "list";
    }

/// ADDING A STUDENT

    @GetMapping("/addStudent")
    public String studentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseRepository.findAll());
        return "studentform";
    }

    @PostMapping("/processStudent")
    public String processStudent(@Valid @ModelAttribute("student") Student student, BindingResult result,
                                 Model model, @RequestParam("courseId") long id, @RequestParam("file") MultipartFile file) {

        model.addAttribute("students", userRepository.findAll());
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            student.setStudentImage(uploadResult.get("url").toString());
            studentRepository.save(student);

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/add";
        }
        studentRepository.save(student);
        return "list";
    }
}
