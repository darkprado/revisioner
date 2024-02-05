package my.project.revisioner.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import my.project.revisioner.enums.OperationType;

import java.time.LocalDate;

@Data
public class OperationDto {

    @Schema(description = "Идентификатор операции")
    private Long id;

    @Schema(description = "Дата операции")
    private LocalDate date;

    @Schema(description = "Название операции")
    @NotEmpty
    private String name;

    @Schema(description = "Сумма операции")
    @NotNull
    private Integer sum;

    @Schema(description = "Тип операции")
    @NotNull
    private OperationType type;

}
