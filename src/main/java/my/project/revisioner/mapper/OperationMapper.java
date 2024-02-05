package my.project.revisioner.mapper;

import my.project.revisioner.dto.OperationDto;
import my.project.revisioner.entity.Operation;
import my.project.revisioner.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "date", source = "dto.date", qualifiedByName = "mapDate")
    @Mapping(target = "positive", source = "dto.sum", qualifiedByName = "mapPositive")
    Operation toEntity(OperationDto dto, User user);

    List<Operation> toEntityList(List<OperationDto> dto);

    OperationDto toDto(Operation entity);

    List<OperationDto> toDtoList(List<Operation> entity);

    @Named("mapDate")
    default LocalDate mapDate(LocalDate sourceDate) {
        return Objects.nonNull(sourceDate) ? sourceDate : LocalDate.now();
    }

    @Named("mapPositive")
    default Boolean mapPositive(Integer sourceSum) {
        return sourceSum < 0 ? Boolean.FALSE : Boolean.TRUE;
    }

}
