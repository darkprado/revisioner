package my.project.revisioner.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OperationDto {

    private Long id;

    private LocalDate date;

    @NotEmpty
    private String name;

    @NotNull
    private Integer sum;

    @NotNull
    private String type;

}
