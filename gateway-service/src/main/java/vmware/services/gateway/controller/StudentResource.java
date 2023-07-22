package vmware.services.gateway.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vmware.services.gateway.entity.Student;
import vmware.services.gateway.repository.StudentRepository;

import java.util.List;

@RestController
@OpenAPIDefinition(info = @Info(title = "Student API", version = "v3"))
@SecurityRequirement(name = "basicAuth")
@RequestMapping(value = "/Student", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentResource {

private final StudentRepository studentRepository;

    public StudentResource(StudentRepository studentRepository) {
        this.studentRepository=studentRepository;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
