package cntmin81.github.io.springbootrestapi.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cntmin81.github.io.springbootrestapi.advice.exception.UserNotFoundException;
import cntmin81.github.io.springbootrestapi.dto.user.UserRequestDto;
import cntmin81.github.io.springbootrestapi.dto.user.UserResponseDto;
import cntmin81.github.io.springbootrestapi.entity.User;
import cntmin81.github.io.springbootrestapi.repository.UserRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;

    @Transactional
    public Long save(UserRequestDto userRequestDto) {
        userRepo.save(userRequestDto.toEntity());
        return userRepo.findByEmail(userRequestDto.getEmail()).getId();
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userRepo.findById(id).orElseThrow(UserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findByEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        } else {
            return new UserResponseDto(user);
        }
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllUser() {
        return userRepo.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, UserRequestDto userRequestDto) {
        User modifiedUser = userRepo.findById(id).orElseThrow(UserNotFoundException::new);
        modifiedUser.setEmail(userRequestDto.getEmail());
        modifiedUser.setName(userRequestDto.getName());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
 }
