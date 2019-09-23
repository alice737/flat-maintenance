package com.flat.flatmaintenance.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> users = userService.findById(id);
        if (!users.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.badRequest().build();
        }
        log.info("Id " + id + " is existed");

        return ResponseEntity.ok(users.get());
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping()
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        if (!userService.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
           return ResponseEntity.badRequest().build();

        }
        userService.deleteById(id);
        log.info("Id " + id + " is existed");

        return ResponseEntity.ok().build();
    }
}
