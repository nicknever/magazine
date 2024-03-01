package backend.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  @RestController
  @RequestMapping("/student")
  public class StudentController {
      private List<Student> students = new ArrayList<>();

      @GetMapping
      public List<Student> getAllStudents() {
          return students;
      }

      @PostMapping
      public ResponseEntity<String> addStudent(@RequestBody Student student) {
          try {
              students.add(student);
              return new ResponseEntity<>("Student added successfully", HttpStatus.CREATED);
          } catch (Exception e) {
              return new ResponseEntity<>("Failed to add student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }

      @ExceptionHandler(Exception.class)
      public ResponseEntity<String> handleException(Exception e) {
          return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
}

class Student {
    private String name;
    private String surname;
    private String email;
    private String description;

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
