package cntmin81.github.io.springbootrestapi.dto.user;

import cntmin81.github.io.springbootrestapi.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long userId;
    private String name;
    private String email;

    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
