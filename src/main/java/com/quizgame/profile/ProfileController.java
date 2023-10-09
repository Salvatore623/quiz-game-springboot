package com.quizgame.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/profile")
@CrossOrigin("${allowed.origins}")
public class ProfileController {

    private final ProfileService profileService;

    @Value("${allowed.origins}")
    private String allowedOrigins;

    @GetMapping("/{username}")
    ResponseEntity<Profile> getProfileByUsername(@PathVariable String username){
        return ResponseEntity.ok( profileService.getProfileByUsername(username) );
    }

    @PatchMapping("/change-avatar")
    public void changeAvatar(@RequestBody ChangeAvatarRequest req){
        profileService.changeAvatar(req);
    }

}
