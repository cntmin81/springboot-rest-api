package cntmin81.github.io.springbootrestapi.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cntmin81.github.io.springbootrestapi.advice.exception.UserNotFoundException;
import cntmin81.github.io.springbootrestapi.entity.User;
import cntmin81.github.io.springbootrestapi.model.CommonResult;
import cntmin81.github.io.springbootrestapi.model.ListResult;
import cntmin81.github.io.springbootrestapi.model.SingleResult;
import cntmin81.github.io.springbootrestapi.repository.UserRepo;
import cntmin81.github.io.springbootrestapi.service.ResponseService;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user/id/{userId}")
    public SingleResult<User> findUserById(@PathVariable Long userId) {
        return responseService.getSingleResult(userRepo.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @GetMapping("/user/email/{email}")
    public SingleResult<User> findUserByEmail(@PathVariable String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        } else {
            return responseService.getSingleResult(user);
        }
    }

    @GetMapping("/users")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userRepo.findAll());
    }

    @PostMapping("/user")
    public SingleResult<User> save(@RequestParam String email, @RequestParam String name) {
        User user = User.builder().email(email).name(name).build();
        return responseService.getSingleResult(userRepo.save(user));
    }

    @PutMapping("/user")
    public SingleResult<User> modify(@RequestParam Long userId, @RequestParam String email, @RequestParam String name) {
        User user = User.builder().id(userId).email(email).name(name).build();
        return responseService.getSingleResult(userRepo.save(user));
    }

    @DeleteMapping("/user/{userId}")
    public CommonResult delete(@PathVariable Long userId) {
        userRepo.deleteById(userId);
        return responseService.getSuccessResult();
    }
}
