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

import cntmin81.github.io.springbootrestapi.dto.user.UserRequestDto;
import cntmin81.github.io.springbootrestapi.dto.user.UserResponseDto;
import cntmin81.github.io.springbootrestapi.model.CommonResult;
import cntmin81.github.io.springbootrestapi.model.ListResult;
import cntmin81.github.io.springbootrestapi.model.SingleResult;
import cntmin81.github.io.springbootrestapi.service.ResponseService;
import cntmin81.github.io.springbootrestapi.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/id/{userId}")
    public SingleResult<UserResponseDto> findUserById(@PathVariable Long userId) {
        return responseService.getSingleResult(userService.findById(userId));
    }

    @GetMapping("/user/email/{email}")
    public SingleResult<UserResponseDto> findUserByEmail(@PathVariable String email) {
        return responseService.getSingleResult(userService.findByEmail(email));
    }

    @GetMapping("/users")
    public ListResult<UserResponseDto> findAllUser() {
        return responseService.getListResult(userService.findAllUser());
    }

    @PostMapping("/user")
    public SingleResult<Long> save(@RequestParam String email, @RequestParam String name) {
        UserRequestDto user = UserRequestDto.builder().email(email).name(name).build();
        return responseService.getSingleResult(userService.save(user));
    }

    @PutMapping("/user")
    public SingleResult<Long> modify(@RequestParam Long userId, @RequestParam String email, @RequestParam String name) {
        UserRequestDto user = UserRequestDto.builder().email(email).name(name).build();
        return responseService.getSingleResult(userService.update(userId, user));
    }

    @DeleteMapping("/user/{userId}")
    public CommonResult delete(@PathVariable Long userId) {
        userService.delete(userId);
        return responseService.getSuccessResult();
    }
}
