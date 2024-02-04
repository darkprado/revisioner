package my.project.revisioner.service.impl;

import lombok.RequiredArgsConstructor;
import my.project.revisioner.dao.OperationDao;
import my.project.revisioner.dto.OperationDto;
import my.project.revisioner.mapper.OperationMapper;
import my.project.revisioner.service.OperationService;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationServiceImpl implements OperationService {

    private final OperationDao dao;
    private final OperationMapper mapper;

    public OperationDto save(OperationDto dto) {
        return mapper.toDto(dao.save(mapper.toEntity(dto)));
    }

    @Override
    public OperationDto findById(Long id) {
        return mapper.toDto(dao.findById(id).orElse(null));
    }

    @Override
    public List<OperationDto> findAll() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    public List<OperationDto> findAllByCurrentMonth() {
        return mapper.toDtoList(dao.findAllByDateBetween(YearMonth.now().atDay(1), YearMonth.now().atEndOfMonth()));
    }

    @Override
    public List<OperationDto> findAllByMonth(int yearNum, int monthNum) {
        YearMonth date = YearMonth.of(yearNum, monthNum);
        return mapper.toDtoList(dao.findAllByDateBetween(date.atDay(1), date.atEndOfMonth()));
    }

    @Override
    public List<OperationDto> findAllByMonthAndPositive(int yearNum, int monthNum, boolean positive) {
        YearMonth date = YearMonth.of(yearNum, monthNum);
        return mapper.toDtoList(dao.findAllByDateBetweenAndPositive(date.atDay(1), date.atEndOfMonth(), positive));
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }

}
