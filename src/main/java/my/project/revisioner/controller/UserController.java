package my.project.revisioner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.project.revisioner.dto.UserDto;
import my.project.revisioner.mapper.UserMapper;
import my.project.revisioner.service.UserService;
import my.project.revisioner.validation.ResponseErrorValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseErrorValidator validator;
    private final UserMapper userMapper;

    @Operation(
            operationId = "Получить текущего пользователя", description = "Получает текущего пользователя")
    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        return new ResponseEntity<>(userMapper.toDto(userService.getCurrentUser(principal)), HttpStatus.OK);
    }

    @Operation(
            operationId = "Получить пользователя по идентификатору", description = "Получает пользователя по идентификатору",
            parameters = {@Parameter(name = "userId", description = "идентификатор пользователя")}
    )
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userMapper.toDto(userService.getById(Long.parseLong(userId))), HttpStatus.OK);
    }

    @Operation(
            operationId = "Обновить текущего пользователя", description = "Обновляет текущего пользователя")
    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = validator.mapValidation(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return new ResponseEntity<>(userMapper.toDto(userService.update(userDto, principal)), HttpStatus.OK);
    }

}
