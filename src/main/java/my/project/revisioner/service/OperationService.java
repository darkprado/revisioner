package my.project.revisioner.service;

import my.project.revisioner.dto.OperationDto;

import java.security.Principal;
import java.util.List;

public interface OperationService {

    OperationDto save(OperationDto dto, Principal principal);

    OperationDto findById(Long id, Principal principal);

    List<OperationDto> findAll(Principal principal);

    List<OperationDto> findAllByCurrentMonth(Principal principal);

    List<OperationDto> findAllByMonth(int yearNum, int monthNum, Principal principal);

    List<OperationDto> findAllByMonthAndPositive(int yearNum, int monthNum, boolean positive, Principal principal);

    void delete(Long id, Principal principal);

}
