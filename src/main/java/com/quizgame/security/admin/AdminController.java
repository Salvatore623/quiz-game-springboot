package com.quizgame.security.admin;

import com.quizgame.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin("${allowed.origins}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @Value("${allowed.origins}")
    private String allowedOrigins;

    @GetMapping("/question/count")
    ResponseEntity<Map<String, Integer>> findCountQuestions(){
        return ResponseEntity.ok( adminService.findCountQuestions() );
    }


    @PostMapping("/question/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<Question> createQuestion(@RequestBody Question question){
        return ResponseEntity.ok( adminService.createQuestion(question) );
    }

    @PatchMapping("/change-role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<Map<String, String>> changeRole(@RequestBody String username){
        adminService.changeRole(username);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Ruolo cambiato con successo");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public boolean verifyAdmin(){
        return true;
    }

}
