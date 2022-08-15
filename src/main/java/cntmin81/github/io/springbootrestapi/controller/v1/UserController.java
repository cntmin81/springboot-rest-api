package cntmin81.github.io.springbootrestapi.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cntmin81.github.io.springbootrestapi.entity.User;
import cntmin81.github.io.springbootrestapi.repository.UserRepo;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/user")
    public User save() {
        User user = User.builder().email("test@test.com").name("Han").build();
        return userRepo.save(user);
    }

    @GetMapping("/finduser/{name}")
    public User findUserByName(@PathVariable String name) {
        return userRepo.findByName(name);
    }
}
