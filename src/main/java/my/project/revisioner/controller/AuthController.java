package my.project.revisioner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.project.revisioner.config.SecurityProps;
import my.project.revisioner.payload.request.LoginRequest;
import my.project.revisioner.payload.request.SignupRequest;
import my.project.revisioner.payload.response.JWTTokenSuccessResponse;
import my.project.revisioner.payload.response.MessageResponse;
import my.project.revisioner.security.JWTTokenProvider;
import my.project.revisioner.service.UserService;
import my.project.revisioner.validation.ResponseErrorValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class AuthController {

    private final ResponseErrorValidator errorValidator;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final SecurityProps props;

    @Operation(
            operationId = "Регистрация", description = "Регистрация пользователя",
            parameters = {@Parameter(name = "request", description = "Данные пользователя")}
    )
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignupRequest request, BindingResult bindingResult) {
        ResponseEntity<Object> errors = errorValidator.mapValidation(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        userService.save(request);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @Operation(
            operationId = "Авторизация", description = "Авторизация пользователя",
            parameters = {@Parameter(name = "request", description = "Данные пользователя")}
    )
    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@Valid @RequestBody LoginRequest request, BindingResult bindingResult) {
        ResponseEntity<Object> errors = errorValidator.mapValidation(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = props.getTokenPrefix() + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

}
