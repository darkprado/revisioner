package my.project.revisioner.service;

import my.project.revisioner.dto.OperationDto;

import java.util.List;

public interface OperationService {

    OperationDto save(OperationDto dto);

    OperationDto findById(Long id);

    List<OperationDto> findAll();

    List<OperationDto> findAllByCurrentMonth();

    List<OperationDto> findAllByMonth(int yearNum, int monthNum);

    List<OperationDto> findAllByMonthAndPositive(int yearNum, int monthNum, boolean positive);

    void delete(Long id);

}
