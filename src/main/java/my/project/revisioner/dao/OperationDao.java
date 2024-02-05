package my.project.revisioner.dao;

import my.project.revisioner.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationDao extends JpaRepository<Operation, Long> {

    List<Operation> findAllByDateBetween(LocalDate start, LocalDate end);

    List<Operation> findAllByDateBetweenAndPositive(LocalDate start, LocalDate end, boolean positive);

}
