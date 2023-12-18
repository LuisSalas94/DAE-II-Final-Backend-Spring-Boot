package net.fernandosalas.backend.controller;

import net.fernandosalas.backend.exception.ResourceNotFoundException;
import net.fernandosalas.backend.model.Register;
import net.fernandosalas.backend.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class RegisterController {
    @Autowired
    private RegisterRepository registerRepository;

    @PostMapping("/registers")
    public Register createRegister(@RequestBody Register register) {
        return registerRepository.save(register);
    }

    @GetMapping("/registers")
    public List<Register> getAllRegisters() {
        return registerRepository.findAll();
    }

    @GetMapping("/registers/{id}")
    public ResponseEntity<Register> getRegisterById(@PathVariable Long id) {
        Register register = registerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Register does not exist with id: " + id));
        return ResponseEntity.ok(register);
    }

    @PutMapping("/registers/{id}")
    public ResponseEntity<Register> updateRegister(@PathVariable Long id,
                                                   @RequestBody Register registerDetails) {
        Register register = registerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Register does not exist with id: " + id));
        register.setStudentName(registerDetails.getStudentName());
        register.setStudentCourse(registerDetails.getStudentCourse());
        register.setDateCreated(registerDetails.getDateCreated());
        Register updateRegister = registerRepository.save(register);
        return ResponseEntity.ok(updateRegister);
    }

    @DeleteMapping("/registers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRegister(@PathVariable Long id) {
        Register register = registerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Register does not exist with id: " + id));
        registerRepository.delete(register);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
