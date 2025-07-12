package org.example.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.dto.UserDto;
import org.example.dto.UserResponse;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/developers")
public class Developers {
    private final UserService userService;

    public Developers(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDto user) {
        User registeredUser = userService.registerUser(user);
        URI location = URI.create("/api/developers/signup/" + registeredUser.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable @NotNull @Min(0) Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
