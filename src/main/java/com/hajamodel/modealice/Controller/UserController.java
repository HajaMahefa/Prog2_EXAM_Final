package com.hajamodel.modealice.Controller;

import com.hajamodel.modealice.DTO.UserDto;
import com.hajamodel.modealice.Model.User;
import com.hajamodel.modealice.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Set<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/insertUser")
    public String addUser(@RequestBody User user) {
        userService.insert(user);
        return "User inserted successfully";
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/selectUserById/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User newUser) {
        userService.update(id, newUser);
        return "User updated successfully";
    }
    @GetMapping("/selectUserByIdDto/{id}")
    public UserDto findUserByIdDto(@PathVariable int id) throws SQLException {
        return userService.finByIdDto(id);
    }
}
