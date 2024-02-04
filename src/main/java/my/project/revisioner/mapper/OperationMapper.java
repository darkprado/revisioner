package my.project.revisioner.mapper;

import my.project.revisioner.dto.OperationDto;
import my.project.revisioner.entity.OperationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    @Mapping(target = "date", source = "date", qualifiedByName = "mapDate")
    @Mapping(target = "positive", source = "sum", qualifiedByName = "mapPositive")
    OperationEntity toEntity(OperationDto dto);

    List<OperationEntity> toEntityList(List<OperationDto> dto);

    OperationDto toDto(OperationEntity entity);

    List<OperationDto> toDtoList(List<OperationEntity> entity);

    @Named("mapDate")
    default LocalDate mapDate(LocalDate sourceDate) {
        return Objects.nonNull(sourceDate) ? sourceDate : LocalDate.now();
    }

    @Named("mapPositive")
    default Boolean mapPositive(Integer sourceSum) {
        return sourceSum < 0 ? Boolean.FALSE : Boolean.TRUE;
    }

}
