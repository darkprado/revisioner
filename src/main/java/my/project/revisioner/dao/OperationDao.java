package my.project.revisioner.dao;

import my.project.revisioner.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationDao extends JpaRepository<OperationEntity, Long> {

    List<OperationEntity> findAllByDateBetween(LocalDate start, LocalDate end);

    List<OperationEntity> findAllByDateBetweenAndPositive(LocalDate start, LocalDate end, boolean positive);

}
