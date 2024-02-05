package my.project.revisioner.service;

import my.project.revisioner.dto.UserDto;
import my.project.revisioner.entity.User;
import my.project.revisioner.payload.request.SignupRequest;

import java.security.Principal;

public interface UserService {

    User save(SignupRequest request);

    User update(UserDto userDto, Principal principal);

    User getById(Long userId);

    User getCurrentUser(Principal principal);

}
