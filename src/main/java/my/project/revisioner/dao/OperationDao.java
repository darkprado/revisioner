package my.project.revisioner.dao;

import my.project.revisioner.entity.Operation;
import my.project.revisioner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OperationDao extends JpaRepository<Operation, Long> {

    Optional<Operation> findByIdAndUser(Long id, User user);

    List<Operation> findAllByUser(User user);

    List<Operation> findAllByDateBetweenAndUser(LocalDate start, LocalDate end, User user);

    List<Operation> findAllByDateBetweenAndPositiveAndUser(LocalDate start, LocalDate end, boolean positive, User user);

    void deleteByIdAndUser(Long id, User user);

}
