package my.project.revisioner.service.impl;

import lombok.RequiredArgsConstructor;
import my.project.revisioner.dao.OperationDao;
import my.project.revisioner.dao.UserDao;
import my.project.revisioner.dto.OperationDto;
import my.project.revisioner.entity.User;
import my.project.revisioner.exception.OperationNotFoundException;
import my.project.revisioner.mapper.OperationMapper;
import my.project.revisioner.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.YearMonth;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class OperationServiceImpl implements OperationService {

    public static final Logger LOG = LoggerFactory.getLogger(OperationService.class);

    private final OperationDao dao;
    private final UserDao userDao;
    private final OperationMapper mapper;

    @Override
    public OperationDto save(OperationDto dto, Principal principal) {
        User user = getUserByPrincipal(principal);
        LOG.info("Saving post for user with username {}", user.getUsername());
        return mapper.toDto(dao.save(mapper.toEntity(dto, user)));
    }

    @Override
    public OperationDto findById(Long id, Principal principal) {
        User user = getUserByPrincipal(principal);
        return mapper.toDto(dao.findByIdAndUser(id, user).orElseThrow(
                () -> new OperationNotFoundException(
                        String.format("Operation with id %s from user with username %s not found", id, user.getUsername()))));
    }

    @Override
    public List<OperationDto> findAll(Principal principal) {
        return mapper.toDtoList(dao.findAllByUser(getUserByPrincipal(principal)));
    }

    @Override
    public List<OperationDto> findAllByCurrentMonth(Principal principal) {
        return mapper.toDtoList(dao.findAllByDateBetweenAndUser(YearMonth.now().atDay(1), YearMonth.now().atEndOfMonth(), getUserByPrincipal(principal)));
    }

    @Override
    public List<OperationDto> findAllByMonth(int yearNum, int monthNum, Principal principal) {
        YearMonth date = YearMonth.of(yearNum, monthNum);
        return mapper.toDtoList(dao.findAllByDateBetweenAndUser(date.atDay(1), date.atEndOfMonth(), getUserByPrincipal(principal)));
    }

    @Override
    public List<OperationDto> findAllByMonthAndPositive(int yearNum, int monthNum, boolean positive, Principal principal) {
        YearMonth date = YearMonth.of(yearNum, monthNum);
        return mapper.toDtoList(dao.findAllByDateBetweenAndPositiveAndUser(date.atDay(1), date.atEndOfMonth(), positive, getUserByPrincipal(principal)));
    }


    @Override
    public void delete(Long id, Principal principal) {
        dao.deleteByIdAndUser(id, getUserByPrincipal(principal));
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userDao.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }

}
