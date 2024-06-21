package com.akampany.api.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserServices userServices;

    @Autowired
    public AppUserController(AppUserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable("id") Long id) {
        Optional<AppUser> userOptional = userServices.getUserById(id);

        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AppUser> getUserByEmail(@PathVariable("email") String email) {
        AppUser user = userServices.getUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = userServices.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@RequestBody AppUser user) {
        userServices.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody AppUser user) {
        userServices.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
