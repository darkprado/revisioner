package my.project.revisioner.service.impl;

import lombok.RequiredArgsConstructor;
import my.project.revisioner.dao.UserDao;
import my.project.revisioner.dto.UserDto;
import my.project.revisioner.entity.User;
import my.project.revisioner.exception.UserExistsException;
import my.project.revisioner.mapper.UserMapper;
import my.project.revisioner.payload.request.SignupRequest;
import my.project.revisioner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Override
    public User save(SignupRequest request) {
        User user = userMapper.fromRequest(request);

        try {
            LOG.info("Saving user with username {}", user.getUsername());
            return userDao.save(user);
        } catch (Exception ex) {
            LOG.error("Error during registration. {}", ex.getMessage());
            throw new UserExistsException(String.format("User with username %s already exist.", user.getUsername()));
        }
    }

    @Override
    public User update(UserDto userDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        LOG.info(String.format("Updating user with username %s", user.getUsername()));
        return userDao.save(userMapper.fromDto(userDto));
    }

    @Override
    public User getById(Long userId) {
        return userDao.findUserById(userId).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with id %s not found.", userId))
        );
    }

    @Override
    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userDao.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }

}
